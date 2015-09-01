package org.thoughtcrime.SMP.crypto.SMP;
/*
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;

/**
 * @author George Politis
 */
public class SerializationUtils {

	/*
	// Mysterious X IO.
	public static org.thoughtcrime.SMP.crypto.SMP.messages.SignatureX toMysteriousX(byte[] b) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		SMPInputStream ois = new SMPInputStream(in);
		org.thoughtcrime.SMP.crypto.SMP.messages.SignatureX x = ois.readMysteriousX();
		ois.close();
		return x;
	}

	public static byte[] toByteArray(org.thoughtcrime.SMP.crypto.SMP.messages.SignatureX x) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SMPOutputStream oos = new SMPOutputStream(out);
		oos.writeMysteriousX(x);
		byte[] b = out.toByteArray();
		oos.close();
		return b;
	}

	// Mysterious M IO.
	public static byte[] toByteArray(org.thoughtcrime.SMP.crypto.SMP.messages.SignatureM m) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SMPOutputStream oos = new SMPOutputStream(out);
		oos.writeMysteriousX(m);
		byte[] b = out.toByteArray();
		oos.close();
		return b;
	}

	// Mysterious T IO.
	public static byte[] toByteArray(org.thoughtcrime.SMP.crypto.SMP.messages.MysteriousT t) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SMPOutputStream oos = new SMPOutputStream(out);
		oos.writeMysteriousT(t);
		byte[] b = out.toByteArray();
		oos.close();
		return b;
	}
*/
	// Basic IO.
	public static byte[] writeData(byte[] b) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SMPOutputStream sos = new SMPOutputStream(out);
		sos.writeData(b);
		byte[] otrb = out.toByteArray();
		sos.close();
		return otrb;
	}

	// BigInteger IO.
	public static byte[] writeMpi(BigInteger bigInt) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		SMPOutputStream sos = new SMPOutputStream(out);
		sos.writeBigInt(bigInt);
		byte[] b = out.toByteArray();
		sos.close();
		return b;
	}

	public static BigInteger readMpi(byte[] b) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		SMPInputStream sis = new SMPInputStream(in);
		BigInteger bigint = sis.readBigInt();
		sis.close();
		return bigint;
	}

		// Public Key IO.
		public static byte[] writePublicKey(PublicKey pubKey) throws IOException {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			SMPOutputStream sos = new SMPOutputStream(out);
			sos.writePublicKey(pubKey);
			byte[] b = out.toByteArray();
			sos.close();
			return b;
		}
	/*
				// Message IO.
				public static String toString(AbstractMessage m) throws IOException {
					StringWriter writer = new StringWriter();
					if (m.messageType != org.thoughtcrime.SMP.crypto.SMP.messages.AbstractMessage.MESSAGE_PLAINTEXT)
						writer.write(SerializationConstants.HEAD);
					switch (m.messageType) {
						case AbstractMessage.MESSAGE_ERROR:
							org.thoughtcrime.SMP.crypto.SMP.messages.ErrorMessage error = (org.thoughtcrime.SMP.crypto.SMP.messages.ErrorMessage) m;
							writer.write(SerializationConstants.HEAD_ERROR);
							writer.write(SerializationConstants.ERROR_PREFIX);
							writer.write(error.error);
							break;
						case AbstractMessage.MESSAGE_PLAINTEXT:
							org.thoughtcrime.SMP.crypto.SMP.messages.PlainTextMessage plaintxt = (org.thoughtcrime.SMP.crypto.SMP.messages.PlainTextMessage) m;
							writer.write(plaintxt.cleanText);
							if (plaintxt.versions != null && plaintxt.versions.size() > 0) {
								writer.write(" \t  \t\t\t\t \t \t \t  ");
								for (int version : plaintxt.versions) {
									if (version == OTRv.ONE)
										writer.write(" \t \t  \t ");

									if (version == OTRv.TWO)
										writer.write("  \t\t  \t ");

									if (version == OTRv.THREE)
										writer.write("  \t\t  \t\t");
								}
							}
							break;
						case AbstractMessage.MESSAGE_QUERY:
							org.thoughtcrime.SMP.crypto.SMP.messages.QueryMessage query = (org.thoughtcrime.SMP.crypto.SMP.messages.QueryMessage) m;
							if (query.versions.size() == 1 && query.versions.get(0) == 1) {
								writer.write(SerializationConstants.HEAD_QUERY_Q);
							} else {
								writer.write(SerializationConstants.HEAD_QUERY_V);
								for (int version : query.versions)
									writer.write(String.valueOf(version));

								writer.write(SerializationConstants.HEAD_QUERY_Q);
							}
							break;
						case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DHKEY:
						case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_REVEALSIG:
						case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_SIGNATURE:
						case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DH_COMMIT:
						case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DATA:
							ByteArrayOutputStream o = new ByteArrayOutputStream();
							SMPOutputStream s = new SMPOutputStream(o);

							switch (m.messageType) {
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DHKEY:
									org.thoughtcrime.SMP.crypto.SMP.messages.DHKeyMessage dhkey = (org.thoughtcrime.SMP.crypto.SMP.messages.DHKeyMessage) m;
									s.writeShort(dhkey.protocolVersion);
									s.writeByte(dhkey.messageType);
									if (dhkey.protocolVersion == OTRv.THREE) {
										s.writeInt(dhkey.senderInstanceTag);
										s.writeInt(dhkey.receiverInstanceTag);
									}
									s.writeDHPublicKey(dhkey.dhPublicKey);
									break;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_REVEALSIG:
									org.thoughtcrime.SMP.crypto.SMP.messages.RevealSignatureMessage revealsig = (org.thoughtcrime.SMP.crypto.SMP.messages.RevealSignatureMessage) m;
									s.writeShort(revealsig.protocolVersion);
									s.writeByte(revealsig.messageType);
									if (revealsig.protocolVersion == OTRv.THREE) {
										s.writeInt(revealsig.senderInstanceTag);
										s.writeInt(revealsig.receiverInstanceTag);
									}
									s.writeData(revealsig.revealedKey);
									s.writeData(revealsig.xEncrypted);
									s.writeMac(revealsig.xEncryptedMAC);
									break;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_SIGNATURE:
									org.thoughtcrime.SMP.crypto.SMP.messages.SignatureMessage sig = (org.thoughtcrime.SMP.crypto.SMP.messages.SignatureMessage) m;
									s.writeShort(sig.protocolVersion);
									s.writeByte(sig.messageType);
									if (sig.protocolVersion == OTRv.THREE) {
										s.writeInt(sig.senderInstanceTag);
										s.writeInt(sig.receiverInstanceTag);
									}
									s.writeData(sig.xEncrypted);
									s.writeMac(sig.xEncryptedMAC);
									break;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DH_COMMIT:
									org.thoughtcrime.SMP.crypto.SMP.messages.DHCommitMessage dhcommit = (org.thoughtcrime.SMP.crypto.SMP.messages.DHCommitMessage) m;
									s.writeShort(dhcommit.protocolVersion);
									s.writeByte(dhcommit.messageType);
									if (dhcommit.protocolVersion == OTRv.THREE) {
										s.writeInt(dhcommit.senderInstanceTag);
										s.writeInt(dhcommit.receiverInstanceTag);
									}
									s.writeData(dhcommit.dhPublicKeyEncrypted);
									s.writeData(dhcommit.dhPublicKeyHash);
									break;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DATA:
									org.thoughtcrime.SMP.crypto.SMP.messages.DataMessage data = (org.thoughtcrime.SMP.crypto.SMP.messages.DataMessage) m;
									s.writeShort(data.protocolVersion);
									s.writeByte(data.messageType);
									if (data.protocolVersion == OTRv.THREE) {
										s.writeInt(data.senderInstanceTag);
										s.writeInt(data.receiverInstanceTag);
									}
									s.writeByte(data.flags);
									s.writeInt(data.senderKeyID);
									s.writeInt(data.recipientKeyID);
									s.writeDHPublicKey(data.nextDH);
									s.writeCtr(data.ctr);
									s.writeData(data.encryptedMessage);
									s.writeMac(data.mac);
									s.writeData(data.oldMACKeys);
									break;
							}

							writer.write(SerializationConstants.HEAD_ENCODED);
							writer.write(new String(Base64.encode(o.toByteArray())));
							writer.write(".");
							break;
						default:
							throw new IOException("Illegal message type.");
					}

					return writer.toString();
				}

				static final Pattern patternWhitespace = Pattern
					.compile("( \\t  \\t\\t\\t\\t \\t \\t \\t  )( \\t \\t  \\t )?(  \\t\\t  \\t )?(  \\t\\t  \\t\\t)?");

				public static AbstractMessage toMessage(String s) throws IOException {
					if (s == null || s.length() == 0)
						return null;

					int idxHead = s.indexOf(SerializationConstants.HEAD);
					if (idxHead > -1) {
						// Message **contains** the string "?OTR". Check to see if it is an error message, a query message or a data
						// message.

						char contentType = s.charAt(idxHead + SerializationConstants.HEAD.length());
						String content = s
							.substring(idxHead + SerializationConstants.HEAD.length() + 1);

						if (contentType == SerializationConstants.HEAD_ERROR
							&& content.startsWith(SerializationConstants.ERROR_PREFIX)) {
							// Error tag found.

							content = content.substring(idxHead + SerializationConstants.ERROR_PREFIX
								.length());
							return new org.thoughtcrime.SMP.crypto.SMP.messages.ErrorMessage(org.thoughtcrime.SMP.crypto.SMP.messages.AbstractMessage.MESSAGE_ERROR, content);
						} else if (contentType == SerializationConstants.HEAD_QUERY_V
							|| contentType == SerializationConstants.HEAD_QUERY_Q) {
							// Query tag found.

							List<Integer> versions = new Vector<Integer>();
							String versionString = null;
							if (SerializationConstants.HEAD_QUERY_Q == contentType) {
								versions.add(OTRv.ONE);
								if (content.charAt(0) == 'v') {
									versionString = content.substring(1, content
										.indexOf('?'));
								}
							} else if (SerializationConstants.HEAD_QUERY_V == contentType) {
								versionString = content.substring(0, content.indexOf('?'));
							}

							if (versionString != null) {
								StringReader sr = new StringReader(versionString);
								int c;
								while ((c = sr.read()) != -1)
									if (!versions.contains(c))
										versions.add(Integer.parseInt(String
											.valueOf((char) c)));
							}
							org.thoughtcrime.SMP.crypto.SMP.messages.QueryMessage query = new org.thoughtcrime.SMP.crypto.SMP.messages.QueryMessage(versions);
							return query;
						} else if (idxHead == 0 && contentType == SerializationConstants.HEAD_ENCODED) {
							// Data message found.


							 // BC 1.48 added a check to throw an exception if a non-base64 character is encountered.
							 // An OTR message consists of ?OTR:AbcDefFe. (note the terminating point).
							 // Otr4j doesn't strip this point before passing the content to the base64 decoder.
							 // So in order to decode the content string we have to get rid of the '.' first.

							ByteArrayInputStream bin = new ByteArrayInputStream(Base64
								.decode(content.substring(0, content.length() - 1).getBytes()));
							SMPInputStream otr = new SMPInputStream(bin);
							// We have an encoded message.
							int protocolVersion = otr.readShort();
							int messageType = otr.readByte();
							int senderInstanceTag = 0;
							int recipientInstanceTag = 0;
							if (protocolVersion == OTRv.THREE) {
								senderInstanceTag = otr.readInt();
								recipientInstanceTag = otr.readInt();
							}
							switch (messageType) {
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DATA:
									int flags = otr.readByte();
									int senderKeyID = otr.readInt();
									int recipientKeyID = otr.readInt();
									DHPublicKey nextDH = otr.readDHPublicKey();
									byte[] ctr = otr.readCtr();
									byte[] encryptedMessage = otr.readData();
									byte[] mac = otr.readMac();
									byte[] oldMacKeys = otr.readMac();
									org.thoughtcrime.SMP.crypto.SMP.messages.DataMessage dataMessage =
										new org.thoughtcrime.SMP.crypto.SMP.messages.DataMessage(protocolVersion, flags, senderKeyID,
											recipientKeyID, nextDH, ctr, encryptedMessage, mac,
											oldMacKeys);
									dataMessage.senderInstanceTag = senderInstanceTag;
									dataMessage.receiverInstanceTag = recipientInstanceTag;
									otr.close();
									return dataMessage;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DH_COMMIT:
									byte[] dhPublicKeyEncrypted = otr.readData();
									byte[] dhPublicKeyHash = otr.readData();
									org.thoughtcrime.SMP.crypto.SMP.messages.DHCommitMessage dhCommitMessage =
										new org.thoughtcrime.SMP.crypto.SMP.messages.DHCommitMessage(protocolVersion,
											dhPublicKeyHash, dhPublicKeyEncrypted);
									dhCommitMessage.senderInstanceTag = senderInstanceTag;
									dhCommitMessage.receiverInstanceTag = recipientInstanceTag;
									otr.close();
									return dhCommitMessage;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_DHKEY:
									DHPublicKey dhPublicKey = otr.readDHPublicKey();
									org.thoughtcrime.SMP.crypto.SMP.messages.DHKeyMessage dhKeyMessage = new org.thoughtcrime.SMP.crypto.SMP.messages.DHKeyMessage(protocolVersion, dhPublicKey);
									dhKeyMessage.senderInstanceTag = senderInstanceTag;
									dhKeyMessage.receiverInstanceTag = recipientInstanceTag;
									otr.close();
									return dhKeyMessage;
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_REVEALSIG: {
									byte[] revealedKey = otr.readData();
									byte[] xEncrypted = otr.readData();
									byte[] xEncryptedMac = otr.readMac();
									org.thoughtcrime.SMP.crypto.SMP.messages.RevealSignatureMessage revealSignatureMessage =
										new org.thoughtcrime.SMP.crypto.SMP.messages.RevealSignatureMessage(protocolVersion,
											xEncrypted, xEncryptedMac, revealedKey);
									revealSignatureMessage.senderInstanceTag = senderInstanceTag;
									revealSignatureMessage.receiverInstanceTag = recipientInstanceTag;
									otr.close();
									return revealSignatureMessage;
								}
								case org.thoughtcrime.SMP.crypto.SMP.messages.AbstractEncodedMessage.MESSAGE_SIGNATURE: {
									byte[] xEncryted = otr.readData();
									byte[] xEncryptedMac = otr.readMac();
									org.thoughtcrime.SMP.crypto.SMP.messages.SignatureMessage signatureMessage =
										new org.thoughtcrime.SMP.crypto.SMP.messages.SignatureMessage(protocolVersion, xEncryted,
											xEncryptedMac);
									signatureMessage.senderInstanceTag = senderInstanceTag;
									signatureMessage.receiverInstanceTag = recipientInstanceTag;
									otr.close();
									return signatureMessage;
								}
								default:
									// NOTE by gp: aren't we being a little too harsh here? Passing the message as a plaintext
									// message to the host application shouldn't hurt anybody.
									otr.close();
									throw new IOException("Illegal message type.");
							}
						}
					}


					// Try to detect whitespace tag.
					final Matcher matcher = patternWhitespace.matcher(s);

					boolean v1 = false;
					boolean v2 = false;
					boolean v3 = false;
					while (matcher.find()) {
						if (!v1 && matcher.start(2) > -1)
							v1 = true;

						if (!v2 && matcher.start(3) > -1)
							v2 = true;

						if (!v3 && matcher.start(3) > -1)
							v3 = true;

						if (v1 && v2 && v3)
							break;
					}

					String cleanText = matcher.replaceAll("");
					List<Integer> versions = null;
					if (v1 || v2 || v3) {
						versions = new ArrayList<Integer>();
						if (v1)
							versions.add(OTRv.ONE);
						if (v2)
							versions.add(OTRv.TWO);
						if (v3)
							versions.add(OTRv.THREE);
					}

					return new org.thoughtcrime.SMP.crypto.SMP.messages.PlainTextMessage(versions, cleanText);

				}
	*/
		private static final char HEX_ENCODER[] = {'0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	public static String byteArrayToHexString(byte in[]) {
		int i = 0;
		if (in == null || in.length <= 0)
			return null;
		StringBuffer out = new StringBuffer(in.length * 2);
		while (i < in.length) {
			out.append(HEX_ENCODER[(in[i] >>> 4) & 0x0F]);
			out.append(HEX_ENCODER[in[i] & 0x0F]);
			i++;
		}
		return out.toString();
	}
/*
	private static final String HEX_DECODER = "0123456789ABCDEF";


	public static byte[] hexStringToByteArray(String value) {
		value = value.toUpperCase();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int index = 0; index < value.length(); index += 2) {
			int high = HEX_DECODER.indexOf(value.charAt(index));
			int low = HEX_DECODER.indexOf(value.charAt(index + 1));
			out.write((high << 4) + low);
		}
		return out.toByteArray();
	}

	/**
	 * Check whether the provided content is OTR encoded.
	 *
	 * @param content
	 *            the content to investigate
	 * @return returns true if content is OTR encoded, or false otherwise

	public static boolean otrEncoded(String content) {
		return content.startsWith(SerializationConstants.HEAD
			+ SerializationConstants.HEAD_ENCODED);
	}
	*/
}
