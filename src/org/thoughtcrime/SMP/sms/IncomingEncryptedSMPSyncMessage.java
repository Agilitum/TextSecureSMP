package org.thoughtcrime.SMP.sms;

/**
 * Created by ludwig on 23/07/15.
 */
public class IncomingEncryptedSMPSyncMessage extends IncomingSMPMessage {

		IncomingSMPMessage base;
		String newBody;

		public IncomingEncryptedSMPSyncMessage(IncomingSMPMessage base, String newBody) {
			super(base, newBody);
			this.base = base;
			this.newBody = newBody;
		}

		@Override
		public IncomingSMPMessage withMessageBody(String body) {
			return new IncomingEncryptedSMPSyncMessage(this, body);
		}

		@Override
		public boolean isSecureMessage() {
			return true;
		}

		@Override
		public boolean isSMPSyncMessage() {
			return true;
		}

}
