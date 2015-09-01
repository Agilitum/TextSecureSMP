package org.thoughtcrime.SMP.sms;

import org.thoughtcrime.SMP.recipients.Recipients;

/**
 * Created by ludwig on 10/07/15.
 */
public class OutgoingEncryptedSMPMessage extends OutgoingSMPMessage {

	public OutgoingEncryptedSMPMessage(Recipients recipients, String body) {
		super(recipients, body);
	}

	private OutgoingEncryptedSMPMessage(OutgoingEncryptedSMPMessage base, String body) {
		super(base, body);
	}

	@Override
	public boolean isSecureMessage() {
		return true;
	}

	@Override
	public OutgoingSMPMessage withBody(String body) {
		return new OutgoingEncryptedSMPMessage(this, body);
	}
}
