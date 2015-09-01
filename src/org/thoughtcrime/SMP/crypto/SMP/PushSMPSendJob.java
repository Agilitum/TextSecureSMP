package org.thoughtcrime.SMP.crypto.SMP;

import android.content.Context;
import android.util.Log;

import org.thoughtcrime.SMP.ApplicationContext;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.database.EncryptingSMPDatabase;
import org.thoughtcrime.SMP.database.NoSuchMessageException;
import org.thoughtcrime.SMP.database.SMPDatabase;
import org.thoughtcrime.SMP.database.model.SMPMessageRecord;
import org.thoughtcrime.SMP.dependencies.InjectableType;
import org.thoughtcrime.SMP.dependencies.TextSecureCommunicationModule;
import org.thoughtcrime.SMP.jobs.DirectoryRefreshJob;
import org.thoughtcrime.SMP.jobs.PushSendJob;
import org.thoughtcrime.SMP.notifications.MessageNotifier;
import org.thoughtcrime.SMP.recipients.RecipientFactory;
import org.thoughtcrime.SMP.recipients.Recipients;
import org.thoughtcrime.SMP.transport.InsecureFallbackApprovalException;
import org.thoughtcrime.SMP.transport.RetryLaterException;
import org.whispersystems.textsecure.api.crypto.UntrustedIdentityException;
import org.whispersystems.textsecure.api.push.TextSecureAddress;
import org.whispersystems.textsecure.api.push.exceptions.UnregisteredUserException;
import org.whispersystems.textsecure.api.util.InvalidNumberException;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by ludwig on 10/07/15.
 */
public class PushSMPSendJob extends PushSendJob implements InjectableType {

	private static final String TAG = PushSMPSendJob.class.getSimpleName();

	@Inject
	transient TextSecureCommunicationModule.TextSecureSMPMessageSenderFactory messageSenderFactory;

	private final long messageId;

	public PushSMPSendJob(Context context, long messageId, String destination) {
		super(context, constructParameters(context, destination));
		this.messageId = messageId;
	}

	@Override
	public void onAdded() {
		SMPDatabase smpDatabase = DatabaseFactory.getSMPDatabase(context);
		smpDatabase.markAsSending(messageId);
		smpDatabase.markAsPush(messageId);
	}

	@Override
	public void onSend(MasterSecret masterSecret) throws NoSuchMessageException, RetryLaterException {
		EncryptingSMPDatabase database = DatabaseFactory.getEncryptingSMPDatabase(context);
		SMPMessageRecord record   = database.getMessage(masterSecret, messageId);

		try {
			Log.d(TAG, "Sending message: " + messageId);

			deliver(masterSecret, record);
			database.markAsPush(messageId);
			database.markAsSecure(messageId);
			database.markAsSent(messageId);

		} catch (InsecureFallbackApprovalException e) {
			Log.w(TAG, e);
			database.markAsPendingInsecureSmsFallback(record.getId());
			MessageNotifier.notifyMessageDeliveryFailed(context, record.getRecipients(), record.getThreadId());
			ApplicationContext.getInstance(context).getJobManager().add(new DirectoryRefreshJob(context));
		} catch (UntrustedIdentityException e) {
			Log.w(TAG, e);
			Recipients recipients  = RecipientFactory.getRecipientsFromString(context, e.getE164Number(), false);
			long       recipientId = recipients.getPrimaryRecipient().getRecipientId();

			database.addMismatchedIdentity(record.getId(), recipientId, e.getIdentityKey());
			database.markAsSentFailed(record.getId());
			database.markAsPush(record.getId());
		}
	}

	@Override
	public boolean onShouldRetryThrowable(Exception exception) {
		if (exception instanceof RetryLaterException) return true;

		return false;
	}

	@Override
	public void onCanceled() {
		DatabaseFactory.getSmsDatabase(context).markAsSentFailed(messageId);

		long       threadId   = DatabaseFactory.getSmsDatabase(context).getThreadIdForMessage(messageId);
		Recipients recipients = DatabaseFactory.getThreadDatabase(context).getRecipientsForThreadId(threadId);

		if (threadId != -1 && recipients != null) {
			MessageNotifier.notifyMessageDeliveryFailed(context, recipients, threadId);
		}
	}

	private void deliver(MasterSecret masterSecret, SMPMessageRecord message)
		throws UntrustedIdentityException, InsecureFallbackApprovalException, RetryLaterException
	{
		try {
			TextSecureAddress address = getPushAddress(message.getIndividualRecipient().getNumber());
			TextSecureSMPMessageSender messageSender = messageSenderFactory.create(masterSecret);

			TextSecureSMPMessage textSecureSMPMessage = TextSecureSMPMessage.newSMPBuilder()
				.withTimestamp(message.getDateSent())
				.withBody(message.getBody().getBody())
				.asEndSessionMessage(message.isEndSession())
				.asSMPSessionMessage(message.isSMPMessage())
				.asSMPSyncSessionMessage(message.isSMPSyncMessage())
				.build();

			messageSender.sendMessage(address, textSecureSMPMessage);

		} catch (InvalidNumberException | UnregisteredUserException e) {
			Log.w(TAG, e);
			throw new InsecureFallbackApprovalException(e);
		} catch (IOException e) {
			Log.w(TAG, e);
			throw new RetryLaterException(e);
		}
	}
}
