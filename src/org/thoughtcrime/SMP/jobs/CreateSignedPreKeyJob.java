package org.thoughtcrime.SMP.jobs;

import android.content.Context;
import android.util.Log;

import org.thoughtcrime.SMP.crypto.IdentityKeyUtil;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.crypto.PreKeyUtil;
import org.thoughtcrime.SMP.dependencies.InjectableType;
import org.thoughtcrime.SMP.util.ParcelUtil;
import org.thoughtcrime.SMP.util.TextSecurePreferences;
import org.whispersystems.jobqueue.EncryptionKeys;
import org.whispersystems.jobqueue.JobParameters;
import org.whispersystems.jobqueue.requirements.NetworkRequirement;
import org.whispersystems.libaxolotl.IdentityKeyPair;
import org.whispersystems.libaxolotl.state.SignedPreKeyRecord;
import org.whispersystems.textsecure.api.TextSecureAccountManager;
import org.whispersystems.textsecure.api.push.exceptions.PushNetworkException;

import java.io.IOException;

import javax.inject.Inject;

public class CreateSignedPreKeyJob extends ContextJob implements InjectableType {

  private static final String TAG = CreateSignedPreKeyJob.class.getSimpleName();

  @Inject transient TextSecureAccountManager accountManager;

  public CreateSignedPreKeyJob(Context context, MasterSecret masterSecret) {
    super(context, JobParameters.newBuilder()
                                .withPersistence()
                                .withRequirement(new NetworkRequirement(context))
                                .withEncryption(new EncryptionKeys(ParcelUtil.serialize(masterSecret)))
                                .withGroupId(CreateSignedPreKeyJob.class.getSimpleName())
                                .create());
  }

  @Override
  public void onAdded() {}

  @Override
  public void onRun() throws IOException {
    MasterSecret masterSecret = ParcelUtil.deserialize(getEncryptionKeys().getEncoded(), MasterSecret.CREATOR);

    if (TextSecurePreferences.isSignedPreKeyRegistered(context)) {
      Log.w(TAG, "Signed prekey already registered...");
      return;
    }

    IdentityKeyPair    identityKeyPair    = IdentityKeyUtil.getIdentityKeyPair(context, masterSecret);
    SignedPreKeyRecord signedPreKeyRecord = PreKeyUtil.generateSignedPreKey(context, masterSecret, identityKeyPair);

    accountManager.setSignedPreKey(signedPreKeyRecord);
    TextSecurePreferences.setSignedPreKeyRegistered(context, true);
  }

  @Override
  public void onCanceled() {}

  @Override
  public boolean onShouldRetry(Exception exception) {
    if (exception instanceof PushNetworkException) return true;
    return false;
  }
}
