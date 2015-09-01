package org.thoughtcrime.SMP.sms;

import org.thoughtcrime.SMP.recipients.Recipients;

/**
 * Created by ludwig on 21/07/15.
 */
public class OutgoingEncryptedSMPSyncMessage extends OutgoingSMPMessage {

	public OutgoingEncryptedSMPSyncMessage(Recipients recipients, String message) {
		super(recipients, message);
	}

	private OutgoingEncryptedSMPSyncMessage(OutgoingEncryptedSMPSyncMessage base, String body) {
		super(base, body);
	}

	@Override
	public boolean isSMPSyncMessage() {
		return true;
	}

	@Override
	public boolean isSecureMessage() {
		return true;
	}


	@Override
	public OutgoingSMPMessage withBody(String body) {
		return new OutgoingEncryptedSMPSyncMessage(this, body);
	}
}

