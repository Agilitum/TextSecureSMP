package org.thoughtcrime.SMP.crypto.SMP;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import org.whispersystems.libaxolotl.AxolotlAddress;
import org.whispersystems.libaxolotl.InvalidKeyException;
import org.whispersystems.libaxolotl.SessionBuilder;
import org.whispersystems.libaxolotl.logging.Log;
import org.whispersystems.libaxolotl.state.AxolotlStore;
import org.whispersystems.libaxolotl.state.PreKeyBundle;
import org.whispersystems.libaxolotl.util.guava.Optional;
import org.whispersystems.textsecure.api.crypto.TextSecureCipher;
import org.whispersystems.textsecure.api.crypto.UntrustedIdentityException;
import org.whispersystems.textsecure.api.messages.TextSecureAttachment;
import org.whispersystems.textsecure.api.messages.TextSecureAttachmentStream;
import org.whispersystems.textsecure.api.messages.TextSecureGroup;
import org.whispersystems.textsecure.api.push.TextSecureAddress;
import org.whispersystems.textsecure.api.push.TrustStore;
import org.whispersystems.textsecure.api.push.exceptions.EncapsulatedExceptions;
import org.whispersystems.textsecure.api.push.exceptions.NetworkFailureException;
import org.whispersystems.textsecure.api.push.exceptions.PushNetworkException;
import org.whispersystems.textsecure.api.push.exceptions.UnregisteredUserException;
import org.whispersystems.textsecure.internal.push.MismatchedDevices;
import org.whispersystems.textsecure.internal.push.OutgoingPushMessage;
import org.whispersystems.textsecure.internal.push.OutgoingPushMessageList;
import org.whispersystems.textsecure.internal.push.PushAttachmentData;
import org.whispersystems.textsecure.internal.push.PushMessageProtos;
import org.whispersystems.textsecure.internal.push.PushServiceSocket;
import org.whispersystems.textsecure.internal.push.SendMessageResponse;
import org.whispersystems.textsecure.internal.push.StaleDevices;
import org.whispersystems.textsecure.internal.push.exceptions.MismatchedDevicesException;
import org.whispersystems.textsecure.internal.push.exceptions.StaleDevicesException;
import org.whispersystems.textsecure.internal.util.StaticCredentialsProvider;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ludwig on 19/07/15.
 */
public class TextSecureSMPMessageSender {

	private static final String TAG = TextSecureSMPMessageSender.class.getSimpleName();
	private final PushServiceSocket socket;
	private final AxolotlStore store;
	private final TextSecureAddress localAddress;
	private final Optional<TextSecureSMPMessageSender.EventListener> eventListener;

	public TextSecureSMPMessageSender(String url, TrustStore trustStore, String user, String
		password, AxolotlStore store, Optional<EventListener> eventListener) {
		this.socket = new PushServiceSocket(url, trustStore, new StaticCredentialsProvider(user, password, (String)null));
		this.store = store;
		this.localAddress = new TextSecureAddress(user);
		this.eventListener = eventListener;
	}

