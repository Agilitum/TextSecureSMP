package org.thoughtcrime.SMP.crypto.SMP;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import org.thoughtcrime.SMP.crypto.MasterSecret;

/**
 * Created by ludwig on 14/07/15.
 */
public class SMPContentObserver extends ContentObserver {

	private static final String TAG = SMPContentObserver.class.getSimpleName();

	volatile boolean smpMessage = false;
	private Context context;
	private MasterSecret masterSecret;

	public SMPContentObserver(Handler handler, Context context, MasterSecret masterSecret) {
		super(handler);
		this.context = context;
		this.masterSecret = masterSecret;
	}

	@Override
	public void onChange(boolean selfChange) {
		this.onChange(selfChange, null);
	}

	@Override
	public void onChange(boolean selfChange, Uri uri){
		smpMessage = true;
		Log.d(TAG, "smpMessage: " + smpMessage);
	}

	public boolean newSMPMessage() {
		return smpMessage;
	}
/*
	public byte[] getNewSMPMessage(int extra){
		/*
		int messageCount =  DatabaseFactory.getSmsDatabase(context).getMessageCount();
		Log.d(TAG, "messageCount before: " +  messageCount);
		messageCount = messageCount + extra;
		Log.d(TAG, "messageCount after: " +  messageCount);

		byte[] msg = null;

		Log.d(TAG, "extra: " + extra);

		try {
			SmsMessageRecord smsMessageRecord = DatabaseFactory.getEncryptingSmsDatabase(context)
				.getMessage(masterSecret, extra);



			String msString = smsMessageRecord.getDisplayBody().toString();
			Log.d(TAG, "message: " +  msString);

			msg = decode(msString);
		} catch (NoSuchMessageException e) {}

		return msg;
	}

	private byte[] decode(String input) {
		byte[] byteArray = Base64.decode(input.getBytes(), 0);
		Log.d(TAG, "decode array length: " + byteArray.length);
		return byteArray;
	}
	*/
}
