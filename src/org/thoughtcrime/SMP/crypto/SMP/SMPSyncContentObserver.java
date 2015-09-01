package org.thoughtcrime.SMP.crypto.SMP;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.VerifyIdentityActivity;
import org.thoughtcrime.SMP.recipients.Recipients;

/**
 * Created by ludwig on 24/07/15.
 */
public class SMPSyncContentObserver extends ContentObserver {

	private static final String TAG = SMPSyncContentObserver.class.getSimpleName();

	volatile boolean smpSyncMessage = false;
	private Context context;
	private Recipients recipients;

	public SMPSyncContentObserver(Handler handler, Context context, Recipients recipients) {
		super(handler);
		this.context = context;
		this.recipients = recipients;
	}

	@Override
	public void onChange(boolean selfChange) {
		this.onChange(selfChange, null);
	}

	@Override
	public void onChange(boolean selfChange, Uri uri){
		smpSyncMessage = true;
		Log.d(TAG, "smpSyncMessage: " + smpSyncMessage);

		handleSMPSessionStart(context);

		smpSyncMessage = false;
	}

	public void handleSMPSessionStart(final Context context) {
		new AlertDialogWrapper.Builder(context)
			.setTitle(R.string.ConversationActivity_smp_session_start_verification)
			.setMessage(R.string.ConversationActivity_smp_session_start_question)
			.setNegativeButton(android.R.string.cancel, null)
			.setPositiveButton(R.string.ConversationActivity_smp_session_accept, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent verifyIdentityIntent = new Intent(context, VerifyIdentityActivity.class);
					verifyIdentityIntent.putExtra("recipient", recipients.getPrimaryRecipient().getRecipientId());
					verifyIdentityIntent.putExtra("initiator", false);
					context.startActivity(verifyIdentityIntent);
				}
			}).show();
	}

	public boolean newSMPSyncMessage() {
		return smpSyncMessage;
	}
}

