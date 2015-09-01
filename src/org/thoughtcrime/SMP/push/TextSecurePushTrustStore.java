package org.thoughtcrime.SMP.push;

import android.content.Context;

import org.thoughtcrime.SMP.R;
import org.whispersystems.textsecure.api.push.TrustStore;

import java.io.InputStream;

public class TextSecurePushTrustStore implements TrustStore {

  private final Context context;

  public TextSecurePushTrustStore(Context context) {
    this.context = context.getApplicationContext();
  }

  @Override
  public InputStream getKeyStoreInputStream() {
    return context.getResources().openRawResource(R.raw.whisper);
  }

  @Override
  public String getKeyStorePassword() {
    return "whisper";
  }
}
