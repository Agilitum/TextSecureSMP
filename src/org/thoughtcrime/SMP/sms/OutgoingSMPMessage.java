package org.thoughtcrime.SMP.sms;

import android.util.Log;

import org.thoughtcrime.SMP.database.model.SmsMessageRecord;
import org.thoughtcrime.SMP.recipients.Recipients;

/**
 * Created by ludwig on 09/07/15.
 */
public class OutgoingSMPMessage {

	private static final String TAG = OutgoingSMPMessage.class.getSimpleName();

	private final Recipients recipients;
	private final String     message;

	public OutgoingSMPMessage(Recipients recipients, String message) {
		this.recipients = recipients;
		this.message    = message;
	}

	protected OutgoingSMPMessage(OutgoingSMPMessage base, String body) {
		this.recipients = base.getRecipients();
		this.message    = body;
	}

	public String getMessageBody() {
		return message;
	}

	public Recipients getRecipients() {
		return recipients;
	}

	public boolean isSMPSyncMessage () {
		return false;
	}

	public boolean isSecureMessage() {
		return false;
	}

	public boolean isEndSession() {
		return false;
	}

	public boolean isPreKeyBundle() {
		return false;
	}

	public  boolean isSMPMessage() {
		return true;
	}

	public OutgoingSMPMessage withBody(String body) {
		return new OutgoingSMPMessage(this, body);
	}

	public static OutgoingSMPMessage from(SmsMessageRecord record) {
		if (record.isSecure()) {
			Log.d(TAG, "OutgoingEncryptedSMPMessage");
			return new OutgoingEncryptedSMPMessage(record.getRecipients(), record.getBody().getBody());
		} else if (record.isSMPSyncMessage()) {
			Log.d(TAG, "OutgoingEncryptedSMPSyncMessage");
			return new OutgoingEncryptedSMPSyncMessage(record.getRecipients(), record.getBody().getBody());
		} else {
			return new OutgoingSMPMessage(record.getRecipients(), record.getBody().getBody());
		}
	}
}
