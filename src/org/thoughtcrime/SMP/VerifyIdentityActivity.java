/**
 * Copyright (C) 2011 Whisper Systems
 * Copyright (C) 2013 Open Whisper Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thoughtcrime.SMP;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.thoughtcrime.SMP.crypto.IdentityKeyParcelable;
import org.thoughtcrime.SMP.crypto.IdentityKeyUtil;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.crypto.SMP.SMP;
import org.thoughtcrime.SMP.crypto.SMP.SMPContentObserver;
import org.thoughtcrime.SMP.crypto.SMP.SMPException;
import org.thoughtcrime.SMP.crypto.SMP.SMPState;
import org.thoughtcrime.SMP.crypto.storage.TextSecureSessionStore;
import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.database.NoSuchMessageException;
import org.thoughtcrime.SMP.database.model.SMPMessageRecord;
import org.thoughtcrime.SMP.recipients.Recipient;
import org.thoughtcrime.SMP.recipients.RecipientFactory;
import org.thoughtcrime.SMP.recipients.Recipients;
import org.thoughtcrime.SMP.sms.MessageSender;
import org.thoughtcrime.SMP.sms.OutgoingEncryptedSMPMessage;
import org.thoughtcrime.SMP.sms.OutgoingEncryptedSMPSyncMessage;
import org.thoughtcrime.SMP.sms.OutgoingSMPMessage;
import org.thoughtcrime.SMP.util.Util;
import org.whispersystems.libaxolotl.AxolotlAddress;
import org.whispersystems.libaxolotl.IdentityKey;
import org.whispersystems.libaxolotl.InvalidMessageException;
import org.whispersystems.libaxolotl.state.SessionRecord;
import org.whispersystems.libaxolotl.state.SessionStore;
import org.whispersystems.textsecure.api.push.TextSecureAddress;

/**
 * Activity for verifying identity keys.
 *
 * @author Moxie Marlinspike modifed by Ludwig
 */
