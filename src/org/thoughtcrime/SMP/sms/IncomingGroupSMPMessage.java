package org.thoughtcrime.SMP.sms;

import org.whispersystems.textsecure.internal.push.PushMessageProtos;

/**
 * Created by ludwig on 09/07/15.
 */
public class IncomingGroupSMPMessage extends IncomingSMPMessage {

		private final PushMessageProtos.PushMessageContent.GroupContext groupContext;

		public IncomingGroupSMPMessage(IncomingSMPMessage base, PushMessageProtos.PushMessageContent
			.GroupContext groupContext, String body) {
			super(base, body);
			this.groupContext = groupContext;
		}

		@Override
		public IncomingGroupSMPMessage withMessageBody(String body) {
			return new IncomingGroupSMPMessage(this, groupContext, body);
		}

		@Override
		public boolean isGroup() {
			return true;
		}

		public boolean isUpdate() {
			return groupContext.getType().getNumber() == PushMessageProtos.PushMessageContent.GroupContext.Type.UPDATE_VALUE;
		}

		public boolean isQuit() {
			return groupContext.getType().getNumber() == PushMessageProtos.PushMessageContent.GroupContext.Type.QUIT_VALUE;
		}
}
