package org.thoughtcrime.SMP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.WindowManager;

import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.crypto.MasterSecretUtil;
import org.thoughtcrime.SMP.service.KeyCachingService;
import org.thoughtcrime.SMP.service.MessageRetrievalService;
import org.thoughtcrime.SMP.util.TextSecurePreferences;

import java.util.Locale;

public abstract class PassphraseRequiredActionBarActivity extends BaseActionBarActivity implements MasterSecretListener {
  private static final String TAG = PassphraseRequiredActionBarActivity.class.getSimpleName();

  public static final String LOCALE_EXTRA = "locale_extra";

  private static final int STATE_NORMAL                   = 0;
  private static final int STATE_CREATE_PASSPHRASE        = 1;
  private static final int STATE_PROMPT_PASSPHRASE        = 2;
  private static final int STATE_UPGRADE_DATABASE         = 3;
  private static final int STATE_PROMPT_PUSH_REGISTRATION = 4;

  private BroadcastReceiver clearKeyReceiver;
  private boolean           isVisible;

  @Override
  protected final void onCreate(Bundle savedInstanceState) {
    onPreCreate();
    final MasterSecret masterSecret = KeyCachingService.getMasterSecret(this);
    routeApplicationState(masterSecret);
    super.onCreate(savedInstanceState);
    if (!isFinishing()) {
      initializeClearKeyReceiver();
      onCreate(savedInstanceState, masterSecret);
    }
  }

  protected void onPreCreate() {}
  protected void onCreate(Bundle savedInstanceState, @NonNull MasterSecret masterSecret) {}

  @Override
  protected void onResume() {
    super.onResume();
    initializeScreenshotSecurity();
    KeyCachingService.registerPassphraseActivityStarted(this);
    MessageRetrievalService.registerActivityStarted(this);
    isVisible = true;
  }

  @Override
  protected void onPause() {
    super.onPause();
    KeyCachingService.registerPassphraseActivityStopped(this);
    MessageRetrievalService.registerActivityStopped(this);
    isVisible = false;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    removeClearKeyReceiver(this);
  }

  @Override
  public void onMasterSecretCleared() {
    Log.w(TAG, "onMasterSecretCleared()");
    if (isVisible) routeApplicationState(null);
    else           finish();
  }

  protected <T extends Fragment> T initFragment(@IdRes int target,
                                                @NonNull T fragment,
                                                @NonNull MasterSecret masterSecret)
  {
    return initFragment(target, fragment, masterSecret, null);
  }

  protected <T extends Fragment> T initFragment(@IdRes int target,
                                                @NonNull T fragment,
                                                @NonNull MasterSecret masterSecret,
                                                @Nullable Locale locale)
  {
    return initFragment(target, fragment, masterSecret, locale, null);
  }

  protected <T extends Fragment> T initFragment(@IdRes int target,
                                                @NonNull T fragment,
                                                @NonNull MasterSecret masterSecret,
                                                @Nullable Locale locale,
                                                @Nullable Bundle extras)
  {
    Bundle args = new Bundle();
    args.putParcelable("master_secret", masterSecret);
    args.putSerializable(LOCALE_EXTRA, locale);

    if (extras != null) {
      args.putAll(extras);
    }

    fragment.setArguments(args);
    getSupportFragmentManager().beginTransaction()
                               .replace(target, fragment)
                               .commit();
    return fragment;
  }

  private void routeApplicationState(MasterSecret masterSecret) {
    Intent intent = getIntentForState(masterSecret, getApplicationState(masterSecret));
    if (intent != null) {
      startActivity(intent);
      finish();
    }
  }

  private Intent getIntentForState(MasterSecret masterSecret, int state) {
    Log.w(TAG, "routeApplicationState(), state: " + state);

    switch (state) {
      case STATE_CREATE_PASSPHRASE:        return getCreatePassphraseIntent();
      case STATE_PROMPT_PASSPHRASE:        return getPromptPassphraseIntent();
      case STATE_UPGRADE_DATABASE:         return getUpgradeDatabaseIntent(masterSecret);
      case STATE_PROMPT_PUSH_REGISTRATION: return getPushRegistrationIntent(masterSecret);
      default:                             return null;
    }
  }

  private int getApplicationState(MasterSecret masterSecret) {
    if (!MasterSecretUtil.isPassphraseInitialized(this)) {
      return STATE_CREATE_PASSPHRASE;
    } else if (masterSecret == null) {
      return STATE_PROMPT_PASSPHRASE;
    } else if (DatabaseUpgradeActivity.isUpdate(this)) {
      return STATE_UPGRADE_DATABASE;
    } else if (!TextSecurePreferences.hasPromptedPushRegistration(this)) {
      return STATE_PROMPT_PUSH_REGISTRATION;
    } else {
      return STATE_NORMAL;
    }
  }

  private Intent getCreatePassphraseIntent() {
    return getRoutedIntent(PassphraseCreateActivity.class, getIntent(), null);
  }

  private Intent getPromptPassphraseIntent() {
    return getRoutedIntent(PassphrasePromptActivity.class, getIntent(), null);
  }

  private Intent getUpgradeDatabaseIntent(MasterSecret masterSecret) {
    return getRoutedIntent(DatabaseUpgradeActivity.class,
                           TextSecurePreferences.hasPromptedPushRegistration(this)
                               ? getConversationListIntent()
                               : getPushRegistrationIntent(masterSecret),
                           masterSecret);
  }

  private Intent getPushRegistrationIntent(MasterSecret masterSecret) {
    return getRoutedIntent(RegistrationActivity.class, getConversationListIntent(), masterSecret);
  }

  private Intent getRoutedIntent(Class<?> destination, @Nullable Intent nextIntent, @Nullable MasterSecret masterSecret) {
    final Intent intent = new Intent(this, destination);
    if (nextIntent != null)   intent.putExtra("next_intent", nextIntent);
    if (masterSecret != null) intent.putExtra("master_secret", masterSecret);
    return intent;
  }

  private Intent getConversationListIntent() {
    return new Intent(this, ConversationListActivity.class);
  }

  private void initializeScreenshotSecurity() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
        TextSecurePreferences.isScreenSecurityEnabled(this))
    {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    } else {
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }
  }

  private void initializeClearKeyReceiver() {
    Log.w(TAG, "initializeClearKeyReceiver()");
    this.clearKeyReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        Log.w(TAG, "onReceive() for clear key event");
        onMasterSecretCleared();
      }
    };

    IntentFilter filter = new IntentFilter(KeyCachingService.CLEAR_KEY_EVENT);
    registerReceiver(clearKeyReceiver, filter, KeyCachingService.KEY_PERMISSION, null);
  }

  private void removeClearKeyReceiver(Context context) {
    if (clearKeyReceiver != null) {
      context.unregisterReceiver(clearKeyReceiver);
      clearKeyReceiver = null;
    }
  }
}
