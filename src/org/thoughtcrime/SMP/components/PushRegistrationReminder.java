package org.thoughtcrime.SMP.components;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.RegistrationActivity;
import org.thoughtcrime.SMP.util.TextSecurePreferences;
import org.thoughtcrime.SMP.crypto.MasterSecret;

public class PushRegistrationReminder extends Reminder {

  public PushRegistrationReminder(final Context context, final MasterSecret masterSecret) {
    super(R.drawable.ic_push_registration_reminder,
          R.string.reminder_header_push_title,
          R.string.reminder_header_push_text);

    final OnClickListener okListener = new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        intent.putExtra("master_secret", masterSecret);
        intent.putExtra("cancel_button", true);
        context.startActivity(intent);
      }
    };

    setOkListener(okListener);
  }

  @Override
  public boolean isDismissable() {
    return false;
  }

  public static boolean isEligible(Context context) {
    return !TextSecurePreferences.isPushRegistered(context);
  }
}
