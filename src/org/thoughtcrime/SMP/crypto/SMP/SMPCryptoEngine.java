package org.thoughtcrime.SMP.crypto.SMP;

/**
 * Created by ludwig on 29/06/15.
 */

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

/**
 *
 * @author Ge@autor george Politis
 *
 */
public interface SMPCryptoEngine {
	/*

	public static final String MODULUS_TEXT = "00FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA237327FFFFFFFFFFFFFFFF";
	public static final BigInteger MODULUS = new BigInteger(MODULUS_TEXT, 16);
	public static final BigInteger BIGINTEGER_TWO = BigInteger.valueOf(2);
	public static final BigInteger MODULUS_MINUS_TWO = MODULUS
		.subtract(BIGINTEGER_TWO);

	public static String GENERATOR_TEXT = "2";
	public static BigInteger GENERATOR = new BigInteger(GENERATOR_TEXT, 10);

	public static final int AES_KEY_BYTE_LENGTH = 16;
	public static final int SHA256_HMAC_KEY_BYTE_LENGTH = 32;
	public static final int DH_PRIVATE_KEY_MINIMUM_BIT_LENGTH = 320;
	public static final byte[] ZERO_CTR = new byte[] { 0x00, 0x00, 0x00, 0x00,
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
		0x00 };

	public static final int DSA_PUB_TYPE = 0;

	public abstract KeyPair generateDHKeyPair() throws SMPCryptoException;

	public abstract DHPublicKey getDHPublicKey(byte[] mpiBytes)
		throws SMPCryptoException;

	public abstract DHPublicKey getDHPublicKey(BigInteger mpi)
		throws SMPCryptoException;

	public abstract byte[] sha256Hmac(byte[] b, byte[] key)
		throws SMPCryptoException;

	public abstract byte[] sha256Hmac(byte[] b, byte[] key, int length)
		throws SMPCryptoException;

	public abstract byte[] sha1Hmac(byte[] b, byte[] key, int length)
		throws SMPCryptoException;

	public abstract byte[] sha256Hmac160(byte[] b, byte[] key)
		throws SMPCryptoException;

	public abstract byte[] sha256Hash(byte[] b) throws SMPCryptoException;

	public abstract byte[] sha1Hash(byte[] b) throws SMPCryptoException;

	public abstract byte[] aesDecrypt(byte[] key, byte[] ctr, byte[] b)
		throws SMPCryptoException;

	public abstract byte[] aesEncrypt(byte[] key, byte[] ctr, byte[] b)
		throws SMPCryptoException;

	public abstract BigInteger generateSecret(PrivateKey privKey,
	                                          PublicKey pubKey) throws SMPCryptoException;

	public abstract byte[] sign(byte[] b, PrivateKey privatekey)
		throws SMPCryptoException;

	public abstract boolean verify(byte[] b, PublicKey pubKey, byte[] rs)
		throws SMPCryptoException;

	public abstract String getFingerprint(PublicKey pubKey)
		throws SMPCryptoException;

	public abstract byte[] getFingerprintRaw(PublicKey pubKey)
		throws SMPCryptoException;
		*/
}