	public void sendMessage(TextSecureAddress recipient, TextSecureSMPMessage message)
		throws
		UntrustedIdentityException, IOException {
		Log.d(TAG, "sendMessage() check for SMPSync true: " + message.isSMPSyncMessage());
		byte[] content = this.createMessageContent(message);
		long timestamp = message.getTimestamp();
/*
		//TODO: figured this out ... still what triggers the true response?!!?
		SendMessageResponse response = new SendMessageResponse(true); // this.sendMessage(recipient,
		// timestamp, content);
		Log.d(TAG, "SendMessageResponse.needsSYC: " + response.getNeedsSync());

		// TODO: adapt to SMP here & change to "isSMPSyncMessage()"
		Log.d(TAG, "isSMPMessage: " + message.isSMPMessage());
		if(response != null && response.getNeedsSync()) {
			byte[] syncSMPMessage = this.createSyncSMPMessageContent(content, Optional.of(recipient),
				timestamp);
			this.sendMessage(this.localAddress, timestamp, syncSMPMessage);
			Log.d(TAG, "sendSyncMessage");
		}

		if(message.isEndSession()) {
			this.store.deleteAllSessions(recipient.getNumber());
			if(this.eventListener.isPresent()) {
				(this.eventListener.get()).onSecurityEvent(recipient);
			}
		}
*/
		// TODO: adapt to SMP here & change to "isSMPSyncMessage()"
		Log.d(TAG, "isSMPMessage: " + message.isSMPMessage());
		Log.d(TAG, "isSMPSyncMessage: " + message.isSMPSyncMessage());
		if(message.isSMPSyncMessage()) {
			byte[] syncSMPMessage = this.createSyncSMPMessageContent(content, Optional.of(recipient),
				timestamp);
			Log.d(TAG, "sendsSyncMessage");
			this.sendMessage(recipient, timestamp, syncSMPMessage);
		} else {
			Log.d(TAG, "sendsSMPMessage");
			this.sendMessage(recipient, timestamp, content);
		}
	}

	private byte[] createMessageContent(TextSecureSMPMessage message) throws IOException {
		PushMessageProtos.PushMessageContent.Builder builder = PushMessageProtos.PushMessageContent.newBuilder();
		List pointers = this.createAttachmentPointers(message.getAttachments());
		if(!pointers.isEmpty()) {
			builder.addAllAttachments(pointers);
		}

		if(message.getBody().isPresent()) {
			Log.d(TAG, "createMessageContent getBody.isPresent()");
			builder.setBody((String)message.getBody().get());
		}

		if(message.getGroupInfo().isPresent()) {
			builder.setGroup(this.createGroupContent(message.getGroupInfo().get()));
		}

		if(message.isEndSession()) {
			builder.setFlags(1);
		}

		if(message.isSMPSyncMessage()){
			Log.d(TAG, "createMessageContent.isSMPSyncMessage: " + message.isSMPSyncMessage());
			builder.setBody((String)message.getBody().get());

		}

		return builder.build().toByteArray();
	}

	private SendMessageResponse sendMessage(TextSecureAddress recipient, long timestamp, byte[] content) throws UntrustedIdentityException, IOException {
		for(int i = 0; i < 3; ++i) {
			try {
				OutgoingPushMessageList ste = this.getEncryptedMessages(this.socket, recipient, timestamp, content);

				return this.socket.sendMessage(ste);
			} catch (MismatchedDevicesException var7) {
				Log.w(TAG, var7);
				this.handleMismatchedDevices(this.socket, recipient, var7.getMismatchedDevices());
			} catch (StaleDevicesException var8) {
				Log.w(TAG, var8);
				this.handleStaleDevices(recipient, var8.getStaleDevices());
			}
		}

		throw new IOException("Failed to resolve conflicts after 3 attempts!");
	}

	private byte[] createSyncSMPMessageContent(byte[] content, Optional<TextSecureAddress> recipient,
	                                         long timestamp) {
		try {
			org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.SyncMessageContext.Builder e = PushMessageProtos.PushMessageContent.SyncMessageContext.newBuilder();
			e.setTimestamp(timestamp);
			if(recipient.isPresent()) {
				Log.d(TAG, "createSynSMPMessageContent recipient: " + recipient.get().getNumber());
				e.setDestination((recipient.get()).getNumber());
			}

			Log.d(TAG, "createSyncSMPMessageContent SyncMessageContext.Builder: " + e
				.getDescriptorForType().getFullName());
			PushMessageProtos.PushMessageContent.Builder builder = PushMessageProtos.PushMessageContent.parseFrom(content).toBuilder();

			builder.setSync(e.build());
			Log.d(TAG, "createSyncSMPMessageContent: " + builder.getSync());

			return builder.build().toByteArray();
		} catch (InvalidProtocolBufferException var7) {
			throw new AssertionError(var7);
		}
	}

