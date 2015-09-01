package org.thoughtcrime.SMP.dependencies;

import android.content.Context;

import org.thoughtcrime.SMP.BuildConfig;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.crypto.SMP.PushSMPSendJob;
import org.thoughtcrime.SMP.crypto.SMP.TextSecureSMPMessageSender;
import org.thoughtcrime.SMP.crypto.storage.TextSecureAxolotlStore;
import org.thoughtcrime.SMP.jobs.AttachmentDownloadJob;
import org.thoughtcrime.SMP.jobs.CleanPreKeysJob;
import org.thoughtcrime.SMP.jobs.CreateSignedPreKeyJob;
import org.thoughtcrime.SMP.jobs.DeliveryReceiptJob;
import org.thoughtcrime.SMP.jobs.PushGroupSendJob;
import org.thoughtcrime.SMP.jobs.PushMediaSendJob;
import org.thoughtcrime.SMP.jobs.PushNotificationReceiveJob;
import org.thoughtcrime.SMP.jobs.PushTextSendJob;
import org.thoughtcrime.SMP.jobs.RefreshPreKeysJob;
import org.thoughtcrime.SMP.push.SecurityEventListener;
import org.thoughtcrime.SMP.push.TextSecurePushTrustStore;
import org.thoughtcrime.SMP.service.MessageRetrievalService;
import org.thoughtcrime.SMP.util.TextSecurePreferences;
import org.whispersystems.libaxolotl.util.guava.Optional;
import org.whispersystems.textsecure.api.TextSecureAccountManager;
import org.whispersystems.textsecure.api.TextSecureMessageReceiver;
import org.whispersystems.textsecure.api.TextSecureMessageSender;
import org.whispersystems.textsecure.api.util.CredentialsProvider;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, injects = {CleanPreKeysJob.class,
                                     CreateSignedPreKeyJob.class,
                                     DeliveryReceiptJob.class,
                                     PushGroupSendJob.class,
                                     PushTextSendJob.class,
                                     PushSMPSendJob.class,
                                     PushMediaSendJob.class,
                                     AttachmentDownloadJob.class,
                                     RefreshPreKeysJob.class,
                                     MessageRetrievalService.class,
                                     PushNotificationReceiveJob.class})
public class TextSecureCommunicationModule {

  private final Context context;

  public TextSecureCommunicationModule(Context context) {
    this.context = context;
  }

  @Provides TextSecureAccountManager provideTextSecureAccountManager() {
    return new TextSecureAccountManager(BuildConfig.PUSH_URL,
                                        new TextSecurePushTrustStore(context),
                                        TextSecurePreferences.getLocalNumber(context),
                                        TextSecurePreferences.getPushServerPassword(context));
  }

  @Provides TextSecureMessageSenderFactory provideTextSecureMessageSenderFactory() {
    return new TextSecureMessageSenderFactory() {
      @Override
      public TextSecureMessageSender create(MasterSecret masterSecret) {
        return new TextSecureMessageSender(BuildConfig.PUSH_URL,
                                           new TextSecurePushTrustStore(context),
                                           TextSecurePreferences.getLocalNumber(context),
                                           TextSecurePreferences.getPushServerPassword(context),
                                           new TextSecureAxolotlStore(context, masterSecret),
                                           Optional.of((TextSecureMessageSender.EventListener)
                                                           new SecurityEventListener(context)));
      }
    };
  }

  @Provides TextSecureSMPMessageSenderFactory provideTextSecureSMPMessageSenderFactory() {
    return new TextSecureSMPMessageSenderFactory() {
      @Override
      public TextSecureSMPMessageSender create(MasterSecret masterSecret) {
        return new TextSecureSMPMessageSender(BuildConfig.PUSH_URL,
          new TextSecurePushTrustStore(context),
          TextSecurePreferences.getLocalNumber(context),
          TextSecurePreferences.getPushServerPassword(context),
          new TextSecureAxolotlStore(context, masterSecret),
          Optional.of((TextSecureSMPMessageSender.EventListener)
            new SecurityEventListener(context)));
      }
    };
  }

  @Provides TextSecureMessageReceiver provideTextSecureMessageReceiver() {
    return new TextSecureMessageReceiver(BuildConfig.PUSH_URL,
                                         new TextSecurePushTrustStore(context),
                                         new DynamicCredentialsProvider(context));
  }

  public static interface TextSecureMessageSenderFactory {
    public TextSecureMessageSender create(MasterSecret masterSecret);
  }

  public static interface TextSecureSMPMessageSenderFactory{
    public TextSecureSMPMessageSender create(MasterSecret masterSecret);
  }

  private static class DynamicCredentialsProvider implements CredentialsProvider {

    private final Context context;

    private DynamicCredentialsProvider(Context context) {
      this.context = context.getApplicationContext();
    }

    @Override
    public String getUser() {
      return TextSecurePreferences.getLocalNumber(context);
    }

    @Override
    public String getPassword() {
      return TextSecurePreferences.getPushServerPassword(context);
    }

    @Override
    public String getSignalingKey() {
      return TextSecurePreferences.getSignalingKey(context);
    }
  }

}
