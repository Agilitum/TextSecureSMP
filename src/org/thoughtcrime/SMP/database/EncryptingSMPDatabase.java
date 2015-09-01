package org.thoughtcrime.SMP.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.crypto.AsymmetricMasterCipher;
import org.thoughtcrime.SMP.crypto.AsymmetricMasterSecret;
import org.thoughtcrime.SMP.crypto.MasterCipher;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.database.model.DisplayRecord;
import org.thoughtcrime.SMP.database.model.SMPMessageRecord;
import org.thoughtcrime.SMP.sms.IncomingSMPMessage;
import org.thoughtcrime.SMP.sms.OutgoingSMPMessage;
import org.thoughtcrime.SMP.util.LRUCache;
import org.whispersystems.libaxolotl.InvalidMessageException;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ludwig on 31/07/15.
 */

public class EncryptingSMPDatabase extends SMPDatabase {

	public static final String TAG = EncryptingSMPDatabase.class.getSimpleName();
	private final PlaintextCache plaintextCache = new PlaintextCache();

	public EncryptingSMPDatabase(Context context, SQLiteOpenHelper databaseHelper) {
		super(context, databaseHelper);
	}

	private String getAsymmetricEncryptedBody(AsymmetricMasterSecret masterSecret, String body) {
		AsymmetricMasterCipher bodyCipher = new AsymmetricMasterCipher(masterSecret);
		return bodyCipher.encryptBody(body);
	}

	private String getEncryptedBody(MasterSecret masterSecret, String body) {
		MasterCipher bodyCipher = new MasterCipher(masterSecret);
		String ciphertext       = bodyCipher.encryptBody(body);
		plaintextCache.put(ciphertext, body);

		return ciphertext;
	}

	public long insertMessageOutbox(MasterSecret masterSecret, long threadId,
	                                OutgoingSMPMessage message,long timestamp)
	{
		long type = Types.BASE_OUTBOX_TYPE;
		message   = message.withBody(getEncryptedBody(masterSecret, message.getMessageBody()));
		type     |= Types.ENCRYPTION_SYMMETRIC_BIT;

		return insertMessageOutbox(threadId, message, type, timestamp);
	}

	public Pair<Long, Long> insertMessageInbox(MasterSecret masterSecret,
	                                           IncomingSMPMessage message)
	{
		long type = Types.BASE_INBOX_TYPE;

		if (masterSecret == null && message.isSecureMessage()) {
			Log.d(TAG, "insertMessageInbox.isSecureMessage");
			type |= Types.ENCRYPTION_REMOTE_BIT;
		} else if(message.isSMPSyncMessage()) {
			Log.d(TAG, "insertMessageInbox.isSMPSyncMessage");
			//TODO:set correct bit to SMP_SYN_BIT
			type |= Types.ENCRYPTION_SYMMETRIC_BIT;

			message = message.withMessageBody(getEncryptedBody(masterSecret, message.getMessageBody()));
		} else {
			Log.d(TAG, "insertMessageInbox.isSMPMessage");
			type |= Types.ENCRYPTION_SYMMETRIC_BIT;
			message = message.withMessageBody(getEncryptedBody(masterSecret, message.getMessageBody()));
		}

		return insertMessageInbox(message, type);
	}


	public Pair<Long, Long> updateBundleMessageBody(MasterSecret masterSecret, long messageId, String body) {
		String encryptedBody = getEncryptedBody(masterSecret, body);
		return updateMessageBodyAndType(messageId, encryptedBody, Types.TOTAL_MASK,
			Types.BASE_INBOX_TYPE | Types.ENCRYPTION_SYMMETRIC_BIT | Types.SECURE_MESSAGE_BIT);
	}

	public void updateMessageBody(MasterSecret masterSecret, long messageId, String body) {
		String encryptedBody = getEncryptedBody(masterSecret, body);
		updateMessageBodyAndType(messageId, encryptedBody, Types.ENCRYPTION_MASK,
			Types.ENCRYPTION_SYMMETRIC_BIT);
	}

	public Reader getMessages(MasterSecret masterSecret, int skip, int limit) {
		Cursor cursor = super.getMessages(skip, limit);
		return new DecryptingReader(masterSecret, cursor);
	}

	public Reader getOutgoingMessages(MasterSecret masterSecret) {
		Cursor cursor = super.getOutgoingMessages();
		return new DecryptingReader(masterSecret, cursor);
	}

	public SMPMessageRecord getMessage(MasterSecret masterSecret, long messageId) throws NoSuchMessageException {
		Cursor           cursor = super.getMessage(messageId);
		DecryptingReader reader = new DecryptingReader(masterSecret, cursor);
		SMPMessageRecord record = reader.getNext();

		reader.close();

		if (record == null) throw new NoSuchMessageException("No message for ID: " + messageId);
		else                return record;
	}

	public Reader getDecryptInProgressMessages(MasterSecret masterSecret) {
		Cursor cursor = super.getDecryptInProgressMessages();
		return new DecryptingReader(masterSecret, cursor);
	}

	public Reader readerFor(MasterSecret masterSecret, Cursor cursor) {
		return new DecryptingReader(masterSecret, cursor);
	}

	public class DecryptingReader extends SMPDatabase.Reader {

		private final MasterCipher masterCipher;

		public DecryptingReader(MasterSecret masterSecret, Cursor cursor) {
			super(cursor);
			this.masterCipher = new MasterCipher(masterSecret);
		}

		@Override
		protected DisplayRecord.Body getBody(Cursor cursor) {
			long type         = cursor.getLong(cursor.getColumnIndexOrThrow(SMPDatabase.TYPE));
			String ciphertext = cursor.getString(cursor.getColumnIndexOrThrow(BODY));

			if (ciphertext == null) {
				return new DisplayRecord.Body("", true);
			}

			try {
				if (SMPDatabase.Types.isSymmetricEncryption(type)) {
					String plaintext = plaintextCache.get(ciphertext);

					if (plaintext != null)
						return new DisplayRecord.Body(plaintext, true);

					plaintext = masterCipher.decryptBody(ciphertext);

					plaintextCache.put(ciphertext, plaintext);
					return new DisplayRecord.Body(plaintext, true);
				} else {
					return new DisplayRecord.Body(ciphertext, true);
				}
			} catch (InvalidMessageException e) {
				Log.w("EncryptingSMPDatabase", e);
				return new DisplayRecord.Body(context.getString(R.string.EncryptingSMPDatabase_error_decrypting_message), true);
			}
		}
	}

	private static class PlaintextCache {
		private static final int MAX_CACHE_SIZE = 2000;
		private static final Map<String, SoftReference<String>> decryptedBodyCache =
			Collections.synchronizedMap(new LRUCache<String, SoftReference<String>>(MAX_CACHE_SIZE));

		public void put(String ciphertext, String plaintext) {
			decryptedBodyCache.put(ciphertext, new SoftReference<String>(plaintext));
		}

		public String get(String ciphertext) {
			SoftReference<String> plaintextReference = decryptedBodyCache.get(ciphertext);

			if (plaintextReference != null) {
				String plaintext = plaintextReference.get();

				if (plaintext != null) {
					return plaintext;
				}
			}

			return null;
		}
	}
}

