package org.thoughtcrime.SMP.sms;

import android.telephony.SmsMessage;

import org.whispersystems.libaxolotl.util.guava.Optional;
import org.whispersystems.textsecure.api.messages.TextSecureGroup;

/**
 * Created by ludwig on 09/07/15.
 */
public class IncomingSMPMessage extends IncomingTextMessage {


	public IncomingSMPMessage(SmsMessage message) {
		super (message);
	}

	public IncomingSMPMessage(IncomingSMPMessage base, String newBody) {
		super(base, newBody);
	}

	public IncomingSMPMessage(String sender, int senderDeviceId, long sentTimestampMillis,
	                           String encodedBody, Optional<TextSecureGroup> group)
	{
		super(sender, senderDeviceId, sentTimestampMillis, encodedBody, group);
	}

	@Override
	public IncomingSMPMessage withMessageBody(String messageBody) {
		return new IncomingSMPMessage(this, messageBody);
	}

  @Override
	public boolean isSMPMessage() {
		return true;
	}
}
