/*
 * $Log: Crypto.java,v $
 * Revision 1.10  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.9  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 settings.
 *
 * Revision 1.8  2005/03/25 09:18:10  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.7  2005/02/22 15:46:54  harpechr
 * Changed the code so that all saved connection information is base 64 encoded to get round the name restrictions.
 *
 * Revision 1.6  2005/02/22 13:16:42  harpechr
 * *** empty log message ***
 *
 * Revision 1.5  2005/02/10 11:33:54  harpechr
 * Safety commit!
 *
 * Revision 1.4  2005/02/09 14:01:14  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.3  2005/01/24 12:34:52  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.2  2005/01/11 14:01:57  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.util.crypto;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.crypto.provider.SunJCE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


/**
 * <h4>Utility to encrypt and decrypt information.</h4>
 * <DL>
 * <DT><B>Description: </B>
 * <DD>Since we have the need of storing sensitive data such as passwords in our configuration files it is required
 * that this information can be stored in a secure way. This class provides a simple way of doing that.</DD>
 * </DT>
 * <DT><B>Copyright (c): </B>
 * <DD>Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>May 2, 2003 10:33:11 AM</DD>
 * </DT>
 * </DL>
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the <a
 * href="http://www.gnu.org/licenses/gpl.html">GNU General Public License </a> for more details.
 * </p>
 * 
 * @author Christopher Andrew Harper (CAH) account: dmadmin
 * @version 3.0.0
 * @since 1.0
 */
