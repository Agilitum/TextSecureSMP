package org.thoughtcrime.SMP.database.model;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.database.MmsSmsColumns;
import org.thoughtcrime.SMP.database.SmsDatabase;
import org.thoughtcrime.SMP.database.documents.IdentityKeyMismatch;
import org.thoughtcrime.SMP.database.documents.NetworkFailure;
import org.thoughtcrime.SMP.database.model.DisplayRecord;
import org.thoughtcrime.SMP.database.model.MessageRecord;
import org.thoughtcrime.SMP.recipients.Recipient;
import org.thoughtcrime.SMP.recipients.Recipients;
import org.thoughtcrime.SMP.util.GroupUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ludwig on 31/07/15.
 */
public class SMPMessageRecord  extends MessageRecord {

	public SMPMessageRecord(Context context, long id,
	                        Body body, Recipients recipients,
	                        Recipient individualRecipient,
	                        int recipientDeviceId,
	                        long dateSent, long dateReceived,
	                        int receiptCount,
	                        long type, long threadId,
	                        int status, List<IdentityKeyMismatch> mismatches)
	{
		super(context, id, body, recipients, individualRecipient, recipientDeviceId,
			dateSent, dateReceived, threadId, receiptCount, getGenericDeliveryStatus(status), type,
			mismatches, new LinkedList<NetworkFailure>());
	}

	public long getType() {
		return type;
	}

	@Override
	public SpannableString getDisplayBody() {
		if (SmsDatabase.Types.isFailedDecryptType(type)) {
			return emphasisAdded(context.getString(R.string.MessageDisplayHelper_bad_encrypted_message));
		} else if (isProcessedKeyExchange()) {
			return new SpannableString("");
		} else if (isStaleKeyExchange()) {
			return emphasisAdded(context.getString(R.string.ConversationItem_error_received_stale_key_exchange_message));
		} else if (isCorruptedKeyExchange()) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_received_corrupted_key_exchange_message));
		} else if (isInvalidVersionKeyExchange()) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_received_key_exchange_message_for_invalid_protocol_version));
		} else if (MmsSmsColumns.Types.isLegacyType(type)) {
			return emphasisAdded(context.getString(R.string.MessageRecord_message_encrypted_with_a_legacy_protocol_version_that_is_no_longer_supported));
		} else if (isBundleKeyExchange()) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_received_message_with_unknown_identity_key_click_to_process));
		} else if (isIdentityUpdate()) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_received_updated_but_unknown_identity_information));
		} else if (isKeyExchange() && isOutgoing()) {
			return new SpannableString("");
		} else if (isKeyExchange() && !isOutgoing()) {
			return emphasisAdded(context.getString(R.string.ConversationItem_received_key_exchange_message_click_to_process));
		} else if (SmsDatabase.Types.isDuplicateMessageType(type)) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_duplicate_message));
		} else if (SmsDatabase.Types.isDecryptInProgressType(type)) {
			return emphasisAdded(context.getString(R.string.MessageDisplayHelper_decrypting_please_wait));
		} else if (SmsDatabase.Types.isNoRemoteSessionType(type)) {
			return emphasisAdded(context.getString(R.string.MessageDisplayHelper_message_encrypted_for_non_existing_session));
		} else if (!getBody().isPlaintext()) {
			return emphasisAdded(context.getString(R.string.MessageNotifier_encrypted_message));
		} else if (SmsDatabase.Types.isEndSessionType(type)) {
			return emphasisAdded(context.getString(R.string.SmsMessageRecord_secure_session_ended));
		} else {
			return super.getDisplayBody();
		}
	}

	@Override
	public boolean isMms() {
		return false;
	}

	@Override
	public boolean isMmsNotification() {
		return false;
	}

	private static int getGenericDeliveryStatus(int status) {
		if (status == SmsDatabase.Status.STATUS_NONE) {
			return MessageRecord.DELIVERY_STATUS_NONE;
		} else if (status >= SmsDatabase.Status.STATUS_FAILED) {
			return MessageRecord.DELIVERY_STATUS_FAILED;
		} else if (status >= SmsDatabase.Status.STATUS_PENDING) {
			return MessageRecord.DELIVERY_STATUS_PENDING;
		} else {
			return MessageRecord.DELIVERY_STATUS_RECEIVED;
		}
	}
}

