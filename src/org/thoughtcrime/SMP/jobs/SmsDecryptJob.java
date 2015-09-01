package org.thoughtcrime.SMP.jobs;

import android.content.Context;
import android.util.Log;

import org.thoughtcrime.SMP.crypto.AsymmetricMasterCipher;
import org.thoughtcrime.SMP.crypto.AsymmetricMasterSecret;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.crypto.MasterSecretUtil;
import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.database.EncryptingSmsDatabase;
import org.thoughtcrime.SMP.database.NoSuchMessageException;
import org.thoughtcrime.SMP.database.model.SmsMessageRecord;
import org.thoughtcrime.SMP.jobs.requirements.MasterSecretRequirement;
import org.thoughtcrime.SMP.notifications.MessageNotifier;
import org.thoughtcrime.SMP.sms.IncomingEncryptedMessage;
import org.thoughtcrime.SMP.sms.IncomingTextMessage;
import org.whispersystems.jobqueue.JobParameters;
import org.whispersystems.libaxolotl.InvalidMessageException;
import org.whispersystems.libaxolotl.util.guava.Optional;
import org.whispersystems.textsecure.api.messages.TextSecureGroup;

import java.io.IOException;

public class SmsDecryptJob extends MasterSecretJob {

  private static final String TAG = SmsDecryptJob.class.getSimpleName();

  private final long messageId;

  public SmsDecryptJob(Context context, long messageId) {
    super(context, JobParameters.newBuilder()
                                .withPersistence()
                                .withRequirement(new MasterSecretRequirement(context))
                                .create());

    this.messageId = messageId;
  }

  @Override
  public void onAdded() {}

  @Override
  public void onRun(MasterSecret masterSecret) throws NoSuchMessageException {
    EncryptingSmsDatabase database = DatabaseFactory.getEncryptingSmsDatabase(context);

    try {
      SmsMessageRecord    record    = database.getMessage(masterSecret, messageId);
      IncomingTextMessage message   = createIncomingTextMessage(masterSecret, record);
      long                messageId = record.getId();

      if (message.isSecureMessage()) {
        database.markAsLegacyVersion(messageId);
      } else {
        database.updateMessageBody(masterSecret, messageId, message.getMessageBody());
      }

      MessageNotifier.updateNotification(context, masterSecret);
    } catch (InvalidMessageException e) {
      Log.w(TAG, e);
      database.markAsDecryptFailed(messageId);
    }
  }

  @Override
  public boolean onShouldRetryThrowable(Exception exception) {
    return false;
  }

  @Override
  public void onCanceled() {
    // TODO
  }

  private String getAsymmetricDecryptedBody(MasterSecret masterSecret, String body)
      throws InvalidMessageException
  {
    try {
      AsymmetricMasterSecret asymmetricMasterSecret = MasterSecretUtil.getAsymmetricMasterSecret(context, masterSecret);
      AsymmetricMasterCipher asymmetricMasterCipher = new AsymmetricMasterCipher(asymmetricMasterSecret);

      return asymmetricMasterCipher.decryptBody(body);
    } catch (IOException e) {
      throw new InvalidMessageException(e);
    }
  }

  private IncomingTextMessage createIncomingTextMessage(MasterSecret masterSecret, SmsMessageRecord record)
      throws InvalidMessageException
  {
    IncomingTextMessage message = new IncomingTextMessage(record.getRecipients().getPrimaryRecipient().getNumber(),
                                                          record.getRecipientDeviceId(),
                                                          record.getDateSent(),
                                                          record.getBody().getBody(),
                                                          Optional.<TextSecureGroup>absent());

    if (record.isAsymmetricEncryption()) {
      String plaintextBody = getAsymmetricDecryptedBody(masterSecret, record.getBody().getBody());
      return new IncomingTextMessage(message, plaintextBody);
    } else {
      return new IncomingEncryptedMessage(message, message.getMessageBody());
    }
  }
}