public class VerifyIdentityActivity extends KeyScanningActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

  private static final String TAG = VerifyIdentityActivity.class.getSimpleName();

  private Recipient    recipient;
  private Recipients   recipients;
  private MasterSecret masterSecret;
  private long         threadId;
  private IdentityKey  identityKey;
  private Boolean initiatorFlag = true;

  // ContentObserver
  Handler handler = new Handler(Looper.getMainLooper());
  String CONVERSATION_URI      = "content://textsecure/thread/";
  SMPContentObserver smpContentObserver;

  private TextView localIdentityFingerprint;
  private TextView remoteIdentityFingerprint;

  private LinearLayout advancedIdentity;

  // initialise colours
  private final int ORANGE = Color.rgb(255, 165, 0);
  private final int RED = Color.rgb(255, 0, 0);
  private final int BLACK = Color.rgb(0, 0, 0);
  private final int GREEEN = Color.rgb(0, 255, 0);
  private final int BLUE = Color.rgb(0, 0, 255);
  private final int BROWN = Color.rgb(153, 76, 0);
  private final int PINK = Color.rgb(255, 0, 255);
  private final int PURPLE = Color.rgb(153, 51, 255);
  private final int YELLOW = Color.rgb(255, 255, 0);

  int[] colours = {ORANGE, RED, BLACK, GREEEN, BLUE, BROWN, PINK, PURPLE, YELLOW};

  int initiatorButtonColour = 0;
  int responderButtonColour = 0;

  @Override
  protected void onCreate(Bundle state, @NonNull MasterSecret masterSecret) {
    this.masterSecret = masterSecret;

    initiatorFlag = getIntent().getExtras().getBoolean("initiator");
    Log.i(TAG, "initiator: " + initiatorFlag);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    if (initiatorFlag == true) {
      setContentView(R.layout.verify_identity_activity_initiator);
      Log.i(TAG, "setContentView: initiator = " + initiatorFlag.toString());

    } else {
      setContentView(R.layout.verify_identity_activity_listener);
      Log.i(TAG, "setContentView: initiator = " + initiatorFlag.toString());
    }

    initializeResources();
    initializeFingerprints();

    recipients = getRecipients();
    threadId = getThreadId();

    // register ContentObserver
    smpContentObserver = new SMPContentObserver(handler, getApplicationContext(), masterSecret);
    getContentResolver().registerContentObserver(Uri.parse
      (CONVERSATION_URI + threadId + "/smp"), true, smpContentObserver);

    // Button to toggle advanced settings (previous default)
    ToggleButton showAdvancedIdentity = (ToggleButton) findViewById(R.id.toggleAdvancedIdentity);
    showAdvancedIdentity.setOnCheckedChangeListener(this);
    advancedIdentity = (LinearLayout) findViewById(R.id.advancedIdentity);


    // set all buttons
    Button button0 = (Button) findViewById(R.id.Button0);
    Button button1 = (Button) findViewById(R.id.Button1);
    Button button2 = (Button) findViewById(R.id.Button2);
    Button button3 = (Button) findViewById(R.id.Button3);
    Button button4 = (Button) findViewById(R.id.Button4);
    Button button5 = (Button) findViewById(R.id.Button5);
    Button button6 = (Button) findViewById(R.id.Button6);
    Button button7 = (Button) findViewById(R.id.Button7);
    Button button8 = (Button) findViewById(R.id.Button8);

    if (initiatorFlag == true) {
      button3.setVisibility(View.INVISIBLE);

      initiatorButtonColour = getRandomColour();

      button4.setBackgroundColor(initiatorButtonColour);
      button4.setEnabled(false);

      button5.setVisibility(View.INVISIBLE);

      final Button smpStartButton = (Button) findViewById(R.id.smp_start_button);
      smpStartButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          try {
            // start SMP protocol
            Log.i(TAG, "start smpSession as initiator");
            smpSession(initiatorButtonColour, initiatorFlag);
          } catch (SMPException e) {}
        }
      });
    } else {
      button0.setBackgroundColor(ORANGE);
      button1.setBackgroundColor(GREEEN);
      button2.setBackgroundColor(BLUE);
      button3.setBackgroundColor(BROWN);
      button4.setBackgroundColor(RED);
      button5.setBackgroundColor(YELLOW);
      button6.setBackgroundColor(PURPLE);
      button7.setBackgroundColor(BLACK);
      button8.setBackgroundColor(PINK);
    }

  }
  @Override
  public void onPostCreate(Bundle savedInstanceState){
    super.onPostCreate(savedInstanceState);
    if(initiatorFlag == true) {
      SharedPreferences prefs = getApplicationContext()
        .getSharedPreferences("org.thoughtcrime.SMP.SMP", Context.MODE_PRIVATE);
      boolean authenticated = prefs.getBoolean("authenticated", false);

      Log.d(TAG, "onPostCreate() authenticated: " + prefs.getAll().toString());

      setEntityVerification(authenticated);
    }
  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    Boolean enabled = !isChecked;
    if (enabled) {
      advancedIdentity.setVisibility(View.GONE);
      findViewById(R.id.basicIdentity).setVisibility(View.VISIBLE);
      findViewById(R.id.identityScrollView).invalidate();
    } else {
      advancedIdentity.setVisibility(View.VISIBLE);
      findViewById(R.id.basicIdentity).setVisibility(View.GONE);
      findViewById(R.id.identityScrollView).invalidate();
    }
  }

  public void setVerificationProgressVisibility (boolean visible){
    if (visible == true) {
      ProgressBar verificationProgress = (ProgressBar) findViewById(R.id.verification_progress);
      verificationProgress.setVisibility(View.VISIBLE);

      TextView verificationProgressText = (TextView) findViewById(R.id.verification_progress_text);
      verificationProgressText.setText("Waiting for: " + recipient.getName());
      verificationProgressText.setVisibility(View.VISIBLE);
    } else {
      ProgressBar verificationProgress = (ProgressBar) findViewById(R.id.verification_progress);
      verificationProgress.setVisibility(View.INVISIBLE);

      TextView verificationProgressText = (TextView) findViewById(R.id.verification_progress_text);
      verificationProgressText.setVisibility(View.INVISIBLE);
    }
  }

  public void setEntityVerification(boolean verified){
    TextView verfiedEntity = (TextView) findViewById(R.id.entity_verified);
    if(verified) {
      verfiedEntity.setText("Entity " + recipient.getName() + " has been verified.");
    } else {
      verfiedEntity.setText("Entity " + recipient.getName() + " has NOT been verified.");
    }
  }

  public void setResponderResultScreen(){
    setContentView(R.layout.verify_identity_activity_initiator);

    findViewById(R.id.Button3).setVisibility(View.INVISIBLE);

    Button button4 = (Button) findViewById(R.id.Button4);
    button4.setBackgroundColor(getRandomColour());
    button4.setEnabled(false);

    findViewById(R.id.Button5).setVisibility(View.INVISIBLE);
  }

  public void setEntityVerifiedFlag(boolean verifiedBefore){
    SharedPreferences prefs = getApplicationContext()
      .getSharedPreferences("org.thoughtcrime.SMP.SMP", Context.MODE_PRIVATE);
    prefs.edit().putBoolean("authenticated", verifiedBefore).commit();
  }

  // set listeners to all buttons if not initiator
  @Override
  public void onClick(View v) {
    Button selectedButton = (Button) findViewById(v.getId());
    ColorDrawable selectedButtonColour = (ColorDrawable) selectedButton.getBackground();

    responderButtonColour = selectedButtonColour.getColor();

    try {
      Log.i(TAG, "start smpSession as receiver");
      smpSession(responderButtonColour, initiatorFlag);
    } catch (SMPException e) {}
  }

  // pick random colour for initator
  public int getRandomColour() {
    return colours[Util.getSecureRandom().nextInt(colours.length)];
  }

  // initiate the SMP session here
  public void smpSession(Integer secret, Boolean initiator) throws SMPException {
    byte[] secretByteArray;

    final SMPState a = new SMPState();
    final SMPState b = new SMPState();

    // TODO: SMP supported by opposite site?!
    //send SMP support request
    Boolean supportSMP = true;

    if (supportSMP == true) {
      Log.i(TAG, "SMP support: " + supportSMP.toString());

      //logic for the current entity Alice(step1(), step3(), step5()) or Bob(step2(), step4())
      if (initiatorFlag) {
        Log.i(TAG, "smpSession: initiatorFlag= " + initiatorFlag);

        setVerificationProgressVisibility(true);

        // set correct secret from user input + identity keys
        secretByteArray = (secret.toString()+IdentityKeyUtil.getIdentityKey(this)
          .getFingerprint()+identityKey.getFingerprint()).getBytes();

        // start the protocol & send first message
        try {
          byte[] msg1 = SMP.step1(a, secretByteArray);
          Log.i(TAG, "sendSMPSyncMessage_step1");
          sendSMPMessage(encode(msg1), true);

          //TODO: wait for response & enable to back channel if the other side refuses right now - but how?
          new Handler().postDelayed(new Runnable() {
            public void run() {
              if (!smpContentObserver.newSMPMessage()) {
                Log.w(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());
              } else if (smpContentObserver.newSMPMessage()) {
                Log.d(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());

                try {
                  byte[] msg3 = SMP.step3(a, getNewSMPMessage());
                  Log.d(TAG, "sendSMPMessage_step3");
                  sendSMPMessage(encode(msg3), false);
                } catch (InvalidMessageException | SMPException e) {
                  setEntityVerification(false);
                }
              }
            }
          }, 20000L);

          // wait for message from responder
          new Handler().postDelayed(new Runnable() {
            public void run() {
              if (!smpContentObserver.newSMPMessage()) {
                Log.d(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());
              } else if (smpContentObserver.newSMPMessage()) {
                Log.d(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());

                try {
                  Log.i(TAG, "before SMP.step5()");
                  SMP.step5(a, getNewSMPMessage());
                  if (a.smProgState == 1) {
                    // display toast or other indication for SMP success
                    setVerificationProgressVisibility(false);
                    setEntityVerification(true);

                    // TODO: move along and do Web of Trust authentication & save contacts keys
                    // set permanent marker for successful authentication
                    setEntityVerifiedFlag(true);
                  } else {
                    Log.d(TAG, "SMP.step5() NO success!");
                    setEntityVerification(false);
                    setVerificationProgressVisibility(false);
                  }
                } catch (SMPException e) {
                }
              }
            }
          }, 40000L);
        } catch (InvalidMessageException e){}
      } else {
        Log.i(TAG, "smpSession: initiatorFlag= " + initiatorFlag);

        // set correct secret from user input + identity keys
        secretByteArray = (secret.toString()+identityKey.getFingerprint()+IdentityKeyUtil.getIdentityKey(this)
          .getFingerprint()).getBytes();

        // initiate step2a after first message received
        SMP.step2a(b, getNewSMPMessage(), 123);

        // return message to initiator
        try {
          byte[] msg2 = SMP.step2b(b, secretByteArray);
          sendSMPMessage(encode(msg2), false);
        } catch (InvalidMessageException e) {}


        // wait for response from initiator
        new Handler().postDelayed(new Runnable() {
          public void run() {
            // Do delayed stuff!
            if (!smpContentObserver.newSMPMessage()) {
              Log.d(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());
            } else if (smpContentObserver.newSMPMessage()) {
              Log.d(TAG, "new SMP message: " + smpContentObserver.newSMPMessage());

              try {
                Log.d(TAG, "before SMP.step4.getNewSMPMessage");
                byte[] msg4 = SMP.step4(b, getNewSMPMessage());
                sendSMPMessage(encode(msg4), false);
                Log.d(TAG, "msg4 array.length: " + msg4.length);

                if (b.smProgState == 1) {
                  // display toast or other indication for SMP success
                  Log.d(TAG, "SMP.step4() success!");
                  // set VerifyIdentityActivity as initiator screen and show success!
                  setResponderResultScreen();
                  setVerificationProgressVisibility(false);
                  setEntityVerification(true);
                  // TODO: move along and do Web of Trust authentication & save contacts keys
                  // set permanent marker for successful authentication
                  setEntityVerifiedFlag(true);
                } else {
                  Log.d(TAG, "SMP.step4() NO success!");
                  setResponderResultScreen();
                  setEntityVerification(false);
                  setVerificationProgressVisibility(false);
                }
              } catch (InvalidMessageException | SMPException e) {
              }
            }
          }
        }, 20000L);
      }
    } else {
      Toast.makeText(getApplicationContext(), "SMP not supported. Please use advanced option to " +
        "verify identities.", Toast.LENGTH_LONG).show();

      // automatically press advancedIdentity button after short time
      try {
        wait(2000);
        findViewById(R.id.toggleAdvancedIdentity).performClick();
      } catch (InterruptedException e) {}
    }
  }

  private String encode(byte[] input) {
    String byteArrayString = new String(Base64.encode(input, 0));
    return byteArrayString;
  }

  private byte[] decode(String input) {
    byte[] byteArray = Base64.decode(input.getBytes(), 0);
    return byteArray;
  }

  private byte[] getNewSMPMessage(){
    // obtain correct messageId from SharedPreferences
    SharedPreferences prefs = getApplicationContext()
      .getSharedPreferences("org.thoughtcrime.SMP.SMP", Context.MODE_PRIVATE);
    int messageId = prefs.getInt("messageId", 0);

    byte[] msg = null;

    try {
      SMPMessageRecord smpMessageRecord = DatabaseFactory
        .getEncryptingSMPDatabase(getApplicationContext())
        .getMessage(masterSecret, messageId);

      String msString = smpMessageRecord.getDisplayBody().toString();

      msg = decode(msString);
    } catch (NoSuchMessageException e) {}
    return msg;
  }

  private void sendSMPMessage(String processMessage, boolean sync)
    throws InvalidMessageException
  {
    final Context context = getApplicationContext();

    if (!sync) {
      OutgoingSMPMessage message;
      message = new OutgoingEncryptedSMPMessage(recipients, processMessage);

      new AsyncTask<OutgoingSMPMessage, Void, Long>() {
        @Override
        protected Long doInBackground(OutgoingSMPMessage... messages) {
          return MessageSender.send(context, masterSecret, messages[0], threadId);
        }
      }.execute(message);
    } else {
      OutgoingSMPMessage message;
      message = new OutgoingEncryptedSMPSyncMessage(recipients, processMessage);

      new AsyncTask<OutgoingSMPMessage, Void, Long>() {
        @Override
        protected Long doInBackground(OutgoingSMPMessage... messages) {
          return MessageSender.send(context, masterSecret, messages[0], threadId);
        }
      }.execute(message);
    }
  }

    private Long getThreadId (){
    threadId = DatabaseFactory.getThreadDatabase(getApplicationContext()).getThreadIdFor(recipients);
    return threadId;
  }

  private Recipients getRecipients() {
    // get the single recipient for the SMP message
    recipients = RecipientFactory.getRecipientsFor(this, recipient, true);
    return recipients;
  }

  @Override
  public void onResume() {
    super.onResume();
    getSupportActionBar().setTitle(R.string.AndroidManifest__verify_identity);
  }

  @Override
  public void onPause() {
    super.onPause();

    getContentResolver().unregisterContentObserver(smpContentObserver);
  }

  private void initializeLocalIdentityKey() {
    if (!IdentityKeyUtil.hasIdentityKey(this)) {
      localIdentityFingerprint.setText(R.string.VerifyIdentityActivity_you_do_not_have_an_identity_key);
      return;
    }

    localIdentityFingerprint.setText(IdentityKeyUtil.getIdentityKey(this).getFingerprint());
  }

  private void initializeRemoteIdentityKey() {
    IdentityKeyParcelable identityKeyParcelable = getIntent().getParcelableExtra("remote_identity");
    identityKey = null;

    if (identityKeyParcelable != null) {
      identityKey = identityKeyParcelable.get();
    }

    if (identityKey == null) {
      identityKey = getRemoteIdentityKey(masterSecret, recipient);
    }

    if (identityKey == null) {
      remoteIdentityFingerprint.setText(R.string.VerifyIdentityActivity_recipient_has_no_identity_key);
    } else {
      remoteIdentityFingerprint.setText(identityKey.getFingerprint());
    }
  }

  private void initializeFingerprints() {
    initializeLocalIdentityKey();
    initializeRemoteIdentityKey();
  }

  private void initializeResources() {
    this.localIdentityFingerprint  = (TextView)findViewById(R.id.you_read);
    this.remoteIdentityFingerprint = (TextView)findViewById(R.id.friend_reads);
    this.recipient                 = RecipientFactory.getRecipientForId(this, this.getIntent().getLongExtra("recipient", -1), true);
  }

  @Override
  protected void initiateDisplay() {
    if (!IdentityKeyUtil.hasIdentityKey(this)) {
      Toast.makeText(this,
        R.string.VerifyIdentityActivity_you_don_t_have_an_identity_key_exclamation,
        Toast.LENGTH_LONG).show();
      return;
    }

    super.initiateDisplay();
  }

  @Override
  protected void initiateScan() {
    IdentityKey identityKey = getRemoteIdentityKey(masterSecret, recipient);

    if (identityKey == null) {
      Toast.makeText(this, R.string.VerifyIdentityActivity_recipient_has_no_identity_key_exclamation,
        Toast.LENGTH_LONG).show();
    } else {
      super.initiateScan();
    }
  }

  @Override
  protected String getScanString() {
    return getString(R.string.VerifyIdentityActivity_scan_their_key_to_compare);
  }

  @Override
  protected String getDisplayString() {
    return getString(R.string.VerifyIdentityActivity_get_my_key_scanned);
  }

  @Override
  protected IdentityKey getIdentityKeyToCompare() {
    return getRemoteIdentityKey(masterSecret, recipient);
  }

  @Override
  protected IdentityKey getIdentityKeyToDisplay() {
    return IdentityKeyUtil.getIdentityKey(this);
  }

  @Override
  protected String getNotVerifiedMessage() {
    return getString(R.string.VerifyIdentityActivity_warning_the_scanned_key_does_not_match_please_check_the_fingerprint_text_carefully);
  }

  @Override
  protected String getNotVerifiedTitle() {
    return getString(R.string.VerifyIdentityActivity_not_verified_exclamation);
  }

  @Override
  protected String getVerifiedMessage() {
    return getString(R.string.VerifyIdentityActivity_their_key_is_correct_it_is_also_necessary_to_verify_your_key_with_them_as_well);
  }

  @Override
  protected String getVerifiedTitle() {
    return getString(R.string.VerifyIdentityActivity_verified_exclamation);
  }

  private IdentityKey getRemoteIdentityKey(MasterSecret masterSecret, Recipient recipient) {
    SessionStore   sessionStore   = new TextSecureSessionStore(this, masterSecret);
    AxolotlAddress axolotlAddress = new AxolotlAddress(recipient.getNumber(), TextSecureAddress.DEFAULT_DEVICE_ID);
    SessionRecord  record         = sessionStore.loadSession(axolotlAddress);

    if (record == null) {
      return null;
    }

    return record.getSessionState().getRemoteIdentityKey();
  }
}