	private List<PushMessageProtos.PushMessageContent.AttachmentPointer> createAttachmentPointers(Optional<List<TextSecureAttachment>> attachments) throws IOException {
		LinkedList pointers = new LinkedList();
		if(attachments.isPresent() && !((List)attachments.get()).isEmpty()) {
			Iterator var3 = ((List)attachments.get()).iterator();

			while(var3.hasNext()) {
				TextSecureAttachment attachment = (TextSecureAttachment)var3.next();
				if(attachment.isStream()) {
					Log.w(TAG, "Found attachment, creating pointer...");
					pointers.add(this.createAttachmentPointer(attachment.asStream()));
				}
			}

			return pointers;
		} else {
			Log.w(TAG, "No attachments present...");
			return pointers;
		}
	}

	private PushMessageProtos.PushMessageContent.AttachmentPointer createAttachmentPointer(TextSecureAttachmentStream attachment) throws IOException {
		byte[] attachmentKey = org.whispersystems.textsecure.internal.util.Util.getSecretBytes(64);
		PushAttachmentData attachmentData = new PushAttachmentData(attachment.getContentType(), attachment.getInputStream(), attachment.getLength(), attachmentKey);
		long attachmentId = this.socket.sendAttachment(attachmentData);
		return PushMessageProtos.PushMessageContent.AttachmentPointer.newBuilder().setContentType(attachment.getContentType()).setId(attachmentId).setKey(ByteString.copyFrom(attachmentKey)).build();
	}

	private PushMessageProtos.PushMessageContent.GroupContext createGroupContent(TextSecureGroup group) throws IOException {
		org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext.Builder builder = PushMessageProtos.PushMessageContent.GroupContext.newBuilder();
		builder.setId(ByteString.copyFrom(group.getGroupId()));
		if(group.getType() != TextSecureGroup.Type.DELIVER) {
			if(group.getType() == TextSecureGroup.Type.UPDATE) {
				builder.setType(org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext.Type.UPDATE);
			} else {
				if(group.getType() != TextSecureGroup.Type.QUIT) {
					throw new AssertionError("Unknown type: " + group.getType());
				}

				builder.setType(org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext.Type.QUIT);
			}

			if(group.getName().isPresent()) {
				builder.setName((String)group.getName().get());
			}

			if(group.getMembers().isPresent()) {
				builder.addAllMembers((Iterable)group.getMembers().get());
			}

			if(group.getAvatar().isPresent() && ((TextSecureAttachment)group.getAvatar().get()).isStream()) {
				PushMessageProtos.PushMessageContent.AttachmentPointer pointer = this.createAttachmentPointer(((TextSecureAttachment)group.getAvatar().get()).asStream());
				builder.setAvatar(pointer);
			}
		} else {
			builder.setType(org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext.Type.DELIVER);
		}

		return builder.build();
	}

	private SendMessageResponse sendMessage(List<TextSecureAddress> recipients, long timestamp, byte[] content) throws IOException, EncapsulatedExceptions {
		LinkedList untrustedIdentities = new LinkedList();
		LinkedList unregisteredUsers = new LinkedList();
		LinkedList networkExceptions = new LinkedList();
		SendMessageResponse response = null;
		Iterator var9 = recipients.iterator();

		while(var9.hasNext()) {
			TextSecureAddress recipient = (TextSecureAddress)var9.next();

			try {
				response = this.sendMessage(recipient, timestamp, content);
			} catch (UntrustedIdentityException var12) {
				Log.w(TAG, var12);
				untrustedIdentities.add(var12);
			} catch (UnregisteredUserException var13) {
				Log.w(TAG, var13);
				unregisteredUsers.add(var13);
			} catch (PushNetworkException var14) {
				Log.w(TAG, var14);
				networkExceptions.add(new NetworkFailureException(recipient.getNumber(), var14));
			}
		}

		if(untrustedIdentities.isEmpty() && unregisteredUsers.isEmpty() && networkExceptions.isEmpty()) {
			return response;
		} else {
			throw new EncapsulatedExceptions(untrustedIdentities, unregisteredUsers, networkExceptions);
		}
	}

