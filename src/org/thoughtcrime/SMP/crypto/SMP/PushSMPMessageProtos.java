package org.thoughtcrime.SMP.crypto.SMP;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.UnmodifiableLazyStringList;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ludwig on 23/07/15.
 */
public class PushSMPMessageProtos {

	private static Descriptors.Descriptor internal_static_textsecure_IncomingPushMessageSignal_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_textsecure_IncomingPushMessageSignal_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_textsecure_PushSMPMessageContent_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_textsecure_PushSMPMessageContent_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_textsecure_PushSMPMessageContent_GroupContext_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_fieldAccessorTable;
	private static Descriptors.FileDescriptor descriptor;

	private PushSMPMessageProtos() {
	}

	public static void registerAllExtensions(ExtensionRegistry registry) {
	}

	public static Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}

	static {
		String[] descriptorData = new String[]{"\n\u001fIncomingPushMessageSignal.proto\u0012\ntextsecure\"\u0085\u0002\n\u0019IncomingPushMessageSignal\u00128\n\u0004type\u0018\u0001 \u0001(\u000e2*.textsecure.IncomingPushMessageSignal.Type\u0012\u000e\n\u0006source\u0018\u0002 \u0001(\t\u0012\u0014\n\fsourceDevice\u0018\u0007 \u0001(\r\u0012\r\n\u0005relay\u0018\u0003 \u0001(\t\u0012\u0011\n\ttimestamp\u0018\u0005 \u0001(\u0004\u0012\u000f\n\u0007message\u0018\u0006 \u0001(\f\"U\n\u0004Type\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000e\n\nCIPHERTEXT\u0010\u0001\u0012\u0010\n\fKEY_EXCHANGE\u0010\u0002\u0012\u0011\n\rPREKEY_BUNDLE\u0010\u0003\u0012\u000b\n\u0007RECEIPT\u0010\u0005\"\u0086\u0005\n\u0012PushSMPMessageContent\u0012\f\n\u0004body\u0018\u0001 \u0001(\t\u0012E\n\u000battachments\u0018\u0002 \u0003(\u000b20.textsecure.PushSMPMessageContent.", "AttachmentPointer\u0012:\n\u0005group\u0018\u0003 \u0001(\u000b2+.textsecure.PushSMPMessageContent.GroupContext\u0012\r\n\u0005flags\u0018\u0004 \u0001(\r\u0012?\n\u0004sync\u0018\u0005 \u0001(\u000b21.textsecure.PushSMPMessageContent.SyncMessageContext\u001aA\n\u0011AttachmentPointer\u0012\n\n\u0002id\u0018\u0001 \u0001(\u0006\u0012\u0013\n\u000bcontentType\u0018\u0002 \u0001(\t\u0012\u000b\n\u0003key\u0018\u0003 \u0001(\f\u001aó\u0001\n\fGroupContext\u0012\n\n\u0002id\u0018\u0001 \u0001(\f\u0012>\n\u0004type\u0018\u0002 \u0001(\u000e20.textsecure.PushSMPMessageContent.GroupContext.Type\u0012\f\n\u0004name\u0018\u0003 \u0001(\t\u0012\u000f\n\u0007members\u0018\u0004 \u0003(\t\u0012@\n\u0006avatar\u0018\u0005 \u0001(\u000b20.textsecure.PushSMPMessageContent.Att", "achmentPointer\"6\n\u0004Type\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\n\n\u0006UPDATE\u0010\u0001\u0012\u000b\n\u0007DELIVER\u0010\u0002\u0012\b\n\u0004QUIT\u0010\u0003\u001a<\n\u0012SyncMessageContext\u0012\u0013\n\u000bdestination\u0018\u0001 \u0001(\t\u0012\u0011\n\ttimestamp\u0018\u0002 \u0001(\u0004\"\u0018\n\u0005Flags\u0012\u000f\n\u000bEND_SESSION\u0010\u0001B@\n+org.whispersystems.textsecure.internal.pushB\u0011PushSMPMessageProtos"};
		Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
			public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
				PushSMPMessageProtos.descriptor = root;
				PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_descriptor = (Descriptors.Descriptor)PushSMPMessageProtos.descriptor.getMessageTypes().get(0);
				PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_descriptor, new String[]{"Type", "Source", "SourceDevice", "Relay", "Timestamp", "Message"});
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor = (Descriptors.Descriptor)PushSMPMessageProtos.descriptor.getMessageTypes().get(1);
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor, new String[]{"Body", "Attachments", "Group", "Flags", "Sync"});
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor = (Descriptors.Descriptor)PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor.getNestedTypes().get(0);
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor, new String[]{"Id", "ContentType", "Key"});
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor = (Descriptors.Descriptor)PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor.getNestedTypes().get(1);
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor, new String[]{"Id", "Type", "Name", "Members", "Avatar"});
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor = (Descriptors.Descriptor)PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor.getNestedTypes().get(2);
				PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor, new String[]{"Destination", "Timestamp"});
				return null;
			}
		};
		Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
	}

	public static final class PushSMPMessageContent extends GeneratedMessage implements
		PushSMPMessageProtos.PushSMPMessageContentOrBuilder {
		private static final PushSMPMessageProtos.PushSMPMessageContent defaultInstance = new PushSMPMessageProtos.PushSMPMessageContent(true);
		private final UnknownFieldSet unknownFields;
		public static Parser<PushSMPMessageContent> PARSER = new AbstractParser() {
			public PushSMPMessageProtos.PushSMPMessageContent parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return new PushSMPMessageProtos.PushSMPMessageContent(input, extensionRegistry);
			}
		};
		private int bitField0_;
		public static final int BODY_FIELD_NUMBER = 1;
		private Object body_;
		public static final int ATTACHMENTS_FIELD_NUMBER = 2;
		private List<AttachmentPointer> attachments_;
		public static final int GROUP_FIELD_NUMBER = 3;
		private PushSMPMessageProtos.PushSMPMessageContent.GroupContext group_;
		public static final int FLAGS_FIELD_NUMBER = 4;
		private int flags_;
		public static final int SYNC_FIELD_NUMBER = 5;
		private PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext sync_;
		private byte memoizedIsInitialized;
		private int memoizedSerializedSize;
		private static final long serialVersionUID = 0L;

		private PushSMPMessageContent(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
			super(builder);
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.unknownFields = builder.getUnknownFields();
		}

		private PushSMPMessageContent(boolean noInit) {
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.unknownFields = UnknownFieldSet.getDefaultInstance();
		}

		public static PushSMPMessageProtos.PushSMPMessageContent getDefaultInstance() {
			return defaultInstance;
		}

		public PushSMPMessageProtos.PushSMPMessageContent getDefaultInstanceForType() {
			return defaultInstance;
		}

		public final UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private PushSMPMessageContent(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.initFields();
			int mutable_bitField0_ = 0;
			com.google.protobuf.UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

			try {
				boolean e = false;

				while(!e) {
					int tag = input.readTag();
					switch(tag) {
						case 0:
							e = true;
							break;
						case 10:
							this.bitField0_ |= 1;
							this.body_ = input.readBytes();
							break;
						case 18:
							if((mutable_bitField0_ & 2) != 2) {
								this.attachments_ = new ArrayList();
								mutable_bitField0_ |= 2;
							}

							this.attachments_.add(input.readMessage(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.PARSER, extensionRegistry));
							break;
						case 26:
							PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder subBuilder1 = null;
							if((this.bitField0_ & 2) == 2) {
								subBuilder1 = this.group_.toBuilder();
							}

							this.group_ = (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)input.readMessage(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.PARSER, extensionRegistry);
							if(subBuilder1 != null) {
								subBuilder1.mergeFrom(this.group_);
								this.group_ = subBuilder1.buildPartial();
							}

							this.bitField0_ |= 2;
							break;
						case 32:
							this.bitField0_ |= 4;
							this.flags_ = input.readUInt32();
							break;
						case 42:
							PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder subBuilder = null;
							if((this.bitField0_ & 8) == 8) {
								subBuilder = this.sync_.toBuilder();
							}

							this.sync_ = (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)input.readMessage(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.PARSER, extensionRegistry);
							if(subBuilder != null) {
								subBuilder.mergeFrom(this.sync_);
								this.sync_ = subBuilder.buildPartial();
							}

							this.bitField0_ |= 8;
							break;
						default:
							if(!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
								e = true;
							}
					}
				}
			} catch (InvalidProtocolBufferException var12) {
				throw var12.setUnfinishedMessage(this);
			} catch (IOException var13) {
				throw (new InvalidProtocolBufferException(var13.getMessage())).setUnfinishedMessage(this);
			} finally {
				if((mutable_bitField0_ & 2) == 2) {
					this.attachments_ = Collections.unmodifiableList(this.attachments_);
				}

				this.unknownFields = unknownFields.build();
				this.makeExtensionsImmutable();
			}

		}

		public static final Descriptors.Descriptor getDescriptor() {
			return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor;
		}

		protected FieldAccessorTable internalGetFieldAccessorTable() {
			return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.class, PushSMPMessageProtos.PushSMPMessageContent.Builder.class);
		}

		public Parser<PushSMPMessageProtos.PushSMPMessageContent> getParserForType() {
			return PARSER;
		}

		public boolean hasBody() {
			return (this.bitField0_ & 1) == 1;
		}

		public String getBody() {
			Object ref = this.body_;
			if(ref instanceof String) {
				return (String)ref;
			} else {
				ByteString bs = (ByteString)ref;
				String s = bs.toStringUtf8();
				if(bs.isValidUtf8()) {
					this.body_ = s;
				}

				return s;
			}
		}

		public ByteString getBodyBytes() {
			Object ref = this.body_;
			if(ref instanceof String) {
				ByteString b = ByteString.copyFromUtf8((String)ref);
				this.body_ = b;
				return b;
			} else {
				return (ByteString)ref;
			}
		}

		public List<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> getAttachmentsList() {
			return this.attachments_;
		}

		public List<? extends PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> getAttachmentsOrBuilderList() {
			return this.attachments_;
		}

		public int getAttachmentsCount() {
			return this.attachments_.size();
		}

		public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAttachments(int index) {
			return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)this.attachments_.get(index);
		}

		public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAttachmentsOrBuilder(int index) {
			return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder)this.attachments_.get(index);
		}

		public boolean hasGroup() {
			return (this.bitField0_ & 2) == 2;
		}

		public PushSMPMessageProtos.PushSMPMessageContent.GroupContext getGroup() {
			return this.group_;
		}

		public PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder getGroupOrBuilder() {
			return this.group_;
		}

		public boolean hasFlags() {
			return (this.bitField0_ & 4) == 4;
		}

		public int getFlags() {
			return this.flags_;
		}

		public boolean hasSync() {
			return (this.bitField0_ & 8) == 8;
		}

		public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getSync() {
			return this.sync_;
		}

		public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder getSyncOrBuilder() {
			return this.sync_;
		}

		private void initFields() {
			this.body_ = "";
			this.attachments_ = Collections.emptyList();
			this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
			this.flags_ = 0;
			this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
		}

		public final boolean isInitialized() {
			byte isInitialized = this.memoizedIsInitialized;
			if(isInitialized != -1) {
				return isInitialized == 1;
			} else {
				this.memoizedIsInitialized = 1;
				return true;
			}
		}

		public void writeTo(CodedOutputStream output) throws IOException {
			this.getSerializedSize();
			if((this.bitField0_ & 1) == 1) {
				output.writeBytes(1, this.getBodyBytes());
			}

			for(int i = 0; i < this.attachments_.size(); ++i) {
				output.writeMessage(2, (MessageLite)this.attachments_.get(i));
			}

			if((this.bitField0_ & 2) == 2) {
				output.writeMessage(3, this.group_);
			}

			if((this.bitField0_ & 4) == 4) {
				output.writeUInt32(4, this.flags_);
			}

			if((this.bitField0_ & 8) == 8) {
				output.writeMessage(5, this.sync_);
			}

			this.getUnknownFields().writeTo(output);
		}

		public int getSerializedSize() {
			int size = this.memoizedSerializedSize;
			if(size != -1) {
				return size;
			} else {
				size = 0;
				if((this.bitField0_ & 1) == 1) {
					size += CodedOutputStream.computeBytesSize(1, this.getBodyBytes());
				}

				for(int i = 0; i < this.attachments_.size(); ++i) {
					size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.attachments_.get(i));
				}

				if((this.bitField0_ & 2) == 2) {
					size += CodedOutputStream.computeMessageSize(3, this.group_);
				}

				if((this.bitField0_ & 4) == 4) {
					size += CodedOutputStream.computeUInt32Size(4, this.flags_);
				}

				if((this.bitField0_ & 8) == 8) {
					size += CodedOutputStream.computeMessageSize(5, this.sync_);
				}

				size += this.getUnknownFields().getSerializedSize();
				this.memoizedSerializedSize = size;
				return size;
			}
		}

		protected Object writeReplace() throws ObjectStreamException {
			return super.writeReplace();
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(ByteString data) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(data);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(data, extensionRegistry);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(byte[] data) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(data);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(data, extensionRegistry);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(InputStream input) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(input);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseDelimitedFrom(InputStream input) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseDelimitedFrom(input);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(CodedInputStream input) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(input);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.PushSMPMessageContent)PARSER.parseFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.PushSMPMessageContent.Builder newBuilder() {
			return PushSMPMessageProtos.PushSMPMessageContent.Builder.create();
		}

		public PushSMPMessageProtos.PushSMPMessageContent.Builder newBuilderForType() {
			return newBuilder();
		}

		public static PushSMPMessageProtos.PushSMPMessageContent.Builder newBuilder(PushSMPMessageProtos.PushSMPMessageContent prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public PushSMPMessageProtos.PushSMPMessageContent.Builder toBuilder() {
			return newBuilder(this);
		}

		protected PushSMPMessageProtos.PushSMPMessageContent.Builder newBuilderForType(BuilderParent parent) {
			PushSMPMessageProtos.PushSMPMessageContent.Builder builder = new PushSMPMessageProtos.PushSMPMessageContent.Builder(parent);
			return builder;
		}

		static {
			defaultInstance.initFields();
		}

		public static final class Builder extends com.google.protobuf.GeneratedMessage
			.Builder<PushSMPMessageProtos.PushSMPMessageContent.Builder> implements
			PushSMPMessageProtos.PushSMPMessageContentOrBuilder {
			private int bitField0_;
			private Object body_;
			private List<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> attachments_;
			private RepeatedFieldBuilder<AttachmentPointer, AttachmentPointer.Builder, AttachmentPointerOrBuilder> attachmentsBuilder_;
			private PushSMPMessageProtos.PushSMPMessageContent.GroupContext group_;
			private SingleFieldBuilder<GroupContext, GroupContext.Builder, GroupContextOrBuilder> groupBuilder_;
			private int flags_;
			private PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext sync_;
			private SingleFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext, PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder, PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder> syncBuilder_;

			public static final Descriptors.Descriptor getDescriptor() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor;
			}

			protected FieldAccessorTable internalGetFieldAccessorTable() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.class, PushSMPMessageProtos.PushSMPMessageContent.Builder.class);
			}

			private Builder() {
				this.body_ = "";
				this.attachments_ = Collections.emptyList();
				this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
				this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
				this.maybeForceBuilderInitialization();
			}

			private Builder(BuilderParent parent) {
				super(parent);
				this.body_ = "";
				this.attachments_ = Collections.emptyList();
				this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
				this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
				this.maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if(PushSMPMessageProtos.PushSMPMessageContent.alwaysUseFieldBuilders) {
					this.getAttachmentsFieldBuilder();
					this.getGroupFieldBuilder();
					this.getSyncFieldBuilder();
				}

			}

			private static PushSMPMessageProtos.PushSMPMessageContent.Builder create() {
				return new PushSMPMessageProtos.PushSMPMessageContent.Builder();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clear() {
				super.clear();
				this.body_ = "";
				this.bitField0_ &= -2;
				if(this.attachmentsBuilder_ == null) {
					this.attachments_ = Collections.emptyList();
					this.bitField0_ &= -3;
				} else {
					this.attachmentsBuilder_.clear();
				}

				if(this.groupBuilder_ == null) {
					this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
				} else {
					this.groupBuilder_.clear();
				}

				this.bitField0_ &= -5;
				this.flags_ = 0;
				this.bitField0_ &= -9;
				if(this.syncBuilder_ == null) {
					this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
				} else {
					this.syncBuilder_.clear();
				}

				this.bitField0_ &= -17;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clone() {
				return create().mergeFrom(this.buildPartial());
			}

			public Descriptors.Descriptor getDescriptorForType() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_descriptor;
			}

			public PushSMPMessageProtos.PushSMPMessageContent getDefaultInstanceForType() {
				return PushSMPMessageProtos.PushSMPMessageContent.defaultInstance;
			}

			public PushSMPMessageProtos.PushSMPMessageContent build() {
				PushSMPMessageProtos.PushSMPMessageContent result = this.buildPartial();
				if(!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				} else {
					return result;
				}
			}

			public PushSMPMessageProtos.PushSMPMessageContent buildPartial() {
				PushSMPMessageProtos.PushSMPMessageContent result = new PushSMPMessageProtos.PushSMPMessageContent(this);
				int from_bitField0_ = this.bitField0_;
				int to_bitField0_ = 0;
				if((from_bitField0_ & 1) == 1) {
					to_bitField0_ |= 1;
				}

				result.body_ = this.body_;
				if(this.attachmentsBuilder_ == null) {
					if((this.bitField0_ & 2) == 2) {
						this.attachments_ = Collections.unmodifiableList(this.attachments_);
						this.bitField0_ &= -3;
					}

					result.attachments_ = this.attachments_;
				} else {
					result.attachments_ = this.attachmentsBuilder_.build();
				}

				if((from_bitField0_ & 4) == 4) {
					to_bitField0_ |= 2;
				}

				if(this.groupBuilder_ == null) {
					result.group_ = this.group_;
				} else {
					result.group_ = (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)this.groupBuilder_.build();
				}

				if((from_bitField0_ & 8) == 8) {
					to_bitField0_ |= 4;
				}

				result.flags_ = this.flags_;
				if((from_bitField0_ & 16) == 16) {
					to_bitField0_ |= 8;
				}

				if(this.syncBuilder_ == null) {
					result.sync_ = this.sync_;
				} else {
					result.sync_ = (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)this.syncBuilder_.build();
				}

				result.bitField0_ = to_bitField0_;
				this.onBuilt();
				return result;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder mergeFrom(Message other) {
				if(other instanceof PushSMPMessageProtos.PushSMPMessageContent) {
					return this.mergeFrom((PushSMPMessageProtos.PushSMPMessageContent)other);
				} else {
					super.mergeFrom(other);
					return this;
				}
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder mergeFrom(PushSMPMessageProtos.PushSMPMessageContent other) {
				if(other == PushSMPMessageProtos.PushSMPMessageContent.defaultInstance) {
					return this;
				} else {
					if(other.hasBody()) {
						this.bitField0_ |= 1;
						this.body_ = other.body_;
						this.onChanged();
					}

					if(this.attachmentsBuilder_ == null) {
						if(!other.attachments_.isEmpty()) {
							if(this.attachments_.isEmpty()) {
								this.attachments_ = other.attachments_;
								this.bitField0_ &= -3;
							} else {
								this.ensureAttachmentsIsMutable();
								this.attachments_.addAll(other.attachments_);
							}

							this.onChanged();
						}
					} else if(!other.attachments_.isEmpty()) {
						if(this.attachmentsBuilder_.isEmpty()) {
							this.attachmentsBuilder_.dispose();
							this.attachmentsBuilder_ = null;
							this.attachments_ = other.attachments_;
							this.bitField0_ &= -3;
							this.attachmentsBuilder_ = PushSMPMessageProtos.PushSMPMessageContent.alwaysUseFieldBuilders?this.getAttachmentsFieldBuilder():null;
						} else {
							this.attachmentsBuilder_.addAllMessages(other.attachments_);
						}
					}

					if(other.hasGroup()) {
						this.mergeGroup(other.getGroup());
					}

					if(other.hasFlags()) {
						this.setFlags(other.getFlags());
					}

					if(other.hasSync()) {
						this.mergeSync(other.getSync());
					}

					this.mergeUnknownFields(other.getUnknownFields());
					return this;
				}
			}

			public final boolean isInitialized() {
				return true;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				PushSMPMessageProtos.PushSMPMessageContent parsedMessage = null;

				try {
					parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent)PushSMPMessageProtos.PushSMPMessageContent.PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (InvalidProtocolBufferException var8) {
					parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent)var8.getUnfinishedMessage();
					throw var8;
				} finally {
					if(parsedMessage != null) {
						this.mergeFrom(parsedMessage);
					}

				}

				return this;
			}

			public boolean hasBody() {
				return (this.bitField0_ & 1) == 1;
			}

			public String getBody() {
				Object ref = this.body_;
				if(!(ref instanceof String)) {
					String s = ((ByteString)ref).toStringUtf8();
					this.body_ = s;
					return s;
				} else {
					return (String)ref;
				}
			}

			public ByteString getBodyBytes() {
				Object ref = this.body_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.body_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setBody(String value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 1;
					this.body_ = value;
					this.onChanged();
					return this;
				}
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clearBody() {
				this.bitField0_ &= -2;
				this.body_ = PushSMPMessageProtos.PushSMPMessageContent.defaultInstance.getBody();
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setBodyBytes(ByteString value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 1;
					this.body_ = value;
					this.onChanged();
					return this;
				}
			}

			private void ensureAttachmentsIsMutable() {
				if((this.bitField0_ & 2) != 2) {
					this.attachments_ = new ArrayList(this.attachments_);
					this.bitField0_ |= 2;
				}

			}

			public List<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> getAttachmentsList() {
				return this.attachmentsBuilder_ == null?Collections.unmodifiableList(this.attachments_):this.attachmentsBuilder_.getMessageList();
			}

			public int getAttachmentsCount() {
				return this.attachmentsBuilder_ == null?this.attachments_.size():this.attachmentsBuilder_.getCount();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAttachments(int index) {
				return this.attachmentsBuilder_ == null?(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)this.attachments_.get(index):(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)this.attachmentsBuilder_.getMessage(index);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setAttachments(int index, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer value) {
				if(this.attachmentsBuilder_ == null) {
					if(value == null) {
						throw new NullPointerException();
					}

					this.ensureAttachmentsIsMutable();
					this.attachments_.set(index, value);
					this.onChanged();
				} else {
					this.attachmentsBuilder_.setMessage(index, value);
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setAttachments(int index, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder builderForValue) {
				if(this.attachmentsBuilder_ == null) {
					this.ensureAttachmentsIsMutable();
					this.attachments_.set(index, builderForValue.build());
					this.onChanged();
				} else {
					this.attachmentsBuilder_.setMessage(index, builderForValue.build());
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder addAttachments(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer value) {
				if(this.attachmentsBuilder_ == null) {
					if(value == null) {
						throw new NullPointerException();
					}

					this.ensureAttachmentsIsMutable();
					this.attachments_.add(value);
					this.onChanged();
				} else {
					this.attachmentsBuilder_.addMessage(value);
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder addAttachments(int index, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer value) {
				if(this.attachmentsBuilder_ == null) {
					if(value == null) {
						throw new NullPointerException();
					}

					this.ensureAttachmentsIsMutable();
					this.attachments_.add(index, value);
					this.onChanged();
				} else {
					this.attachmentsBuilder_.addMessage(index, value);
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder addAttachments(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder builderForValue) {
				if(this.attachmentsBuilder_ == null) {
					this.ensureAttachmentsIsMutable();
					this.attachments_.add(builderForValue.build());
					this.onChanged();
				} else {
					this.attachmentsBuilder_.addMessage(builderForValue.build());
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder addAttachments(int index, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder builderForValue) {
				if(this.attachmentsBuilder_ == null) {
					this.ensureAttachmentsIsMutable();
					this.attachments_.add(index, builderForValue.build());
					this.onChanged();
				} else {
					this.attachmentsBuilder_.addMessage(index, builderForValue.build());
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder addAllAttachments(Iterable<? extends PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> values) {
				if(this.attachmentsBuilder_ == null) {
					this.ensureAttachmentsIsMutable();
					com.google.protobuf.GeneratedMessage.Builder.addAll(values, this.attachments_);
					this.onChanged();
				} else {
					this.attachmentsBuilder_.addAllMessages(values);
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clearAttachments() {
				if(this.attachmentsBuilder_ == null) {
					this.attachments_ = Collections.emptyList();
					this.bitField0_ &= -3;
					this.onChanged();
				} else {
					this.attachmentsBuilder_.clear();
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder removeAttachments(int index) {
				if(this.attachmentsBuilder_ == null) {
					this.ensureAttachmentsIsMutable();
					this.attachments_.remove(index);
					this.onChanged();
				} else {
					this.attachmentsBuilder_.remove(index);
				}

				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder getAttachmentsBuilder(int index) {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder)this.getAttachmentsFieldBuilder().getBuilder(index);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAttachmentsOrBuilder(int index) {
				return this.attachmentsBuilder_ == null?(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder)this.attachments_.get(index):(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder)this.attachmentsBuilder_.getMessageOrBuilder(index);
			}

			public List<? extends PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> getAttachmentsOrBuilderList() {
				return this.attachmentsBuilder_ != null?this.attachmentsBuilder_.getMessageOrBuilderList():Collections.unmodifiableList(this.attachments_);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder addAttachmentsBuilder() {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder)this.getAttachmentsFieldBuilder().addBuilder(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder addAttachmentsBuilder(int index) {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder)this.getAttachmentsFieldBuilder().addBuilder(index, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance);
			}

			public List<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder> getAttachmentsBuilderList() {
				return this.getAttachmentsFieldBuilder().getBuilderList();
			}

			private RepeatedFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> getAttachmentsFieldBuilder() {
				if(this.attachmentsBuilder_ == null) {
					this.attachmentsBuilder_ = new RepeatedFieldBuilder(this.attachments_, (this.bitField0_ & 2) == 2, this.getParentForChildren(), this.isClean());
					this.attachments_ = null;
				}

				return this.attachmentsBuilder_;
			}

			public boolean hasGroup() {
				return (this.bitField0_ & 4) == 4;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext getGroup() {
				return this.groupBuilder_ == null?this.group_:(PushSMPMessageProtos.PushSMPMessageContent.GroupContext)this.groupBuilder_.getMessage();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setGroup(PushSMPMessageProtos.PushSMPMessageContent.GroupContext value) {
				if(this.groupBuilder_ == null) {
					if(value == null) {
						throw new NullPointerException();
					}

					this.group_ = value;
					this.onChanged();
				} else {
					this.groupBuilder_.setMessage(value);
				}

				this.bitField0_ |= 4;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setGroup(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder builderForValue) {
				if(this.groupBuilder_ == null) {
					this.group_ = builderForValue.build();
					this.onChanged();
				} else {
					this.groupBuilder_.setMessage(builderForValue.build());
				}

				this.bitField0_ |= 4;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder mergeGroup(PushSMPMessageProtos.PushSMPMessageContent.GroupContext value) {
				if(this.groupBuilder_ == null) {
					if((this.bitField0_ & 4) == 4 && this.group_ != PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance) {
						this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.newBuilder(this.group_).mergeFrom(value).buildPartial();
					} else {
						this.group_ = value;
					}

					this.onChanged();
				} else {
					this.groupBuilder_.mergeFrom(value);
				}

				this.bitField0_ |= 4;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clearGroup() {
				if(this.groupBuilder_ == null) {
					this.group_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
					this.onChanged();
				} else {
					this.groupBuilder_.clear();
				}

				this.bitField0_ &= -5;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder getGroupBuilder() {
				this.bitField0_ |= 4;
				this.onChanged();
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder)this.getGroupFieldBuilder().getBuilder();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder getGroupOrBuilder() {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder)(this.groupBuilder_ != null?(PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder)this.groupBuilder_.getMessageOrBuilder():this.group_);
			}

			private SingleFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.GroupContext, PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder, PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder> getGroupFieldBuilder() {
				if(this.groupBuilder_ == null) {
					this.groupBuilder_ = new SingleFieldBuilder(this.group_, this.getParentForChildren(), this.isClean());
					this.group_ = null;
				}

				return this.groupBuilder_;
			}

			public boolean hasFlags() {
				return (this.bitField0_ & 8) == 8;
			}

			public int getFlags() {
				return this.flags_;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setFlags(int value) {
				this.bitField0_ |= 8;
				this.flags_ = value;
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clearFlags() {
				this.bitField0_ &= -9;
				this.flags_ = 0;
				this.onChanged();
				return this;
			}

			public boolean hasSync() {
				return (this.bitField0_ & 16) == 16;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getSync() {
				return this.syncBuilder_ == null?this.sync_:(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)this.syncBuilder_.getMessage();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setSync(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext value) {
				if(this.syncBuilder_ == null) {
					if(value == null) {
						throw new NullPointerException();
					}

					this.sync_ = value;
					this.onChanged();
				} else {
					this.syncBuilder_.setMessage(value);
				}

				this.bitField0_ |= 16;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder setSync(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder builderForValue) {
				if(this.syncBuilder_ == null) {
					this.sync_ = builderForValue.build();
					this.onChanged();
				} else {
					this.syncBuilder_.setMessage(builderForValue.build());
				}

				this.bitField0_ |= 16;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder mergeSync(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext value) {
				if(this.syncBuilder_ == null) {
					if((this.bitField0_ & 16) == 16 && this.sync_ != PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance) {
						this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.newBuilder(this.sync_).mergeFrom(value).buildPartial();
					} else {
						this.sync_ = value;
					}

					this.onChanged();
				} else {
					this.syncBuilder_.mergeFrom(value);
				}

				this.bitField0_ |= 16;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.Builder clearSync() {
				if(this.syncBuilder_ == null) {
					this.sync_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
					this.onChanged();
				} else {
					this.syncBuilder_.clear();
				}

				this.bitField0_ &= -17;
				return this;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder getSyncBuilder() {
				this.bitField0_ |= 16;
				this.onChanged();
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder)this.getSyncFieldBuilder().getBuilder();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder getSyncOrBuilder() {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder)(this.syncBuilder_ != null?(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder)this.syncBuilder_.getMessageOrBuilder():this.sync_);
			}

			private SingleFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext, PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder, PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder> getSyncFieldBuilder() {
				if(this.syncBuilder_ == null) {
					this.syncBuilder_ = new SingleFieldBuilder(this.sync_, this.getParentForChildren(), this.isClean());
					this.sync_ = null;
				}

				return this.syncBuilder_;
			}
		}

		public static final class SyncMessageContext extends GeneratedMessage implements PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder {
			private static final PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext defaultInstance = new PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext(true);
			private final UnknownFieldSet unknownFields;
			public static Parser<PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext> PARSER = new AbstractParser() {
				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
					return new PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext(input, extensionRegistry);
				}
			};
			private int bitField0_;
			public static final int DESTINATION_FIELD_NUMBER = 1;
			private Object destination_;
			public static final int TIMESTAMP_FIELD_NUMBER = 2;
			private long timestamp_;
			private byte memoizedIsInitialized;
			private int memoizedSerializedSize;
			private static final long serialVersionUID = 0L;

			private SyncMessageContext(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
				super(builder);
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = builder.getUnknownFields();
			}

			private SyncMessageContext(boolean noInit) {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = UnknownFieldSet.getDefaultInstance();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getDefaultInstance() {
				return defaultInstance;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getDefaultInstanceForType() {
				return defaultInstance;
			}

			public final UnknownFieldSet getUnknownFields() {
				return this.unknownFields;
			}

			private SyncMessageContext(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.initFields();
				boolean mutable_bitField0_ = false;
				com.google.protobuf.UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

				try {
					boolean e = false;

					while(!e) {
						int tag = input.readTag();
						switch(tag) {
							case 0:
								e = true;
								break;
							case 10:
								this.bitField0_ |= 1;
								this.destination_ = input.readBytes();
								break;
							case 16:
								this.bitField0_ |= 2;
								this.timestamp_ = input.readUInt64();
								break;
							default:
								if(!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
									e = true;
								}
						}
					}
				} catch (InvalidProtocolBufferException var11) {
					throw var11.setUnfinishedMessage(this);
				} catch (IOException var12) {
					throw (new InvalidProtocolBufferException(var12.getMessage())).setUnfinishedMessage(this);
				} finally {
					this.unknownFields = unknownFields.build();
					this.makeExtensionsImmutable();
				}

			}

			public static final Descriptors.Descriptor getDescriptor() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor;
			}

			protected FieldAccessorTable internalGetFieldAccessorTable() {
				return PushSMPMessageProtos
					.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_fieldAccessorTable
					.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.class,
						PushSMPMessageProtos.PushSMPMessageContent.Builder.class);
			}

			public Parser<PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext> getParserForType() {
				return PARSER;
			}

			public boolean hasDestination() {
				return (this.bitField0_ & 1) == 1;
			}

			public String getDestination() {
				Object ref = this.destination_;
				if(ref instanceof String) {
					return (String)ref;
				} else {
					ByteString bs = (ByteString)ref;
					String s = bs.toStringUtf8();
					if(bs.isValidUtf8()) {
						this.destination_ = s;
					}

					return s;
				}
			}

			public ByteString getDestinationBytes() {
				Object ref = this.destination_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.destination_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public boolean hasTimestamp() {
				return (this.bitField0_ & 2) == 2;
			}

			public long getTimestamp() {
				return this.timestamp_;
			}

			private void initFields() {
				this.destination_ = "";
				this.timestamp_ = 0L;
			}

			public final boolean isInitialized() {
				byte isInitialized = this.memoizedIsInitialized;
				if(isInitialized != -1) {
					return isInitialized == 1;
				} else {
					this.memoizedIsInitialized = 1;
					return true;
				}
			}

			public void writeTo(CodedOutputStream output) throws IOException {
				this.getSerializedSize();
				if((this.bitField0_ & 1) == 1) {
					output.writeBytes(1, this.getDestinationBytes());
				}

				if((this.bitField0_ & 2) == 2) {
					output.writeUInt64(2, this.timestamp_);
				}

				this.getUnknownFields().writeTo(output);
			}

			public int getSerializedSize() {
				int size = this.memoizedSerializedSize;
				if(size != -1) {
					return size;
				} else {
					size = 0;
					if((this.bitField0_ & 1) == 1) {
						size += CodedOutputStream.computeBytesSize(1, this.getDestinationBytes());
					}

					if((this.bitField0_ & 2) == 2) {
						size += CodedOutputStream.computeUInt64Size(2, this.timestamp_);
					}

					size += this.getUnknownFields().getSerializedSize();
					this.memoizedSerializedSize = size;
					return size;
				}
			}

			protected Object writeReplace() throws ObjectStreamException {
				return super.writeReplace();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(ByteString data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(byte[] data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseDelimitedFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseDelimitedFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseDelimitedFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(CodedInputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder newBuilder() {
				return PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder.create();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder newBuilderForType() {
				return newBuilder();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder newBuilder(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext prototype) {
				return newBuilder().mergeFrom(prototype);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder toBuilder() {
				return newBuilder(this);
			}

			protected PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder newBuilderForType(BuilderParent parent) {
				PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder builder = new PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder(parent);
				return builder;
			}

			static {
				defaultInstance.initFields();
			}

			public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder> implements PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder {
				private int bitField0_;
				private Object destination_;
				private long timestamp_;

				public static final Descriptors.Descriptor getDescriptor() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor;
				}

				protected FieldAccessorTable internalGetFieldAccessorTable() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.class, PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder.class);
				}

				private Builder() {
					this.destination_ = "";
					this.maybeForceBuilderInitialization();
				}

				private Builder(BuilderParent parent) {
					super(parent);
					this.destination_ = "";
					this.maybeForceBuilderInitialization();
				}

				private void maybeForceBuilderInitialization() {
					if(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.alwaysUseFieldBuilders) {
						;
					}

				}

				private static PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder create() {
					return new PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder();
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder clear() {
					super.clear();
					this.destination_ = "";
					this.bitField0_ &= -2;
					this.timestamp_ = 0L;
					this.bitField0_ &= -3;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder clone() {
					return create().mergeFrom(this.buildPartial());
				}

				public Descriptors.Descriptor getDescriptorForType() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_SyncMessageContext_descriptor;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getDefaultInstanceForType() {
					return PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext build() {
					PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext result = this.buildPartial();
					if(!result.isInitialized()) {
						throw newUninitializedMessageException(result);
					} else {
						return result;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext buildPartial() {
					PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext result = new PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext(this);
					int from_bitField0_ = this.bitField0_;
					int to_bitField0_ = 0;
					if((from_bitField0_ & 1) == 1) {
						to_bitField0_ |= 1;
					}

					result.destination_ = this.destination_;
					if((from_bitField0_ & 2) == 2) {
						to_bitField0_ |= 2;
					}

					result.timestamp_ = this.timestamp_;
					result.bitField0_ = to_bitField0_;
					this.onBuilt();
					return result;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder mergeFrom(Message other) {
					if(other instanceof PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext) {
						return this.mergeFrom((PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)other);
					} else {
						super.mergeFrom(other);
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder mergeFrom(PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext other) {
					if(other == PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance) {
						return this;
					} else {
						if(other.hasDestination()) {
							this.bitField0_ |= 1;
							this.destination_ = other.destination_;
							this.onChanged();
						}

						if(other.hasTimestamp()) {
							this.setTimestamp(other.getTimestamp());
						}

						this.mergeUnknownFields(other.getUnknownFields());
						return this;
					}
				}

				public final boolean isInitialized() {
					return true;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
					PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext parsedMessage = null;

					try {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.PARSER.parsePartialFrom(input, extensionRegistry);
					} catch (InvalidProtocolBufferException var8) {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext)var8.getUnfinishedMessage();
						throw var8;
					} finally {
						if(parsedMessage != null) {
							this.mergeFrom(parsedMessage);
						}

					}

					return this;
				}

				public boolean hasDestination() {
					return (this.bitField0_ & 1) == 1;
				}

				public String getDestination() {
					Object ref = this.destination_;
					if(!(ref instanceof String)) {
						String s = ((ByteString)ref).toStringUtf8();
						this.destination_ = s;
						return s;
					} else {
						return (String)ref;
					}
				}

				public ByteString getDestinationBytes() {
					Object ref = this.destination_;
					if(ref instanceof String) {
						ByteString b = ByteString.copyFromUtf8((String)ref);
						this.destination_ = b;
						return b;
					} else {
						return (ByteString)ref;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder setDestination(String value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 1;
						this.destination_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder clearDestination() {
					this.bitField0_ &= -2;
					this.destination_ = PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.defaultInstance.getDestination();
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder setDestinationBytes(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 1;
						this.destination_ = value;
						this.onChanged();
						return this;
					}
				}

				public boolean hasTimestamp() {
					return (this.bitField0_ & 2) == 2;
				}

				public long getTimestamp() {
					return this.timestamp_;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder setTimestamp(long value) {
					this.bitField0_ |= 2;
					this.timestamp_ = value;
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext.Builder clearTimestamp() {
					this.bitField0_ &= -3;
					this.timestamp_ = 0L;
					this.onChanged();
					return this;
				}
			}
		}

		public interface SyncMessageContextOrBuilder extends MessageOrBuilder {
			boolean hasDestination();

			String getDestination();

			ByteString getDestinationBytes();

			boolean hasTimestamp();

			long getTimestamp();
		}

		public static final class GroupContext extends GeneratedMessage implements PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder {
			private static final PushSMPMessageProtos.PushSMPMessageContent.GroupContext defaultInstance = new PushSMPMessageProtos.PushSMPMessageContent.GroupContext(true);
			private final UnknownFieldSet unknownFields;
			public static Parser<PushSMPMessageProtos.PushSMPMessageContent.GroupContext> PARSER = new AbstractParser() {
				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
					return new PushSMPMessageProtos.PushSMPMessageContent.GroupContext(input, extensionRegistry);
				}
			};
			private int bitField0_;
			public static final int ID_FIELD_NUMBER = 1;
			private ByteString id_;
			public static final int TYPE_FIELD_NUMBER = 2;
			private PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type type_;
			public static final int NAME_FIELD_NUMBER = 3;
			private Object name_;
			public static final int MEMBERS_FIELD_NUMBER = 4;
			private LazyStringList members_;
			public static final int AVATAR_FIELD_NUMBER = 5;
			private PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer avatar_;
			private byte memoizedIsInitialized;
			private int memoizedSerializedSize;
			private static final long serialVersionUID = 0L;

			private GroupContext(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
				super(builder);
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = builder.getUnknownFields();
			}

			private GroupContext(boolean noInit) {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = UnknownFieldSet.getDefaultInstance();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext getDefaultInstance() {
				return defaultInstance;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext getDefaultInstanceForType() {
				return defaultInstance;
			}

			public final UnknownFieldSet getUnknownFields() {
				return this.unknownFields;
			}

			private GroupContext(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.initFields();
				int mutable_bitField0_ = 0;
				com.google.protobuf.UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

				try {
					boolean e = false;

					while(!e) {
						int tag = input.readTag();
						switch(tag) {
							case 0:
								e = true;
								break;
							case 10:
								this.bitField0_ |= 1;
								this.id_ = input.readBytes();
								break;
							case 16:
								int subBuilder1 = input.readEnum();
								PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type value = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.valueOf(subBuilder1);
								if(value == null) {
									unknownFields.mergeVarintField(2, subBuilder1);
								} else {
									this.bitField0_ |= 2;
									this.type_ = value;
								}
								break;
							case 26:
								this.bitField0_ |= 4;
								this.name_ = input.readBytes();
								break;
							case 34:
								if((mutable_bitField0_ & 8) != 8) {
									this.members_ = new LazyStringArrayList();
									mutable_bitField0_ |= 8;
								}

								this.members_.add(input.readBytes());
								break;
							case 42:
								PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder subBuilder = null;
								if((this.bitField0_ & 8) == 8) {
									subBuilder = this.avatar_.toBuilder();
								}

								this.avatar_ = (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)input.readMessage(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.PARSER, extensionRegistry);
								if(subBuilder != null) {
									subBuilder.mergeFrom(this.avatar_);
									this.avatar_ = subBuilder.buildPartial();
								}

								this.bitField0_ |= 8;
								break;
							default:
								if(!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
									e = true;
								}
						}
					}
				} catch (InvalidProtocolBufferException var13) {
					throw var13.setUnfinishedMessage(this);
				} catch (IOException var14) {
					throw (new InvalidProtocolBufferException(var14.getMessage())).setUnfinishedMessage(this);
				} finally {
					if((mutable_bitField0_ & 8) == 8) {
						this.members_ = new UnmodifiableLazyStringList(this.members_);
					}

					this.unknownFields = unknownFields.build();
					this.makeExtensionsImmutable();
				}

			}

			public static final Descriptors.Descriptor getDescriptor() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor;
			}

			protected FieldAccessorTable internalGetFieldAccessorTable() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.class, PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder.class);
			}

			public Parser<PushSMPMessageProtos.PushSMPMessageContent.GroupContext> getParserForType() {
				return PARSER;
			}

			public boolean hasId() {
				return (this.bitField0_ & 1) == 1;
			}

			public ByteString getId() {
				return this.id_;
			}

			public boolean hasType() {
				return (this.bitField0_ & 2) == 2;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type getType() {
				return this.type_;
			}

			public boolean hasName() {
				return (this.bitField0_ & 4) == 4;
			}

			public String getName() {
				Object ref = this.name_;
				if(ref instanceof String) {
					return (String)ref;
				} else {
					ByteString bs = (ByteString)ref;
					String s = bs.toStringUtf8();
					if(bs.isValidUtf8()) {
						this.name_ = s;
					}

					return s;
				}
			}

			public ByteString getNameBytes() {
				Object ref = this.name_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.name_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public List<String> getMembersList() {
				return this.members_;
			}

			public int getMembersCount() {
				return this.members_.size();
			}

			public String getMembers(int index) {
				return (String)this.members_.get(index);
			}

			public ByteString getMembersBytes(int index) {
				return this.members_.getByteString(index);
			}

			public boolean hasAvatar() {
				return (this.bitField0_ & 8) == 8;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAvatar() {
				return this.avatar_;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAvatarOrBuilder() {
				return this.avatar_;
			}

			private void initFields() {
				this.id_ = ByteString.EMPTY;
				this.type_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.UNKNOWN;
				this.name_ = "";
				this.members_ = LazyStringArrayList.EMPTY;
				this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
			}

			public final boolean isInitialized() {
				byte isInitialized = this.memoizedIsInitialized;
				if(isInitialized != -1) {
					return isInitialized == 1;
				} else {
					this.memoizedIsInitialized = 1;
					return true;
				}
			}

			public void writeTo(CodedOutputStream output) throws IOException {
				this.getSerializedSize();
				if((this.bitField0_ & 1) == 1) {
					output.writeBytes(1, this.id_);
				}

				if((this.bitField0_ & 2) == 2) {
					output.writeEnum(2, this.type_.getNumber());
				}

				if((this.bitField0_ & 4) == 4) {
					output.writeBytes(3, this.getNameBytes());
				}

				for(int i = 0; i < this.members_.size(); ++i) {
					output.writeBytes(4, this.members_.getByteString(i));
				}

				if((this.bitField0_ & 8) == 8) {
					output.writeMessage(5, this.avatar_);
				}

				this.getUnknownFields().writeTo(output);
			}

			public int getSerializedSize() {
				int size = this.memoizedSerializedSize;
				if(size != -1) {
					return size;
				} else {
					size = 0;
					if((this.bitField0_ & 1) == 1) {
						size += CodedOutputStream.computeBytesSize(1, this.id_);
					}

					if((this.bitField0_ & 2) == 2) {
						size += CodedOutputStream.computeEnumSize(2, this.type_.getNumber());
					}

					if((this.bitField0_ & 4) == 4) {
						size += CodedOutputStream.computeBytesSize(3, this.getNameBytes());
					}

					int dataSize = 0;

					for(int i = 0; i < this.members_.size(); ++i) {
						dataSize += CodedOutputStream.computeBytesSizeNoTag(this.members_.getByteString(i));
					}

					size += dataSize;
					size += 1 * this.getMembersList().size();
					if((this.bitField0_ & 8) == 8) {
						size += CodedOutputStream.computeMessageSize(5, this.avatar_);
					}

					size += this.getUnknownFields().getSerializedSize();
					this.memoizedSerializedSize = size;
					return size;
				}
			}

			protected Object writeReplace() throws ObjectStreamException {
				return super.writeReplace();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(ByteString data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(byte[] data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseDelimitedFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseDelimitedFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseDelimitedFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(CodedInputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder newBuilder() {
				return PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder.create();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder newBuilderForType() {
				return newBuilder();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder newBuilder(PushSMPMessageProtos.PushSMPMessageContent.GroupContext prototype) {
				return newBuilder().mergeFrom(prototype);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder toBuilder() {
				return newBuilder(this);
			}

			protected PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder newBuilderForType(BuilderParent parent) {
				PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder builder = new PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder(parent);
				return builder;
			}

			static {
				defaultInstance.initFields();
			}

			public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder> implements PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder {
				private int bitField0_;
				private ByteString id_;
				private PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type type_;
				private Object name_;
				private LazyStringList members_;
				private PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer avatar_;
				private SingleFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> avatarBuilder_;

				public static final Descriptors.Descriptor getDescriptor() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor;
				}

				protected FieldAccessorTable internalGetFieldAccessorTable() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.class, PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder.class);
				}

				private Builder() {
					this.id_ = ByteString.EMPTY;
					this.type_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.UNKNOWN;
					this.name_ = "";
					this.members_ = LazyStringArrayList.EMPTY;
					this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
					this.maybeForceBuilderInitialization();
				}

				private Builder(BuilderParent parent) {
					super(parent);
					this.id_ = ByteString.EMPTY;
					this.type_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.UNKNOWN;
					this.name_ = "";
					this.members_ = LazyStringArrayList.EMPTY;
					this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
					this.maybeForceBuilderInitialization();
				}

				private void maybeForceBuilderInitialization() {
					if(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.alwaysUseFieldBuilders) {
						this.getAvatarFieldBuilder();
					}

				}

				private static PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder create() {
					return new PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder();
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clear() {
					super.clear();
					this.id_ = ByteString.EMPTY;
					this.bitField0_ &= -2;
					this.type_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.UNKNOWN;
					this.bitField0_ &= -3;
					this.name_ = "";
					this.bitField0_ &= -5;
					this.members_ = LazyStringArrayList.EMPTY;
					this.bitField0_ &= -9;
					if(this.avatarBuilder_ == null) {
						this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
					} else {
						this.avatarBuilder_.clear();
					}

					this.bitField0_ &= -17;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clone() {
					return create().mergeFrom(this.buildPartial());
				}

				public Descriptors.Descriptor getDescriptorForType() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_GroupContext_descriptor;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext getDefaultInstanceForType() {
					return PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext build() {
					PushSMPMessageProtos.PushSMPMessageContent.GroupContext result = this.buildPartial();
					if(!result.isInitialized()) {
						throw newUninitializedMessageException(result);
					} else {
						return result;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext buildPartial() {
					PushSMPMessageProtos.PushSMPMessageContent.GroupContext result = new PushSMPMessageProtos.PushSMPMessageContent.GroupContext(this);
					int from_bitField0_ = this.bitField0_;
					int to_bitField0_ = 0;
					if((from_bitField0_ & 1) == 1) {
						to_bitField0_ |= 1;
					}

					result.id_ = this.id_;
					if((from_bitField0_ & 2) == 2) {
						to_bitField0_ |= 2;
					}

					result.type_ = this.type_;
					if((from_bitField0_ & 4) == 4) {
						to_bitField0_ |= 4;
					}

					result.name_ = this.name_;
					if((this.bitField0_ & 8) == 8) {
						this.members_ = new UnmodifiableLazyStringList(this.members_);
						this.bitField0_ &= -9;
					}

					result.members_ = this.members_;
					if((from_bitField0_ & 16) == 16) {
						to_bitField0_ |= 8;
					}

					if(this.avatarBuilder_ == null) {
						result.avatar_ = this.avatar_;
					} else {
						result.avatar_ = (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)this.avatarBuilder_.build();
					}

					result.bitField0_ = to_bitField0_;
					this.onBuilt();
					return result;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder mergeFrom(Message other) {
					if(other instanceof PushSMPMessageProtos.PushSMPMessageContent.GroupContext) {
						return this.mergeFrom((PushSMPMessageProtos.PushSMPMessageContent.GroupContext)other);
					} else {
						super.mergeFrom(other);
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder mergeFrom(PushSMPMessageProtos.PushSMPMessageContent.GroupContext other) {
					if(other == PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance) {
						return this;
					} else {
						if(other.hasId()) {
							this.setId(other.getId());
						}

						if(other.hasType()) {
							this.setType(other.getType());
						}

						if(other.hasName()) {
							this.bitField0_ |= 4;
							this.name_ = other.name_;
							this.onChanged();
						}

						if(!other.members_.isEmpty()) {
							if(this.members_.isEmpty()) {
								this.members_ = other.members_;
								this.bitField0_ &= -9;
							} else {
								this.ensureMembersIsMutable();
								this.members_.addAll(other.members_);
							}

							this.onChanged();
						}

						if(other.hasAvatar()) {
							this.mergeAvatar(other.getAvatar());
						}

						this.mergeUnknownFields(other.getUnknownFields());
						return this;
					}
				}

				public final boolean isInitialized() {
					return true;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
					PushSMPMessageProtos.PushSMPMessageContent.GroupContext parsedMessage = null;

					try {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)PushSMPMessageProtos.PushSMPMessageContent.GroupContext.PARSER.parsePartialFrom(input, extensionRegistry);
					} catch (InvalidProtocolBufferException var8) {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.GroupContext)var8.getUnfinishedMessage();
						throw var8;
					} finally {
						if(parsedMessage != null) {
							this.mergeFrom(parsedMessage);
						}

					}

					return this;
				}

				public boolean hasId() {
					return (this.bitField0_ & 1) == 1;
				}

				public ByteString getId() {
					return this.id_;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setId(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 1;
						this.id_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clearId() {
					this.bitField0_ &= -2;
					this.id_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance.getId();
					this.onChanged();
					return this;
				}

				public boolean hasType() {
					return (this.bitField0_ & 2) == 2;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type getType() {
					return this.type_;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setType(PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 2;
						this.type_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clearType() {
					this.bitField0_ &= -3;
					this.type_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.UNKNOWN;
					this.onChanged();
					return this;
				}

				public boolean hasName() {
					return (this.bitField0_ & 4) == 4;
				}

				public String getName() {
					Object ref = this.name_;
					if(!(ref instanceof String)) {
						String s = ((ByteString)ref).toStringUtf8();
						this.name_ = s;
						return s;
					} else {
						return (String)ref;
					}
				}

				public ByteString getNameBytes() {
					Object ref = this.name_;
					if(ref instanceof String) {
						ByteString b = ByteString.copyFromUtf8((String)ref);
						this.name_ = b;
						return b;
					} else {
						return (ByteString)ref;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setName(String value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 4;
						this.name_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clearName() {
					this.bitField0_ &= -5;
					this.name_ = PushSMPMessageProtos.PushSMPMessageContent.GroupContext.defaultInstance.getName();
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setNameBytes(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 4;
						this.name_ = value;
						this.onChanged();
						return this;
					}
				}

				private void ensureMembersIsMutable() {
					if((this.bitField0_ & 8) != 8) {
						this.members_ = new LazyStringArrayList(this.members_);
						this.bitField0_ |= 8;
					}

				}

				public List<String> getMembersList() {
					return Collections.unmodifiableList(this.members_);
				}

				public int getMembersCount() {
					return this.members_.size();
				}

				public String getMembers(int index) {
					return (String)this.members_.get(index);
				}

				public ByteString getMembersBytes(int index) {
					return this.members_.getByteString(index);
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setMembers(int index, String value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.ensureMembersIsMutable();
						this.members_.set(index, value);
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder addMembers(String value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.ensureMembersIsMutable();
						this.members_.add(value);
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder addAllMembers(Iterable<String> values) {
					this.ensureMembersIsMutable();
					com.google.protobuf.GeneratedMessage.Builder.addAll(values, this.members_);
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clearMembers() {
					this.members_ = LazyStringArrayList.EMPTY;
					this.bitField0_ &= -9;
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder addMembersBytes(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.ensureMembersIsMutable();
						this.members_.add(value);
						this.onChanged();
						return this;
					}
				}

				public boolean hasAvatar() {
					return (this.bitField0_ & 16) == 16;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAvatar() {
					return this.avatarBuilder_ == null?this.avatar_:(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)this.avatarBuilder_.getMessage();
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setAvatar(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer value) {
					if(this.avatarBuilder_ == null) {
						if(value == null) {
							throw new NullPointerException();
						}

						this.avatar_ = value;
						this.onChanged();
					} else {
						this.avatarBuilder_.setMessage(value);
					}

					this.bitField0_ |= 16;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder setAvatar(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder builderForValue) {
					if(this.avatarBuilder_ == null) {
						this.avatar_ = builderForValue.build();
						this.onChanged();
					} else {
						this.avatarBuilder_.setMessage(builderForValue.build());
					}

					this.bitField0_ |= 16;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder mergeAvatar(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer value) {
					if(this.avatarBuilder_ == null) {
						if((this.bitField0_ & 16) == 16 && this.avatar_ != PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance) {
							this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.newBuilder(this.avatar_).mergeFrom(value).buildPartial();
						} else {
							this.avatar_ = value;
						}

						this.onChanged();
					} else {
						this.avatarBuilder_.mergeFrom(value);
					}

					this.bitField0_ |= 16;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Builder clearAvatar() {
					if(this.avatarBuilder_ == null) {
						this.avatar_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
						this.onChanged();
					} else {
						this.avatarBuilder_.clear();
					}

					this.bitField0_ &= -17;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder getAvatarBuilder() {
					this.bitField0_ |= 16;
					this.onChanged();
					return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder)this.getAvatarFieldBuilder().getBuilder();
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAvatarOrBuilder() {
					return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder)(this.avatarBuilder_ != null?(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder)this.avatarBuilder_.getMessageOrBuilder():this.avatar_);
				}

				private SingleFieldBuilder<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> getAvatarFieldBuilder() {
					if(this.avatarBuilder_ == null) {
						this.avatarBuilder_ = new SingleFieldBuilder(this.avatar_, this.getParentForChildren(), this.isClean());
						this.avatar_ = null;
					}

					return this.avatarBuilder_;
				}
			}

			public static enum Type implements ProtocolMessageEnum {
				UNKNOWN(0, 0),
				UPDATE(1, 1),
				DELIVER(2, 2),
				QUIT(3, 3);

				public static final int UNKNOWN_VALUE = 0;
				public static final int UPDATE_VALUE = 1;
				public static final int DELIVER_VALUE = 2;
				public static final int QUIT_VALUE = 3;
				private static Internal.EnumLiteMap<Type> internalValueMap;
				private static final PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type[] VALUES;
				private final int index;
				private final int value;

				public final int getNumber() {
					return this.value;
				}

				public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type valueOf(int value) {
					switch(value) {
						case 0:
							return UNKNOWN;
						case 1:
							return UPDATE;
						case 2:
							return DELIVER;
						case 3:
							return QUIT;
						default:
							return null;
					}
				}

				public static Internal.EnumLiteMap<Type> internalGetValueMap() {
					return internalValueMap;
				}

				public final Descriptors.EnumValueDescriptor getValueDescriptor() {
					return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
				}

				public final Descriptors.EnumDescriptor getDescriptorForType() {
					return getDescriptor();
				}

				public static final Descriptors.EnumDescriptor getDescriptor() {
					return (Descriptors.EnumDescriptor)PushSMPMessageProtos.PushSMPMessageContent.GroupContext.getDescriptor().getEnumTypes().get(0);
				}

				public static PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type valueOf(Descriptors.EnumValueDescriptor desc) {
					if(desc.getType() != getDescriptor()) {
						throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
					} else {
						return VALUES[desc.getIndex()];
					}
				}

				private Type(int index, int value) {
					this.index = index;
					this.value = value;
				}

				static {
					internalValueMap = new Internal.EnumLiteMap() {
						public PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type findValueByNumber(int number) {
							return PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type.valueOf(number);
						}
					};
					VALUES = values();
				}
			}
		}

		public interface GroupContextOrBuilder extends MessageOrBuilder {
			boolean hasId();

			ByteString getId();

			boolean hasType();

			PushSMPMessageProtos.PushSMPMessageContent.GroupContext.Type getType();

			boolean hasName();

			String getName();

			ByteString getNameBytes();

			List<String> getMembersList();

			int getMembersCount();

			String getMembers(int var1);

			ByteString getMembersBytes(int var1);

			boolean hasAvatar();

			PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAvatar();

			PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAvatarOrBuilder();
		}

		public static final class AttachmentPointer extends GeneratedMessage implements PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder {
			private static final PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer defaultInstance = new PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer(true);
			private final UnknownFieldSet unknownFields;
			public static Parser<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> PARSER = new AbstractParser() {
				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
					return new PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer(input, extensionRegistry);
				}
			};
			private int bitField0_;
			public static final int ID_FIELD_NUMBER = 1;
			private long id_;
			public static final int CONTENTTYPE_FIELD_NUMBER = 2;
			private Object contentType_;
			public static final int KEY_FIELD_NUMBER = 3;
			private ByteString key_;
			private byte memoizedIsInitialized;
			private int memoizedSerializedSize;
			private static final long serialVersionUID = 0L;

			private AttachmentPointer(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
				super(builder);
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = builder.getUnknownFields();
			}

			private AttachmentPointer(boolean noInit) {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.unknownFields = UnknownFieldSet.getDefaultInstance();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getDefaultInstance() {
				return defaultInstance;
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getDefaultInstanceForType() {
				return defaultInstance;
			}

			public final UnknownFieldSet getUnknownFields() {
				return this.unknownFields;
			}

			private AttachmentPointer(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				this.memoizedIsInitialized = -1;
				this.memoizedSerializedSize = -1;
				this.initFields();
				boolean mutable_bitField0_ = false;
				com.google.protobuf.UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

				try {
					boolean e = false;

					while(!e) {
						int tag = input.readTag();
						switch(tag) {
							case 0:
								e = true;
								break;
							case 9:
								this.bitField0_ |= 1;
								this.id_ = input.readFixed64();
								break;
							case 18:
								this.bitField0_ |= 2;
								this.contentType_ = input.readBytes();
								break;
							case 26:
								this.bitField0_ |= 4;
								this.key_ = input.readBytes();
								break;
							default:
								if(!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
									e = true;
								}
						}
					}
				} catch (InvalidProtocolBufferException var11) {
					throw var11.setUnfinishedMessage(this);
				} catch (IOException var12) {
					throw (new InvalidProtocolBufferException(var12.getMessage())).setUnfinishedMessage(this);
				} finally {
					this.unknownFields = unknownFields.build();
					this.makeExtensionsImmutable();
				}

			}

			public static final Descriptors.Descriptor getDescriptor() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor;
			}

			protected FieldAccessorTable internalGetFieldAccessorTable() {
				return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.class, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder.class);
			}

			public Parser<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> getParserForType() {
				return PARSER;
			}

			public boolean hasId() {
				return (this.bitField0_ & 1) == 1;
			}

			public long getId() {
				return this.id_;
			}

			public boolean hasContentType() {
				return (this.bitField0_ & 2) == 2;
			}

			public String getContentType() {
				Object ref = this.contentType_;
				if(ref instanceof String) {
					return (String)ref;
				} else {
					ByteString bs = (ByteString)ref;
					String s = bs.toStringUtf8();
					if(bs.isValidUtf8()) {
						this.contentType_ = s;
					}

					return s;
				}
			}

			public ByteString getContentTypeBytes() {
				Object ref = this.contentType_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.contentType_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public boolean hasKey() {
				return (this.bitField0_ & 4) == 4;
			}

			public ByteString getKey() {
				return this.key_;
			}

			private void initFields() {
				this.id_ = 0L;
				this.contentType_ = "";
				this.key_ = ByteString.EMPTY;
			}

			public final boolean isInitialized() {
				byte isInitialized = this.memoizedIsInitialized;
				if(isInitialized != -1) {
					return isInitialized == 1;
				} else {
					this.memoizedIsInitialized = 1;
					return true;
				}
			}

			public void writeTo(CodedOutputStream output) throws IOException {
				this.getSerializedSize();
				if((this.bitField0_ & 1) == 1) {
					output.writeFixed64(1, this.id_);
				}

				if((this.bitField0_ & 2) == 2) {
					output.writeBytes(2, this.getContentTypeBytes());
				}

				if((this.bitField0_ & 4) == 4) {
					output.writeBytes(3, this.key_);
				}

				this.getUnknownFields().writeTo(output);
			}

			public int getSerializedSize() {
				int size = this.memoizedSerializedSize;
				if(size != -1) {
					return size;
				} else {
					size = 0;
					if((this.bitField0_ & 1) == 1) {
						size += CodedOutputStream.computeFixed64Size(1, this.id_);
					}

					if((this.bitField0_ & 2) == 2) {
						size += CodedOutputStream.computeBytesSize(2, this.getContentTypeBytes());
					}

					if((this.bitField0_ & 4) == 4) {
						size += CodedOutputStream.computeBytesSize(3, this.key_);
					}

					size += this.getUnknownFields().getSerializedSize();
					this.memoizedSerializedSize = size;
					return size;
				}
			}

			protected Object writeReplace() throws ObjectStreamException {
				return super.writeReplace();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(ByteString data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(byte[] data) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(data);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(data, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseDelimitedFrom(InputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseDelimitedFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseDelimitedFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(CodedInputStream input) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(input);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				return (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PARSER.parseFrom(input, extensionRegistry);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder newBuilder() {
				return PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder.create();
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder newBuilderForType() {
				return newBuilder();
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder newBuilder(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer prototype) {
				return newBuilder().mergeFrom(prototype);
			}

			public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder toBuilder() {
				return newBuilder(this);
			}

			protected PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder newBuilderForType(BuilderParent parent) {
				PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder builder = new PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder(parent);
				return builder;
			}

			static {
				defaultInstance.initFields();
			}

			public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder> implements PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder {
				private int bitField0_;
				private long id_;
				private Object contentType_;
				private ByteString key_;

				public static final Descriptors.Descriptor getDescriptor() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor;
				}

				protected FieldAccessorTable internalGetFieldAccessorTable() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.class, PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder.class);
				}

				private Builder() {
					this.contentType_ = "";
					this.key_ = ByteString.EMPTY;
					this.maybeForceBuilderInitialization();
				}

				private Builder(BuilderParent parent) {
					super(parent);
					this.contentType_ = "";
					this.key_ = ByteString.EMPTY;
					this.maybeForceBuilderInitialization();
				}

				private void maybeForceBuilderInitialization() {
					if(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.alwaysUseFieldBuilders) {
						;
					}

				}

				private static PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder create() {
					return new PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder();
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder clear() {
					super.clear();
					this.id_ = 0L;
					this.bitField0_ &= -2;
					this.contentType_ = "";
					this.bitField0_ &= -3;
					this.key_ = ByteString.EMPTY;
					this.bitField0_ &= -5;
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder clone() {
					return create().mergeFrom(this.buildPartial());
				}

				public Descriptors.Descriptor getDescriptorForType() {
					return PushSMPMessageProtos.internal_static_textsecure_PushSMPMessageContent_AttachmentPointer_descriptor;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getDefaultInstanceForType() {
					return PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer build() {
					PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer result = this.buildPartial();
					if(!result.isInitialized()) {
						throw newUninitializedMessageException(result);
					} else {
						return result;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer buildPartial() {
					PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer result = new PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer(this);
					int from_bitField0_ = this.bitField0_;
					int to_bitField0_ = 0;
					if((from_bitField0_ & 1) == 1) {
						to_bitField0_ |= 1;
					}

					result.id_ = this.id_;
					if((from_bitField0_ & 2) == 2) {
						to_bitField0_ |= 2;
					}

					result.contentType_ = this.contentType_;
					if((from_bitField0_ & 4) == 4) {
						to_bitField0_ |= 4;
					}

					result.key_ = this.key_;
					result.bitField0_ = to_bitField0_;
					this.onBuilt();
					return result;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder mergeFrom(Message other) {
					if(other instanceof PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer) {
						return this.mergeFrom((PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)other);
					} else {
						super.mergeFrom(other);
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder mergeFrom(PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer other) {
					if(other == PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance) {
						return this;
					} else {
						if(other.hasId()) {
							this.setId(other.getId());
						}

						if(other.hasContentType()) {
							this.bitField0_ |= 2;
							this.contentType_ = other.contentType_;
							this.onChanged();
						}

						if(other.hasKey()) {
							this.setKey(other.getKey());
						}

						this.mergeUnknownFields(other.getUnknownFields());
						return this;
					}
				}

				public final boolean isInitialized() {
					return true;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
					PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer parsedMessage = null;

					try {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.PARSER.parsePartialFrom(input, extensionRegistry);
					} catch (InvalidProtocolBufferException var8) {
						parsedMessage = (PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer)var8.getUnfinishedMessage();
						throw var8;
					} finally {
						if(parsedMessage != null) {
							this.mergeFrom(parsedMessage);
						}

					}

					return this;
				}

				public boolean hasId() {
					return (this.bitField0_ & 1) == 1;
				}

				public long getId() {
					return this.id_;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder setId(long value) {
					this.bitField0_ |= 1;
					this.id_ = value;
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder clearId() {
					this.bitField0_ &= -2;
					this.id_ = 0L;
					this.onChanged();
					return this;
				}

				public boolean hasContentType() {
					return (this.bitField0_ & 2) == 2;
				}

				public String getContentType() {
					Object ref = this.contentType_;
					if(!(ref instanceof String)) {
						String s = ((ByteString)ref).toStringUtf8();
						this.contentType_ = s;
						return s;
					} else {
						return (String)ref;
					}
				}

				public ByteString getContentTypeBytes() {
					Object ref = this.contentType_;
					if(ref instanceof String) {
						ByteString b = ByteString.copyFromUtf8((String)ref);
						this.contentType_ = b;
						return b;
					} else {
						return (ByteString)ref;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder setContentType(String value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 2;
						this.contentType_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder clearContentType() {
					this.bitField0_ &= -3;
					this.contentType_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance.getContentType();
					this.onChanged();
					return this;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder setContentTypeBytes(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 2;
						this.contentType_ = value;
						this.onChanged();
						return this;
					}
				}

				public boolean hasKey() {
					return (this.bitField0_ & 4) == 4;
				}

				public ByteString getKey() {
					return this.key_;
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder setKey(ByteString value) {
					if(value == null) {
						throw new NullPointerException();
					} else {
						this.bitField0_ |= 4;
						this.key_ = value;
						this.onChanged();
						return this;
					}
				}

				public PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.Builder clearKey() {
					this.bitField0_ &= -5;
					this.key_ = PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer.defaultInstance.getKey();
					this.onChanged();
					return this;
				}
			}
		}

		public interface AttachmentPointerOrBuilder extends MessageOrBuilder {
			boolean hasId();

			long getId();

			boolean hasContentType();

			String getContentType();

			ByteString getContentTypeBytes();

			boolean hasKey();

			ByteString getKey();
		}

		public static enum Flags implements ProtocolMessageEnum {
			END_SESSION(0, 1);

			public static final int END_SESSION_VALUE = 1;
			private static Internal.EnumLiteMap<Flags> internalValueMap;
			private static final PushSMPMessageProtos.PushSMPMessageContent.Flags[] VALUES;
			private final int index;
			private final int value;

			public final int getNumber() {
				return this.value;
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.Flags valueOf(int value) {
				switch(value) {
					case 1:
						return END_SESSION;
					default:
						return null;
				}
			}

			public static Internal.EnumLiteMap<Flags> internalGetValueMap() {
				return internalValueMap;
			}

			public final Descriptors.EnumValueDescriptor getValueDescriptor() {
				return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
			}

			public final Descriptors.EnumDescriptor getDescriptorForType() {
				return getDescriptor();
			}

			public static final Descriptors.EnumDescriptor getDescriptor() {
				return (Descriptors.EnumDescriptor)PushSMPMessageProtos.PushSMPMessageContent.getDescriptor().getEnumTypes().get(0);
			}

			public static PushSMPMessageProtos.PushSMPMessageContent.Flags valueOf(Descriptors.EnumValueDescriptor desc) {
				if(desc.getType() != getDescriptor()) {
					throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
				} else {
					return VALUES[desc.getIndex()];
				}
			}

			private Flags(int index, int value) {
				this.index = index;
				this.value = value;
			}

			static {
				internalValueMap = new Internal.EnumLiteMap() {
					public PushSMPMessageProtos.PushSMPMessageContent.Flags findValueByNumber(int number) {
						return PushSMPMessageProtos.PushSMPMessageContent.Flags.valueOf(number);
					}
				};
				VALUES = values();
			}
		}
	}

	public interface PushSMPMessageContentOrBuilder extends MessageOrBuilder {
		boolean hasBody();

		String getBody();

		ByteString getBodyBytes();

		List<PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer> getAttachmentsList();

		PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointer getAttachments(int var1);

		int getAttachmentsCount();

		List<? extends PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder> getAttachmentsOrBuilderList();

		PushSMPMessageProtos.PushSMPMessageContent.AttachmentPointerOrBuilder getAttachmentsOrBuilder(int var1);

		boolean hasGroup();

		PushSMPMessageProtos.PushSMPMessageContent.GroupContext getGroup();

		PushSMPMessageProtos.PushSMPMessageContent.GroupContextOrBuilder getGroupOrBuilder();

		boolean hasFlags();

		int getFlags();

		boolean hasSync();

		PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContext getSync();

		PushSMPMessageProtos.PushSMPMessageContent.SyncMessageContextOrBuilder getSyncOrBuilder();
	}

	public static final class IncomingPushMessageSignal extends GeneratedMessage implements PushSMPMessageProtos.IncomingPushMessageSignalOrBuilder {
		private static final PushSMPMessageProtos.IncomingPushMessageSignal defaultInstance = new PushSMPMessageProtos.IncomingPushMessageSignal(true);
		private final UnknownFieldSet unknownFields;
		public static Parser<PushSMPMessageProtos.IncomingPushMessageSignal> PARSER = new AbstractParser() {
			public PushSMPMessageProtos.IncomingPushMessageSignal parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return new PushSMPMessageProtos.IncomingPushMessageSignal(input, extensionRegistry);
			}
		};
		private int bitField0_;
		public static final int TYPE_FIELD_NUMBER = 1;
		private PushSMPMessageProtos.IncomingPushMessageSignal.Type type_;
		public static final int SOURCE_FIELD_NUMBER = 2;
		private Object source_;
		public static final int SOURCEDEVICE_FIELD_NUMBER = 7;
		private int sourceDevice_;
		public static final int RELAY_FIELD_NUMBER = 3;
		private Object relay_;
		public static final int TIMESTAMP_FIELD_NUMBER = 5;
		private long timestamp_;
		public static final int MESSAGE_FIELD_NUMBER = 6;
		private ByteString message_;
		private byte memoizedIsInitialized;
		private int memoizedSerializedSize;
		private static final long serialVersionUID = 0L;

		private IncomingPushMessageSignal(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
			super(builder);
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.unknownFields = builder.getUnknownFields();
		}

		private IncomingPushMessageSignal(boolean noInit) {
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.unknownFields = UnknownFieldSet.getDefaultInstance();
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal getDefaultInstance() {
			return defaultInstance;
		}

		public PushSMPMessageProtos.IncomingPushMessageSignal getDefaultInstanceForType() {
			return defaultInstance;
		}

		public final UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private IncomingPushMessageSignal(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			this.memoizedIsInitialized = -1;
			this.memoizedSerializedSize = -1;
			this.initFields();
			boolean mutable_bitField0_ = false;
			com.google.protobuf.UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();

			try {
				boolean e = false;

				while(!e) {
					int tag = input.readTag();
					switch(tag) {
						case 0:
							e = true;
							break;
						case 8:
							int rawValue = input.readEnum();
							PushSMPMessageProtos.IncomingPushMessageSignal.Type value = PushSMPMessageProtos.IncomingPushMessageSignal.Type.valueOf(rawValue);
							if(value == null) {
								unknownFields.mergeVarintField(1, rawValue);
							} else {
								this.bitField0_ |= 1;
								this.type_ = value;
							}
							break;
						case 18:
							this.bitField0_ |= 2;
							this.source_ = input.readBytes();
							break;
						case 26:
							this.bitField0_ |= 8;
							this.relay_ = input.readBytes();
							break;
						case 40:
							this.bitField0_ |= 16;
							this.timestamp_ = input.readUInt64();
							break;
						case 50:
							this.bitField0_ |= 32;
							this.message_ = input.readBytes();
							break;
						case 56:
							this.bitField0_ |= 4;
							this.sourceDevice_ = input.readUInt32();
							break;
						default:
							if(!this.parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
								e = true;
							}
					}
				}
			} catch (InvalidProtocolBufferException var13) {
				throw var13.setUnfinishedMessage(this);
			} catch (IOException var14) {
				throw (new InvalidProtocolBufferException(var14.getMessage())).setUnfinishedMessage(this);
			} finally {
				this.unknownFields = unknownFields.build();
				this.makeExtensionsImmutable();
			}

		}

		public static final Descriptors.Descriptor getDescriptor() {
			return PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_descriptor;
		}

		protected FieldAccessorTable internalGetFieldAccessorTable() {
			return PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.IncomingPushMessageSignal.class, PushSMPMessageProtos.IncomingPushMessageSignal.Builder.class);
		}

		public Parser<PushSMPMessageProtos.IncomingPushMessageSignal> getParserForType() {
			return PARSER;
		}

		public boolean hasType() {
			return (this.bitField0_ & 1) == 1;
		}

		public PushSMPMessageProtos.IncomingPushMessageSignal.Type getType() {
			return this.type_;
		}

		public boolean hasSource() {
			return (this.bitField0_ & 2) == 2;
		}

		public String getSource() {
			Object ref = this.source_;
			if(ref instanceof String) {
				return (String)ref;
			} else {
				ByteString bs = (ByteString)ref;
				String s = bs.toStringUtf8();
				if(bs.isValidUtf8()) {
					this.source_ = s;
				}

				return s;
			}
		}

		public ByteString getSourceBytes() {
			Object ref = this.source_;
			if(ref instanceof String) {
				ByteString b = ByteString.copyFromUtf8((String)ref);
				this.source_ = b;
				return b;
			} else {
				return (ByteString)ref;
			}
		}

		public boolean hasSourceDevice() {
			return (this.bitField0_ & 4) == 4;
		}

		public int getSourceDevice() {
			return this.sourceDevice_;
		}

		public boolean hasRelay() {
			return (this.bitField0_ & 8) == 8;
		}

		public String getRelay() {
			Object ref = this.relay_;
			if(ref instanceof String) {
				return (String)ref;
			} else {
				ByteString bs = (ByteString)ref;
				String s = bs.toStringUtf8();
				if(bs.isValidUtf8()) {
					this.relay_ = s;
				}

				return s;
			}
		}

		public ByteString getRelayBytes() {
			Object ref = this.relay_;
			if(ref instanceof String) {
				ByteString b = ByteString.copyFromUtf8((String)ref);
				this.relay_ = b;
				return b;
			} else {
				return (ByteString)ref;
			}
		}

		public boolean hasTimestamp() {
			return (this.bitField0_ & 16) == 16;
		}

		public long getTimestamp() {
			return this.timestamp_;
		}

		public boolean hasMessage() {
			return (this.bitField0_ & 32) == 32;
		}

		public ByteString getMessage() {
			return this.message_;
		}

		private void initFields() {
			this.type_ = PushSMPMessageProtos.IncomingPushMessageSignal.Type.UNKNOWN;
			this.source_ = "";
			this.sourceDevice_ = 0;
			this.relay_ = "";
			this.timestamp_ = 0L;
			this.message_ = ByteString.EMPTY;
		}

		public final boolean isInitialized() {
			byte isInitialized = this.memoizedIsInitialized;
			if(isInitialized != -1) {
				return isInitialized == 1;
			} else {
				this.memoizedIsInitialized = 1;
				return true;
			}
		}

		public void writeTo(CodedOutputStream output) throws IOException {
			this.getSerializedSize();
			if((this.bitField0_ & 1) == 1) {
				output.writeEnum(1, this.type_.getNumber());
			}

			if((this.bitField0_ & 2) == 2) {
				output.writeBytes(2, this.getSourceBytes());
			}

			if((this.bitField0_ & 8) == 8) {
				output.writeBytes(3, this.getRelayBytes());
			}

			if((this.bitField0_ & 16) == 16) {
				output.writeUInt64(5, this.timestamp_);
			}

			if((this.bitField0_ & 32) == 32) {
				output.writeBytes(6, this.message_);
			}

			if((this.bitField0_ & 4) == 4) {
				output.writeUInt32(7, this.sourceDevice_);
			}

			this.getUnknownFields().writeTo(output);
		}

		public int getSerializedSize() {
			int size = this.memoizedSerializedSize;
			if(size != -1) {
				return size;
			} else {
				size = 0;
				if((this.bitField0_ & 1) == 1) {
					size += CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
				}

				if((this.bitField0_ & 2) == 2) {
					size += CodedOutputStream.computeBytesSize(2, this.getSourceBytes());
				}

				if((this.bitField0_ & 8) == 8) {
					size += CodedOutputStream.computeBytesSize(3, this.getRelayBytes());
				}

				if((this.bitField0_ & 16) == 16) {
					size += CodedOutputStream.computeUInt64Size(5, this.timestamp_);
				}

				if((this.bitField0_ & 32) == 32) {
					size += CodedOutputStream.computeBytesSize(6, this.message_);
				}

				if((this.bitField0_ & 4) == 4) {
					size += CodedOutputStream.computeUInt32Size(7, this.sourceDevice_);
				}

				size += this.getUnknownFields().getSerializedSize();
				this.memoizedSerializedSize = size;
				return size;
			}
		}

		protected Object writeReplace() throws ObjectStreamException {
			return super.writeReplace();
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(ByteString data) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(data);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(data, extensionRegistry);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(byte[] data) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(data);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(data, extensionRegistry);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(InputStream input) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(input);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseDelimitedFrom(InputStream input) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseDelimitedFrom(input);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(CodedInputStream input) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(input);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PushSMPMessageProtos.IncomingPushMessageSignal)PARSER.parseFrom(input, extensionRegistry);
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal.Builder newBuilder() {
			return PushSMPMessageProtos.IncomingPushMessageSignal.Builder.create();
		}

		public PushSMPMessageProtos.IncomingPushMessageSignal.Builder newBuilderForType() {
			return newBuilder();
		}

		public static PushSMPMessageProtos.IncomingPushMessageSignal.Builder newBuilder(PushSMPMessageProtos.IncomingPushMessageSignal prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public PushSMPMessageProtos.IncomingPushMessageSignal.Builder toBuilder() {
			return newBuilder(this);
		}

		protected PushSMPMessageProtos.IncomingPushMessageSignal.Builder newBuilderForType(BuilderParent parent) {
			PushSMPMessageProtos.IncomingPushMessageSignal.Builder builder = new PushSMPMessageProtos.IncomingPushMessageSignal.Builder(parent);
			return builder;
		}

		static {
			defaultInstance.initFields();
		}

		public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<PushSMPMessageProtos.IncomingPushMessageSignal.Builder> implements PushSMPMessageProtos.IncomingPushMessageSignalOrBuilder {
			private int bitField0_;
			private PushSMPMessageProtos.IncomingPushMessageSignal.Type type_;
			private Object source_;
			private int sourceDevice_;
			private Object relay_;
			private long timestamp_;
			private ByteString message_;

			public static final Descriptors.Descriptor getDescriptor() {
				return PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_descriptor;
			}

			protected FieldAccessorTable internalGetFieldAccessorTable() {
				return PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_fieldAccessorTable.ensureFieldAccessorsInitialized(PushSMPMessageProtos.IncomingPushMessageSignal.class, PushSMPMessageProtos.IncomingPushMessageSignal.Builder.class);
			}

			private Builder() {
				this.type_ = PushSMPMessageProtos.IncomingPushMessageSignal.Type.UNKNOWN;
				this.source_ = "";
				this.relay_ = "";
				this.message_ = ByteString.EMPTY;
				this.maybeForceBuilderInitialization();
			}

			private Builder(BuilderParent parent) {
				super(parent);
				this.type_ = PushSMPMessageProtos.IncomingPushMessageSignal.Type.UNKNOWN;
				this.source_ = "";
				this.relay_ = "";
				this.message_ = ByteString.EMPTY;
				this.maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if(PushSMPMessageProtos.IncomingPushMessageSignal.alwaysUseFieldBuilders) {
					;
				}

			}

			private static PushSMPMessageProtos.IncomingPushMessageSignal.Builder create() {
				return new PushSMPMessageProtos.IncomingPushMessageSignal.Builder();
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clear() {
				super.clear();
				this.type_ = PushSMPMessageProtos.IncomingPushMessageSignal.Type.UNKNOWN;
				this.bitField0_ &= -2;
				this.source_ = "";
				this.bitField0_ &= -3;
				this.sourceDevice_ = 0;
				this.bitField0_ &= -5;
				this.relay_ = "";
				this.bitField0_ &= -9;
				this.timestamp_ = 0L;
				this.bitField0_ &= -17;
				this.message_ = ByteString.EMPTY;
				this.bitField0_ &= -33;
				return this;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clone() {
				return create().mergeFrom(this.buildPartial());
			}

			public Descriptors.Descriptor getDescriptorForType() {
				return PushSMPMessageProtos.internal_static_textsecure_IncomingPushMessageSignal_descriptor;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal getDefaultInstanceForType() {
				return PushSMPMessageProtos.IncomingPushMessageSignal.defaultInstance;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal build() {
				PushSMPMessageProtos.IncomingPushMessageSignal result = this.buildPartial();
				if(!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				} else {
					return result;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal buildPartial() {
				PushSMPMessageProtos.IncomingPushMessageSignal result = new PushSMPMessageProtos.IncomingPushMessageSignal(this);
				int from_bitField0_ = this.bitField0_;
				int to_bitField0_ = 0;
				if((from_bitField0_ & 1) == 1) {
					to_bitField0_ |= 1;
				}

				result.type_ = this.type_;
				if((from_bitField0_ & 2) == 2) {
					to_bitField0_ |= 2;
				}

				result.source_ = this.source_;
				if((from_bitField0_ & 4) == 4) {
					to_bitField0_ |= 4;
				}

				result.sourceDevice_ = this.sourceDevice_;
				if((from_bitField0_ & 8) == 8) {
					to_bitField0_ |= 8;
				}

				result.relay_ = this.relay_;
				if((from_bitField0_ & 16) == 16) {
					to_bitField0_ |= 16;
				}

				result.timestamp_ = this.timestamp_;
				if((from_bitField0_ & 32) == 32) {
					to_bitField0_ |= 32;
				}

				result.message_ = this.message_;
				result.bitField0_ = to_bitField0_;
				this.onBuilt();
				return result;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder mergeFrom(Message other) {
				if(other instanceof PushSMPMessageProtos.IncomingPushMessageSignal) {
					return this.mergeFrom((PushSMPMessageProtos.IncomingPushMessageSignal)other);
				} else {
					super.mergeFrom(other);
					return this;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder mergeFrom(PushSMPMessageProtos.IncomingPushMessageSignal other) {
				if(other == PushSMPMessageProtos.IncomingPushMessageSignal.defaultInstance) {
					return this;
				} else {
					if(other.hasType()) {
						this.setType(other.getType());
					}

					if(other.hasSource()) {
						this.bitField0_ |= 2;
						this.source_ = other.source_;
						this.onChanged();
					}

					if(other.hasSourceDevice()) {
						this.setSourceDevice(other.getSourceDevice());
					}

					if(other.hasRelay()) {
						this.bitField0_ |= 8;
						this.relay_ = other.relay_;
						this.onChanged();
					}

					if(other.hasTimestamp()) {
						this.setTimestamp(other.getTimestamp());
					}

					if(other.hasMessage()) {
						this.setMessage(other.getMessage());
					}

					this.mergeUnknownFields(other.getUnknownFields());
					return this;
				}
			}

			public final boolean isInitialized() {
				return true;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				PushSMPMessageProtos.IncomingPushMessageSignal parsedMessage = null;

				try {
					parsedMessage = (PushSMPMessageProtos.IncomingPushMessageSignal)PushSMPMessageProtos.IncomingPushMessageSignal.PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (InvalidProtocolBufferException var8) {
					parsedMessage = (PushSMPMessageProtos.IncomingPushMessageSignal)var8.getUnfinishedMessage();
					throw var8;
				} finally {
					if(parsedMessage != null) {
						this.mergeFrom(parsedMessage);
					}

				}

				return this;
			}

			public boolean hasType() {
				return (this.bitField0_ & 1) == 1;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Type getType() {
				return this.type_;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setType(PushSMPMessageProtos.IncomingPushMessageSignal.Type value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 1;
					this.type_ = value;
					this.onChanged();
					return this;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearType() {
				this.bitField0_ &= -2;
				this.type_ = PushSMPMessageProtos.IncomingPushMessageSignal.Type.UNKNOWN;
				this.onChanged();
				return this;
			}

			public boolean hasSource() {
				return (this.bitField0_ & 2) == 2;
			}

			public String getSource() {
				Object ref = this.source_;
				if(!(ref instanceof String)) {
					String s = ((ByteString)ref).toStringUtf8();
					this.source_ = s;
					return s;
				} else {
					return (String)ref;
				}
			}

			public ByteString getSourceBytes() {
				Object ref = this.source_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.source_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setSource(String value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 2;
					this.source_ = value;
					this.onChanged();
					return this;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearSource() {
				this.bitField0_ &= -3;
				this.source_ = PushSMPMessageProtos.IncomingPushMessageSignal.defaultInstance.getSource();
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setSourceBytes(ByteString value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 2;
					this.source_ = value;
					this.onChanged();
					return this;
				}
			}

			public boolean hasSourceDevice() {
				return (this.bitField0_ & 4) == 4;
			}

			public int getSourceDevice() {
				return this.sourceDevice_;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setSourceDevice(int value) {
				this.bitField0_ |= 4;
				this.sourceDevice_ = value;
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearSourceDevice() {
				this.bitField0_ &= -5;
				this.sourceDevice_ = 0;
				this.onChanged();
				return this;
			}

			public boolean hasRelay() {
				return (this.bitField0_ & 8) == 8;
			}

			public String getRelay() {
				Object ref = this.relay_;
				if(!(ref instanceof String)) {
					String s = ((ByteString)ref).toStringUtf8();
					this.relay_ = s;
					return s;
				} else {
					return (String)ref;
				}
			}

			public ByteString getRelayBytes() {
				Object ref = this.relay_;
				if(ref instanceof String) {
					ByteString b = ByteString.copyFromUtf8((String)ref);
					this.relay_ = b;
					return b;
				} else {
					return (ByteString)ref;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setRelay(String value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 8;
					this.relay_ = value;
					this.onChanged();
					return this;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearRelay() {
				this.bitField0_ &= -9;
				this.relay_ = PushSMPMessageProtos.IncomingPushMessageSignal.defaultInstance.getRelay();
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setRelayBytes(ByteString value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 8;
					this.relay_ = value;
					this.onChanged();
					return this;
				}
			}

			public boolean hasTimestamp() {
				return (this.bitField0_ & 16) == 16;
			}

			public long getTimestamp() {
				return this.timestamp_;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setTimestamp(long value) {
				this.bitField0_ |= 16;
				this.timestamp_ = value;
				this.onChanged();
				return this;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearTimestamp() {
				this.bitField0_ &= -17;
				this.timestamp_ = 0L;
				this.onChanged();
				return this;
			}

			public boolean hasMessage() {
				return (this.bitField0_ & 32) == 32;
			}

			public ByteString getMessage() {
				return this.message_;
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder setMessage(ByteString value) {
				if(value == null) {
					throw new NullPointerException();
				} else {
					this.bitField0_ |= 32;
					this.message_ = value;
					this.onChanged();
					return this;
				}
			}

			public PushSMPMessageProtos.IncomingPushMessageSignal.Builder clearMessage() {
				this.bitField0_ &= -33;
				this.message_ = PushSMPMessageProtos.IncomingPushMessageSignal.defaultInstance.getMessage();
				this.onChanged();
				return this;
			}
		}

		public static enum Type implements ProtocolMessageEnum {
			UNKNOWN(0, 0),
			CIPHERTEXT(1, 1),
			KEY_EXCHANGE(2, 2),
			PREKEY_BUNDLE(3, 3),
			RECEIPT(4, 5);

			public static final int UNKNOWN_VALUE = 0;
			public static final int CIPHERTEXT_VALUE = 1;
			public static final int KEY_EXCHANGE_VALUE = 2;
			public static final int PREKEY_BUNDLE_VALUE = 3;
			public static final int RECEIPT_VALUE = 5;
			private static Internal.EnumLiteMap<Type> internalValueMap;
			private static final PushSMPMessageProtos.IncomingPushMessageSignal.Type[] VALUES;
			private final int index;
			private final int value;

			public final int getNumber() {
				return this.value;
			}

			public static PushSMPMessageProtos.IncomingPushMessageSignal.Type valueOf(int value) {
				switch(value) {
					case 0:
						return UNKNOWN;
					case 1:
						return CIPHERTEXT;
					case 2:
						return KEY_EXCHANGE;
					case 3:
						return PREKEY_BUNDLE;
					case 4:
					default:
						return null;
					case 5:
						return RECEIPT;
				}
			}

			public static Internal.EnumLiteMap<Type> internalGetValueMap() {
				return internalValueMap;
			}

			public final Descriptors.EnumValueDescriptor getValueDescriptor() {
				return (Descriptors.EnumValueDescriptor)getDescriptor().getValues().get(this.index);
			}

			public final Descriptors.EnumDescriptor getDescriptorForType() {
				return getDescriptor();
			}

			public static final Descriptors.EnumDescriptor getDescriptor() {
				return (Descriptors.EnumDescriptor)PushSMPMessageProtos.IncomingPushMessageSignal.getDescriptor().getEnumTypes().get(0);
			}

			public static PushSMPMessageProtos.IncomingPushMessageSignal.Type valueOf(Descriptors.EnumValueDescriptor desc) {
				if(desc.getType() != getDescriptor()) {
					throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
				} else {
					return VALUES[desc.getIndex()];
				}
			}

			private Type(int index, int value) {
				this.index = index;
				this.value = value;
			}

			static {
				internalValueMap = new Internal.EnumLiteMap() {
					public PushSMPMessageProtos.IncomingPushMessageSignal.Type findValueByNumber(int number) {
						return PushSMPMessageProtos.IncomingPushMessageSignal.Type.valueOf(number);
					}
				};
				VALUES = values();
			}
		}
	}

	public interface IncomingPushMessageSignalOrBuilder extends MessageOrBuilder {
		boolean hasType();

		PushSMPMessageProtos.IncomingPushMessageSignal.Type getType();

		boolean hasSource();

		String getSource();

		ByteString getSourceBytes();

		boolean hasSourceDevice();

		int getSourceDevice();

		boolean hasRelay();

		String getRelay();

		ByteString getRelayBytes();

		boolean hasTimestamp();

		long getTimestamp();

		boolean hasMessage();

		ByteString getMessage();
	}
}