public class Crypto
					extends
						Object
								implements
									ICrypto
{

	/**
	 * Base 64 decoder.
	 * 
	 * @since 1.0
	 */
	private static final BASE64Decoder	decoder				= new BASE64Decoder();

	/**
	 * BAse 64 encoder.
	 * 
	 * @since 1.0
	 */
	private static final BASE64Encoder	encoder				= new BASE64Encoder();

	/**
	 * Default algorith to use (DESede/CBC/PKCS5Padding).
	 * 
	 * @since 1.0
	 */
	private String						algorithmName		= ICrypto.DEFAULT_ALGORITHM;

	/**
	 * Decrypting cipher.
	 * 
	 * @since 1.0
	 */
	private Cipher						decrypter			= null;

	/**
	 * Encrypting cipher.
	 * 
	 * @since 1.0
	 */
	private Cipher						encrypter			= null;

	/**
	 * Algorithm parameter specification.
	 * 
	 * @since 1.0
	 */
	private IvParameterSpec				ivParameterSpec		= new IvParameterSpec(new byte[] {(byte) 0xc7, (byte) 0x73,
		(byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99});

	/**
	 * Key algorithm name.
	 * 
	 * @since 1.0
	 */
	private String						keyAlgorithm		= ICrypto.KEY_ALGORITHM_DESEDE;

	/**
	 * Key factory for PBEWithMD5AndDES algorithm.
	 * 
	 * @since 1.0
	 */
	private SecretKeyFactory			keyfactory			= null;

	/**
	 * Key factory for other algorithms.
	 * 
	 * @since 1.0
	 */
	private KeyGenerator				keyGenerator		= null;

	/**
	 * Pass phrase for the key.
	 * 
	 * @since 1.0
	 */
	private char[]						passPhrase			= "May goodnes and grace.".toCharArray(); //$NON-NLS-1$

	/**
	 * Algorithm parameter specification for PBEWithMD5AndDES.
	 * 
	 * @since 1.0
	 */
	private PBEParameterSpec			pbeParameterSpec	= new PBEParameterSpec(new byte[] {(byte) 0xc7,
		(byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99}, 20);

	/**
	 * Seed to use when creating a key.
	 * 
	 * @since 1.0
	 */
	private byte[]						salt				= new byte[] {(byte) 0xc7, (byte) 0x73, (byte) 0x21,
		(byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99};

	/**
	 * Make sure that the SunJCE provider is loaded.
	 * 
	 * @since 1.0
	 */
	static
	{
		Security.addProvider(new SunJCE());
	}

	/**
	 * Create an instance of the crypter with {@link org.cah.util.crypto.ICrypto#DEFAULT_ALGORITHM default algorithm}.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 1:18:56 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @throws NoSuchAlgorithmException
	 *             if the default algorithm can't be found.
	 * @throws NoSuchPaddingException
	 *             if the default algorithm has an invalid padding.
	 * @throws NoSuchProviderException
	 *             if the SunJCE jars can't be found.
	 */
	public Crypto() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
	{

		this(ICrypto.DEFAULT_ALGORITHM);
	}

	/**
	 * Create a crypto instance with a given algorithm. See {@link org.cah.util.crypto.ICrypto ICrypto}for valid
	 * algorithms. The given algorithm will determine the used key algorithm.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 2:09:23 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anAlgorithmName
	 *            name of the algorithm.
	 * @throws NoSuchAlgorithmException
	 *             if the given algorithm is not valid.
	 * @throws NoSuchPaddingException
	 *             if the given padding is invalid.
	 * @throws NoSuchProviderException
	 *             if the SunJCE jars can't be found.
	 */
	public Crypto(final String anAlgorithmName) throws NoSuchAlgorithmException, NoSuchPaddingException,
					NoSuchProviderException
	{

		setAlgorithmName(anAlgorithmName);
		setEncrypter(Cipher.getInstance(getAlgorithmName()));
		setDecrypter(Cipher.getInstance(getAlgorithmName()));
		if ((getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_CBC))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_CFB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_ECB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_OFB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_PCBC)))
		{
			setKeyAlgorithm(ICrypto.KEY_ALGORITHM_BLOWFISH);
		} else if ((getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_CBC))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_CFB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_ECB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_OFB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_PCBC)))
		{
			setKeyAlgorithm(ICrypto.KEY_ALGORITHM_DESEDE);
		} else if ((getAlgorithmName().equals(ICrypto.ALGORITHM_DES_CBC))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DES_CBF))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DES_EBC))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DES_OFB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DES_PCBC)))
		{
			setKeyAlgorithm(ICrypto.KEY_ALGORITHM_DES);
		}
		if (getAlgorithmName().equals(ICrypto.ALGORITHM_PBEMD5DES_CBC))
		{
			setKeyAlgorithm(ICrypto.KEY_ALGORITHM_PBEWITHMD5ANDDES);
			setKeyfactory(SecretKeyFactory.getInstance(getKeyAlgorithm(), ICrypto.SECURITY_PROVIDER));
		} else
		{
			setKeyGenerator(KeyGenerator.getInstance(getKeyAlgorithm(), ICrypto.SECURITY_PROVIDER));
			getKeyGenerator().init(new SecureRandom(getSalt()));
		}
	}

	/**
	 * Create a crypto instance with a given algorithm and salt to use wen generating keys. See
	 * {@link org.cah.util.crypto.ICrypto ICrypto}for valid algorithms. The given algorithm will determine the used key
	 * algorithm.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 2:21:04 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anAlgorithmName
	 *            name of the algorithm.
	 * @param aSalt
	 *            bytes used to initialize SecureRandom.
	 * @throws NoSuchAlgorithmException
	 *             if the given algorithm is not valid.
	 * @throws NoSuchPaddingException
	 *             if the given padding is invalid.
	 * @throws NoSuchProviderException
	 *             if the SunJCE jars can't be found.
	 */
	public Crypto(final String anAlgorithmName, final byte[] aSalt) throws NoSuchAlgorithmException,
					NoSuchPaddingException, NoSuchProviderException
	{

		this(anAlgorithmName);
		setSalt(aSalt);
		setPbeParameterSpec(new PBEParameterSpec(getSalt(), 20));
		setIvParameterSpec(new IvParameterSpec(getSalt()));
	}

	/**
	 * Create a crypto instance with a given algorithm, salt to use wen generating keys and a pass phrase to use when
	 * generating PBEWithMD5AndDES key. See {@link org.cah.util.crypto.ICrypto ICrypto}for valid algorithms. The given
	 * algorithm will determine the used key algorithm.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 2:25:29 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anAlgorithmName
	 *            name of the algorithm.
	 * @param aSalt
	 *            bytes used to initialize SecureRandom.
	 * @param aPassPhrase
	 *            used to generate PBEWithMD5AndDES key.
	 * @throws NoSuchAlgorithmException
	 *             if the given algorithm is not valid.
	 * @throws NoSuchPaddingException
	 *             if the given padding is invalid.
	 * @throws NoSuchProviderException
	 *             if the SunJCE jars can't be found.
	 */
	public Crypto(final String anAlgorithmName, final byte[] aSalt, final char[] aPassPhrase)
					throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
	{

		this(anAlgorithmName, aSalt);
		setPassPhrase(aPassPhrase);
	}

	/**
	 * Get the base 64 decoder
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:10:44 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the decoder.
	 */
	public static BASE64Decoder getDecoder()
	{

		return Crypto.decoder;
	}

	/**
	 * Get the base 64 encoder.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the encoder.
	 */
	public static BASE64Encoder getEncoder()
	{

		return Crypto.encoder;
	}

	/**
	 * Decrypt an array of bytes.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:00:03 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToDecrypt
	 *            the value to decrypt.
	 * @return the decrypted bytes.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @see org.cah.util.crypto.ICrypto#decrypt(byte[])
	 */
	public synchronized byte[] decrypt(final byte[] dataToDecrypt) throws BadPaddingException,
					IllegalBlockSizeException
	{

		return (getDecrypter().doFinal(dataToDecrypt));
	}

	/**
	 * Decrypt a string.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:17:02 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToDecrypt
	 *            crypted string.
	 * @return clear text string.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @see org.cah.util.crypto.ICrypto#decrypt(java.lang.String)
	 */
	public String decrypt(final String dataToDecrypt) throws BadPaddingException, IllegalBlockSizeException
	{

		return (new String(decrypt(dataToDecrypt.getBytes())));
	}

	/**
	 * Decode a given string and the decrypt it.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 2:29:43 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToDecrypt
	 *            encrypted & encoded string.
	 * @param characterSet
	 *            character set used to encode the string.
	 * @return the real data.
	 * @throws IOException
	 *             if the string can't be decoded.
	 * @throws IllegalStateException
	 *             if crypting of decryptin method has been invoked at an illegal or inappropriate time. In other words,
	 *             the Java environment or Java application is not in an appropriate state for the requested operation.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @see org.cah.util.crypto.ICrypto#decryptBase64(java.lang.String, java.lang.String)
	 */
	public synchronized String decryptBase64(final String dataToDecrypt, final String characterSet) throws IOException,
					IllegalStateException,
					IllegalBlockSizeException,
					BadPaddingException
	{

		return (new String(getDecrypter().doFinal(getDecoder().decodeBuffer(dataToDecrypt)), characterSet));
	}

	/**
	 * Ecrypt an array of bytes.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:19:57 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToEncrypt
	 *            clear bytes.
	 * @return ecrypted bytes.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @see org.cah.util.crypto.ICrypto#encrypt(byte[])
	 */
	public synchronized byte[] encrypt(final byte[] dataToEncrypt) throws BadPaddingException,
					IllegalBlockSizeException
	{

		return (getEncrypter().doFinal(dataToEncrypt));
	}

	/**
	 * Ecrypt a string.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:22:22 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToEncrypt
	 *            clear text string.
	 * @return ecrypted string.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @see org.cah.util.crypto.ICrypto#encrypt(java.lang.String)
	 */
	public String encrypt(final String dataToEncrypt) throws BadPaddingException, IllegalBlockSizeException
	{

		return (new String(encrypt(dataToEncrypt.getBytes())));
	}

	/**
	 * Ecrypt a given string and the encode it.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 2, 2003 2:33:51 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param dataToEncrypt
	 *            clear text string.
	 * @param characterSet
	 *            character set to use when ecoding the crypted string.
	 * @return the crypted & encoded string.
	 * @throws UnsupportedEncodingException
	 *             if the given character set is not supported.
	 * @throws IllegalStateException
	 *             if crypting of decryptin method has been invoked at an illegal or inappropriate time. In other words,
	 *             the Java environment or Java application is not in an appropriate state for the requested operation.
	 * @throws IllegalBlockSizeException
	 *             exception is thrown when the length of data provided to a block cipher is incorrect, i.e., does not
	 *             match the block size of the cipher.
	 * @throws BadPaddingException
	 *             exception is thrown when a particular padding mechanism is expected for the input data but the data
	 *             is not padded properly.
	 * @see org.cah.util.crypto.ICrypto#encryptBase64(java.lang.String, java.lang.String)
	 */
	public synchronized String encryptBase64(final String dataToEncrypt, final String characterSet) throws UnsupportedEncodingException,
					IllegalStateException,
					IllegalBlockSizeException,
					BadPaddingException
	{

		return (getEncoder().encode(getEncrypter().doFinal(dataToEncrypt.getBytes(characterSet))));
	}

	/**
	 * Generate a new key.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:23:15 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return a new key.
	 * @throws InvalidKeySpecException
	 *             is the exception for invalid key specifications.
	 * @see org.cah.util.crypto.ICrypto#getNewKey()
	 */
	public Key getNewKey() throws InvalidKeySpecException
	{

		if (getAlgorithmName().equals(ICrypto.ALGORITHM_PBEMD5DES_CBC))
		{
			return (getKeyfactory().generateSecret(new PBEKeySpec(getPassPhrase())));
		}
		return (getKeyGenerator().generateKey());
	}

	/**
	 * Set the key to be used with encrypt decrypt operations.
	 * <DL>
	 * <DT><B>Created: </B>
	 * <DD>May 8, 2003 3:24:46 PM</DD>
	 * </DT>
	 * <DT><B>Author: </B>
	 * <DD>Christopher Harper account:dmadmin</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aKey
	 *            either a newly generated key or a key that has been stored from a previous operation.
	 * @throws InvalidKeyException
	 *             is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc).
	 * @throws InvalidAlgorithmParameterException
	 *             is the exception for invalid or inappropriate algorithm parameters.
	 * @see org.cah.util.crypto.ICrypto#setKey(java.security.Key)
	 */
	public void setKey(final Key aKey) throws InvalidKeyException, InvalidAlgorithmParameterException
	{

		if ((getAlgorithmName().equals(ICrypto.ALGORITHM_BLOWFISH_ECB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DESEDE_ECB))
			|| (getAlgorithmName().equals(ICrypto.ALGORITHM_DES_EBC)))
		{
			getEncrypter().init(Cipher.ENCRYPT_MODE, aKey);
			getDecrypter().init(Cipher.DECRYPT_MODE, aKey);
		} else if (getAlgorithmName().equals(ICrypto.ALGORITHM_PBEMD5DES_CBC))
		{
			getEncrypter().init(Cipher.ENCRYPT_MODE, aKey, getPbeParameterSpec());
			getDecrypter().init(Cipher.DECRYPT_MODE, aKey, getPbeParameterSpec());
		} else
		{
			getEncrypter().init(Cipher.ENCRYPT_MODE, aKey, getIvParameterSpec());
			getDecrypter().init(Cipher.DECRYPT_MODE, aKey, getIvParameterSpec());
		}
	}

	/**
	 * Get the algorithm name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:12:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the algorithm name.
	 */
	protected String getAlgorithmName()
	{

		return this.algorithmName;
	}

	/**
	 * Get the decrypter.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the decrypter.
	 */
	protected Cipher getDecrypter()
	{

		return this.decrypter;
	}

	/**
	 * Get the encrypter.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:22 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the encrypter.
	 */
	protected Cipher getEncrypter()
	{

		return this.encrypter;
	}

	/**
	 * Get the parameter spec.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:28 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the parameter spec.
	 */
	protected IvParameterSpec getIvParameterSpec()
	{

		return this.ivParameterSpec;
	}

	/**
	 * Get the key algorithm
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:33 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the algorithm name.
	 */
	protected String getKeyAlgorithm()
	{

		return this.keyAlgorithm;
	}

	/**
	 * Get the secret key factory.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:39 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the key factory.
	 */
	protected SecretKeyFactory getKeyfactory()
	{

		return this.keyfactory;
	}

	/**
	 * Get the key generator.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:48 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the key generator.
	 */
	protected KeyGenerator getKeyGenerator()
	{

		return this.keyGenerator;
	}

	/**
	 * Get the pass phrase characters.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:54 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the pass phrase.
	 */
	protected char[] getPassPhrase()
	{

		return this.passPhrase;
	}

	/**
	 * Get the param spec.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:12:01 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the param spec.
	 */
	protected PBEParameterSpec getPbeParameterSpec()
	{

		return this.pbeParameterSpec;
	}

	/**
	 * Get the salt
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:10:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @return the salt.
	 */
	protected byte[] getSalt()
	{

		return this.salt;
	}

	/**
	 * Set the algorithm name.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:12:09 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anAlgorithmName
	 *            the new algorithm name.
	 */
	protected void setAlgorithmName(final String anAlgorithmName)
	{

		this.algorithmName = anAlgorithmName;
	}

	/**
	 * Set the decrypter.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:06 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aDecrypter
	 *            the new decrypter.
	 */
	protected void setDecrypter(final Cipher aDecrypter)
	{

		this.decrypter = aDecrypter;
	}

	/**
	 * Set the encrypter.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:25 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anEncrypter
	 *            the new encrypter.
	 */
	protected void setEncrypter(final Cipher anEncrypter)
	{

		this.encrypter = anEncrypter;
	}

	/**
	 * Set the parameter spec.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:30 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param anIvParameterSpec
	 *            the new parameter spec.
	 */
	protected void setIvParameterSpec(final IvParameterSpec anIvParameterSpec)
	{

		this.ivParameterSpec = anIvParameterSpec;
	}

	/**
	 * Set the key algorithm
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:36 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aKeyAlgorithm
	 *            the new key algorithm.
	 */
	protected void setKeyAlgorithm(final String aKeyAlgorithm)
	{

		this.keyAlgorithm = aKeyAlgorithm;
	}

	/**
	 * Set the key factory.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:45 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aKeyfactory
	 *            the new key factory.
	 */
	protected void setKeyfactory(final SecretKeyFactory aKeyfactory)
	{

		this.keyfactory = aKeyfactory;
	}

	/**
	 * Set the key generator.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:52 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aKeyGenerator
	 *            the new key generator.
	 */
	protected void setKeyGenerator(final KeyGenerator aKeyGenerator)
	{

		this.keyGenerator = aKeyGenerator;
	}

	/**
	 * Set the pass phrase.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:11:58 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aPassPhrase
	 *            the new pass phrase.
	 */
	protected void setPassPhrase(final char[] aPassPhrase)
	{

		this.passPhrase = aPassPhrase;
	}

	/**
	 * Set the parameter spec.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:12:03 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aPbeParameterSpec
	 *            the new pbe parameter spec.
	 */
	protected void setPbeParameterSpec(final PBEParameterSpec aPbeParameterSpec)
	{

		this.pbeParameterSpec = aPbeParameterSpec;
	}

	/**
	 * Set the salt.
	 * <DL>
	 * <DT><B>Created : </B>
	 * <DD>Dec 29, 2004 3:10:41 PM</DD>
	 * </DT>
	 * <DT><B>Author : </B>
	 * <DD>Christopher Harper account : HARPECHR</DD>
	 * </DT>
	 * </DL>
	 * 
	 * @since 1.0
	 * @param aSalt
	 *            new salt.
	 */
	protected void setSalt(final byte[] aSalt)
	{

		this.salt = aSalt;
	}
}