	private OutgoingPushMessageList getEncryptedMessages(PushServiceSocket socket, TextSecureAddress recipient, long timestamp, byte[] plaintext) throws IOException, UntrustedIdentityException {
		LinkedList messages = new LinkedList();
		if(!recipient.equals(this.localAddress)) {
			Log.d(TAG, "recipient != localAddress");
			messages.add(this.getEncryptedMessage(socket, recipient, 1, plaintext));
		}

		Log.d(TAG, "recipient == localAddress");
		Iterator var7 = this.store.getSubDeviceSessions(recipient.getNumber()).iterator();

		while(var7.hasNext()) {
			int deviceId = ((Integer)var7.next()).intValue();
			messages.add(this.getEncryptedMessage(socket, recipient, deviceId, plaintext));
		}

		return new OutgoingPushMessageList(recipient.getNumber(), timestamp, (String)recipient.getRelay().orNull(), messages);
	}

	private OutgoingPushMessage getEncryptedMessage(PushServiceSocket socket, TextSecureAddress recipient, int deviceId, byte[] plaintext) throws IOException, UntrustedIdentityException {
		AxolotlAddress axolotlAddress = new AxolotlAddress(recipient.getNumber(), deviceId);
		TextSecureCipher cipher = new TextSecureCipher(this.localAddress, this.store);
		if(!this.store.containsSession(axolotlAddress)) {
			try {
				List e = socket.getPreKeys(recipient, deviceId);
				Iterator var8 = e.iterator();

				while(var8.hasNext()) {
					PreKeyBundle preKey = (PreKeyBundle)var8.next();

					try {
						AxolotlAddress e1 = new AxolotlAddress(recipient.getNumber(), preKey.getDeviceId());
						SessionBuilder sessionBuilder = new SessionBuilder(this.store, e1);
						sessionBuilder.process(preKey);
					} catch (org.whispersystems.libaxolotl.UntrustedIdentityException var12) {
						throw new UntrustedIdentityException("Untrusted identity key!", recipient.getNumber(), preKey.getIdentityKey());
					}
				}

				if(this.eventListener.isPresent()) {
					(this.eventListener.get()).onSecurityEvent(recipient);
				}
			} catch (InvalidKeyException var13) {
				throw new IOException(var13);
			}
		}

		return cipher.encrypt(axolotlAddress, plaintext);
	}

	private void handleMismatchedDevices(PushServiceSocket socket, TextSecureAddress recipient, MismatchedDevices mismatchedDevices) throws IOException, UntrustedIdentityException {
		try {
			Iterator e = mismatchedDevices.getExtraDevices().iterator();

			int missingDeviceId;
			while(e.hasNext()) {
				missingDeviceId = ((Integer)e.next()).intValue();
				this.store.deleteSession(new AxolotlAddress(recipient.getNumber(), missingDeviceId));
			}

			e = mismatchedDevices.getMissingDevices().iterator();

			while(e.hasNext()) {
				missingDeviceId = ((Integer)e.next()).intValue();
				PreKeyBundle preKey = socket.getPreKey(recipient, missingDeviceId);

				try {
					SessionBuilder e1 = new SessionBuilder(this.store, new AxolotlAddress(recipient.getNumber(), missingDeviceId));
					e1.process(preKey);
				} catch (org.whispersystems.libaxolotl.UntrustedIdentityException var8) {
					throw new UntrustedIdentityException("Untrusted identity key!", recipient.getNumber(), preKey.getIdentityKey());
				}
			}

		} catch (InvalidKeyException var9) {
			throw new IOException(var9);
		}
	}

	private void handleStaleDevices(TextSecureAddress recipient, StaleDevices staleDevices) {
		Iterator var3 = staleDevices.getStaleDevices().iterator();

		while(var3.hasNext()) {
			int staleDeviceId = ((Integer)var3.next()).intValue();
			this.store.deleteSession(new AxolotlAddress(recipient.getNumber(), staleDeviceId));
		}

	}

	public interface EventListener {
		void onSecurityEvent(TextSecureAddress var1);
	}

}
