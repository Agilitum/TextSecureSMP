package org.thoughtcrime.SMP.crypto.SMP;

import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;

import org.whispersystems.libaxolotl.AxolotlAddress;
import org.whispersystems.libaxolotl.DuplicateMessageException;
import org.whispersystems.libaxolotl.InvalidKeyException;
import org.whispersystems.libaxolotl.InvalidKeyIdException;
import org.whispersystems.libaxolotl.InvalidMessageException;
import org.whispersystems.libaxolotl.InvalidVersionException;
import org.whispersystems.libaxolotl.LegacyMessageException;
import org.whispersystems.libaxolotl.NoSessionException;
import org.whispersystems.libaxolotl.SessionCipher;
import org.whispersystems.libaxolotl.UntrustedIdentityException;
import org.whispersystems.libaxolotl.protocol.CiphertextMessage;
import org.whispersystems.libaxolotl.protocol.PreKeyWhisperMessage;
import org.whispersystems.libaxolotl.protocol.WhisperMessage;
import org.whispersystems.libaxolotl.state.AxolotlStore;
import org.whispersystems.textsecure.api.crypto.TextSecureCipher;
import org.whispersystems.textsecure.api.messages.TextSecureAttachmentPointer;
import org.whispersystems.textsecure.api.messages.TextSecureEnvelope;
import org.whispersystems.textsecure.api.messages.TextSecureGroup;
import org.whispersystems.textsecure.api.messages.TextSecureSyncContext;
import org.whispersystems.textsecure.api.push.TextSecureAddress;
import org.whispersystems.textsecure.internal.push.OutgoingPushMessage;
import org.whispersystems.textsecure.internal.push.PushMessageProtos;
import org.whispersystems.textsecure.internal.push.PushTransportDetails;
import org.whispersystems.textsecure.internal.util.Base64;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ludwig on 09/07/15.
 */
public class TextSecureSMPCipher extends TextSecureCipher {

	private static final String TAG = TextSecureSMPCipher.class.getSimpleName();

	private final AxolotlStore axolotlStore;
	private final TextSecureAddress localAddress;

	public TextSecureSMPCipher (TextSecureAddress localAddress, AxolotlStore axolotlStore) {
		super(localAddress, axolotlStore);
		this.axolotlStore = axolotlStore;
		this.localAddress = localAddress;
	}

	public OutgoingPushMessage encrypt(AxolotlAddress destination, byte[] unpaddedMessage) {
		SessionCipher sessionCipher = new SessionCipher(this.axolotlStore, destination);
		PushTransportDetails transportDetails = new PushTransportDetails(sessionCipher.getSessionVersion());
		CiphertextMessage message = sessionCipher.encrypt(transportDetails.getPaddedMessageBody(unpaddedMessage));
		int remoteRegistrationId = sessionCipher.getRemoteRegistrationId();
		String body = Base64.encodeBytes(message.serialize());
		byte type;
		switch(message.getType()) {
			case 2:
				type = 1;
				break;
			case 3:
				type = 3;
				break;
			default:
				throw new AssertionError("Bad type: " + message.getType());
		}

		return new OutgoingPushMessage(type, destination.getDeviceId(), remoteRegistrationId, body);
	}

	public TextSecureSMPMessage decrypt(TextSecureEnvelope envelope) throws InvalidVersionException,
		InvalidMessageException, InvalidKeyException, DuplicateMessageException, InvalidKeyIdException, UntrustedIdentityException, LegacyMessageException, NoSessionException {
		try {
			AxolotlAddress e = new AxolotlAddress(envelope.getSource(), envelope.getSourceDevice());
			SessionCipher sessionCipher = new SessionCipher(this.axolotlStore, e);
			byte[] paddedMessage;
			if(envelope.isPreKeyWhisperMessage()) {
				paddedMessage = sessionCipher.decrypt(new PreKeyWhisperMessage(envelope.getMessage()));
			} else {
				if(!envelope.isWhisperMessage()) {
					throw new InvalidMessageException("Unknown type: " + envelope.getType());
				}

				paddedMessage = sessionCipher.decrypt(new WhisperMessage(envelope.getMessage()));
			}

			PushTransportDetails transportDetails = new PushTransportDetails(sessionCipher.getSessionVersion());
			PushMessageProtos.PushMessageContent content = PushMessageProtos.PushMessageContent.parseFrom(transportDetails.getStrippedPaddingMessageBody(paddedMessage));
			return this.createTextSecureSMPMessage(envelope, content);
		} catch (InvalidProtocolBufferException var7) {
			throw new InvalidMessageException(var7);
		}
	}

	private TextSecureSMPMessage createTextSecureSMPMessage(TextSecureEnvelope envelope, PushMessageProtos.PushMessageContent content) {
		TextSecureGroup groupInfo = this.createGroupInfo(envelope, content);
		TextSecureSyncContext syncContext = this.createSyncContext(envelope, content);

		//TODO: needs proper flags
		boolean smp = content.hasSync();
		boolean smpSync = true;

		Log.d(TAG, "createTextSecureSMPMessage hasSync: " + content.hasSync());
		LinkedList attachments = new LinkedList();
		boolean endSession = (content.getFlags() & 1) != 0;
		Iterator var7 = content.getAttachmentsList().iterator();

		while(var7.hasNext()) {
			PushMessageProtos.PushMessageContent.AttachmentPointer pointer = (PushMessageProtos.PushMessageContent.AttachmentPointer)var7.next();
			attachments.add(new TextSecureAttachmentPointer(pointer.getId(), pointer.getContentType(), pointer.getKey().toByteArray(), envelope.getRelay()));
		}

		return new TextSecureSMPMessage(envelope.getTimestamp(), groupInfo, attachments, content.getBody(), syncContext, endSession, smp, smpSync);
	}

	private TextSecureSyncContext createSyncContext(TextSecureEnvelope envelope, PushMessageProtos.PushMessageContent content) {
		return !content.hasSync()?null:(!envelope.getSource().equals(this.localAddress.getNumber())?null:new TextSecureSyncContext(content.getSync().getDestination(), content.getSync().getTimestamp()));
	}

	private TextSecureGroup createGroupInfo(TextSecureEnvelope envelope, PushMessageProtos.PushMessageContent content) {
		if(!content.hasGroup()) {
			return null;
		} else {
			TextSecureGroup.Type type;
			switch(content.getGroup().getType()) {
				case DELIVER:
					type = TextSecureGroup.Type.DELIVER;
					break;
				case UPDATE:
					type = TextSecureGroup.Type.UPDATE;
					break;
				case QUIT:
					type = TextSecureGroup.Type.QUIT;
					break;
				default:
					type = TextSecureGroup.Type.UNKNOWN;
			}

			if(content.getGroup().getType() != org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext.Type.DELIVER) {
				String name = null;
				List members = null;
				TextSecureAttachmentPointer avatar = null;
				if(content.getGroup().hasName()) {
					name = content.getGroup().getName();
				}

				if(content.getGroup().getMembersCount() > 0) {
					members = content.getGroup().getMembersList();
				}

				if(content.getGroup().hasAvatar()) {
					avatar = new TextSecureAttachmentPointer(content.getGroup().getAvatar().getId(), content.getGroup().getAvatar().getContentType(), content.getGroup().getAvatar().getKey().toByteArray(), envelope.getRelay());
				}

				return new TextSecureGroup(type, content.getGroup().getId().toByteArray(), name, members, avatar);
			} else {
				return new TextSecureGroup(content.getGroup().getId().toByteArray());
			}
		}
	}
}
