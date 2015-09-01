package org.thoughtcrime.SMP.crypto.SMP;

import org.whispersystems.libaxolotl.util.guava.Optional;
import org.whispersystems.textsecure.api.messages.TextSecureAttachment;
import org.whispersystems.textsecure.api.messages.TextSecureGroup;
import org.whispersystems.textsecure.api.messages.TextSecureMessage;
import org.whispersystems.textsecure.api.messages.TextSecureSyncContext;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ludwig on 09/07/15.
 */
public class TextSecureSMPMessage extends TextSecureMessage {
	private final long timestamp;
	private final Optional<List<TextSecureAttachment>> attachments;
	private final Optional<String> body;
	private final Optional<TextSecureGroup> group;
	private final Optional<TextSecureSyncContext> syncContext;
	private final boolean endSession;
	private final boolean smp;
	private final boolean smpSync;

	public TextSecureSMPMessage(long timestamp, String body) {
		this(timestamp, (List)null, body);
	}

	public TextSecureSMPMessage(long timestamp, final TextSecureAttachment attachment, String body) {
		this(timestamp, (List)(new LinkedList() {
			{
				this.add(attachment);
			}
		}), body);
	}

	public TextSecureSMPMessage(long timestamp, List<TextSecureAttachment> attachments, String body) {
		this(timestamp, (TextSecureGroup)null, attachments, body);
	}

	public TextSecureSMPMessage(long timestamp, TextSecureGroup group, List<TextSecureAttachment>
		attachments, String body) {
		this(timestamp, group, attachments, body, (TextSecureSyncContext) null, false, false, false);
	}

	public TextSecureSMPMessage(long timestamp, TextSecureGroup group, List<TextSecureAttachment>
		attachments, String body, TextSecureSyncContext syncContext, boolean endSession, boolean smp,
	                            boolean smpSync) {
		super(timestamp, group, attachments, body, syncContext, endSession);
		this.timestamp = timestamp;
		this.body = Optional.fromNullable(body);
		this.group = Optional.fromNullable(group);
		this.syncContext = Optional.fromNullable(syncContext);
		this.endSession = endSession;
		this.smp = smp;
		this.smpSync = smpSync;
		if(attachments != null && !attachments.isEmpty()) {
			this.attachments = Optional.of(attachments);
		} else {
			this.attachments = Optional.absent();
		}

	}

	public static TextSecureSMPMessage.SMPBuilder newSMPBuilder() {
		return new TextSecureSMPMessage.SMPBuilder();
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public Optional<List<TextSecureAttachment>> getAttachments() {
		return this.attachments;
	}

	public Optional<String> getBody() {
		return this.body;
	}

	public Optional<TextSecureGroup> getGroupInfo() {
		return this.group;
	}

	public Optional<TextSecureSyncContext> getSyncContext() {
		return this.syncContext;
	}

	public boolean isEndSession() {
		return this.endSession;
	}

	public boolean isSMPMessage() {return this.smp;}

	public boolean isSMPSyncMessage() {
		return  this.smpSync;
	}

	public boolean isGroupUpdate() {
		return this.group.isPresent() && ((TextSecureGroup)this.group.get()).getType() != TextSecureGroup.Type.DELIVER;
	}

	public static class SMPBuilder {
		private List<TextSecureAttachment> attachments;
		private long timestamp;
		private TextSecureGroup group;
		private String body;
		private boolean endSession;
		private boolean smp;
		private boolean smpSync;
		private Optional<TextSecureSMPSyncContext> smpSyncContext;

		private SMPBuilder() {
			this.attachments = new LinkedList();
		}

		public TextSecureSMPMessage.SMPBuilder withTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder asGroupMessage(TextSecureGroup group) {
			this.group = group;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder withAttachment(TextSecureAttachment attachment) {
			this.attachments.add(attachment);
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder withAttachments(List<TextSecureAttachment> attachments) {
			this.attachments.addAll(attachments);
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder withBody(String body) {
			this.body = body;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder asEndSessionMessage() {
			this.endSession = true;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder asEndSessionMessage(boolean endSession) {
			this.endSession = endSession;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder asSMPSessionMessage() {
			this.smp = true;
			return this;
		}

		public  TextSecureSMPMessage.SMPBuilder asSMPSessionMessage(boolean smp) {
			this.smp = smp;
			return this;
		}

		public TextSecureSMPMessage.SMPBuilder asSMPSyncSessionMessage() {
			this.smp = true;
			return this;
		}

		public  TextSecureSMPMessage.SMPBuilder asSMPSyncSessionMessage(boolean smpSync) {
			this.smpSync = smpSync;
			return this;
		}


		public TextSecureSMPMessage build() {
			if(this.timestamp == 0L) {
				this.timestamp = System.currentTimeMillis();
			}

			return new TextSecureSMPMessage(this.timestamp, this.group, this.attachments, this.body,
				(TextSecureSyncContext) null, this.endSession, this.smp, this.smpSync);
		}
	}
}
