package org.thoughtcrime.SMP.sms;

/**
 * Created by ludwig on 09/07/15.
 */
public class IncomingEncryptedSMPMessage extends IncomingSMPMessage {

	IncomingSMPMessage base;
	String newBody;

	public IncomingEncryptedSMPMessage(IncomingSMPMessage base, String newBody) {
		super(base, newBody);
		this.base = base;
		this.newBody = newBody;
	}

	@Override
	public IncomingSMPMessage withMessageBody(String body) {
		return new IncomingEncryptedSMPMessage(this, body);
	}

	@Override
	public boolean isSecureMessage() {
		return true;
	}
}
