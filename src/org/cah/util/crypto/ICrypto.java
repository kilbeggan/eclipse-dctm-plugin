/*
 * $Log: ICrypto.java,v $
 * Revision 1.9  2005/12/04 20:32:42  madcook
 * Version 3.0.0 work started.
 *
 * Revision 1.8  2005/11/21 13:04:31  madcook
 * Plugin version 3.0.0 work started and made code to comply with all java 5 settings.
 *
 * Revision 1.7  2005/03/25 09:18:10  harpechr
 * Version 2.0.0 code that has implemented the new java 1.5 features.
 *
 * Revision 1.6  2005/02/22 15:46:54  harpechr
 * Changed the code so that all saved connection information is base 64 encoded to get round the name restrictions.
 *
 * Revision 1.5  2005/02/09 14:01:14  harpechr
 * Version 1.0.5 work started.
 *
 * Revision 1.4  2005/01/24 12:34:52  harpechr
 * Version 1.0.4 work started.
 *
 * Revision 1.3  2005/01/11 14:01:57  harpechr
 * Changed version number from 1.0.2 to 1.0.3.
 *
 * Revision 1.2  2005/01/10 08:49:02  harpechr
 * Changed the copyright statement from my work standard to my name and the licence to GNU.
 *
 * Revision 1.1  2005/01/07 12:37:48  harpechr
 * Version 1.0.2 code. First CVS commit!
 *
 */

package org.cah.util.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;


/**
 * <h4>Public costants used with the Crypto class.</h4>
 * <DL>
 * <DT><B>Description: </B>
 * <DD>Holds information about available crypto algorithms and allowed character sets to be used with the crypto class.
 * </DD>
 * </DT>
 * <DT><B>Copyright: </B>
 * <DD>2003 (C) Christopher Harper</DD>
 * </DT>
 * <DT><B>Created: </B>
 * <DD>May 2, 2003 10:24:35 AM</DD>
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
public interface ICrypto
{

	/**
	 * Blowfish/CBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_BLOWFISH_CBC			= "Blowfish/CBC/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * Blowfish/CFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_BLOWFISH_CFB			= "Blowfish/CFB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * Blowfish/ECB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_BLOWFISH_ECB			= "Blowfish/ECB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * Blowfish/OFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_BLOWFISH_OFB			= "Blowfish/OFB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * Blowfish/PCBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_BLOWFISH_PCBC			= "Blowfish/PCBC/PKCS5Padding";		//$NON-NLS-1$

	/**
	 * DES/CBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DES_CBC				= "DES/CBC/PKCS5Padding";				//$NON-NLS-1$

	/**
	 * DES/CFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DES_CBF				= "DES/CFB/PKCS5Padding";				//$NON-NLS-1$

	/**
	 * DES/ECB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DES_EBC				= "DES/ECB/PKCS5Padding";				//$NON-NLS-1$

	/**
	 * DES/OFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DES_OFB				= "DES/OFB/PKCS5Padding";				//$NON-NLS-1$

	/**
	 * DES/PCBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DES_PCBC				= "DES/PCBC/PKCS5Padding";				//$NON-NLS-1$

	/**
	 * DESede/CBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DESEDE_CBC			= "DESede/CBC/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * DESede/CFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DESEDE_CFB			= "DESede/CFB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * DESede/ECB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DESEDE_ECB			= "DESede/ECB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * DESede/OFB/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DESEDE_OFB			= "DESede/OFB/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * DESede/PCBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_DESEDE_PCBC			= "DESede/PCBC/PKCS5Padding";			//$NON-NLS-1$

	/**
	 * PBEWithMD5AndDES/CBC/PKCS5Padding (only password based ecryption).
	 * 
	 * @since 1.0
	 */
	public static final String	ALGORITHM_PBEMD5DES_CBC			= "PBEWithMD5AndDES/CBC/PKCS5Padding";	//$NON-NLS-1$

	/**
	 * ISO-8859-1
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_ISO_8859_1		= "ISO-8859-1";						//$NON-NLS-1$

	/**
	 * US-ASCII
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_US_ASCII			= "US-ASCII";							//$NON-NLS-1$

	/**
	 * UTF-16
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_UTF_16			= "UTF-16";							//$NON-NLS-1$

	/**
	 * UTF-16BE
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_UTF_16BE			= "UTF-16BE";							//$NON-NLS-1$

	/**
	 * UTF-16LE
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_UTF_16LE			= "UTF-16LE";							//$NON-NLS-1$

	/**
	 * UTF-8
	 * 
	 * @since 1.0
	 */
	public static final String	CHARACTER_SET_UTF_8				= "UTF-8";								//$NON-NLS-1$

	/**
	 * Defaults to DESede/CBC/PKCS5Padding
	 * 
	 * @since 1.0
	 */
	public static final String	DEFAULT_ALGORITHM				= ALGORITHM_DESEDE_CBC;

	/**
	 * Defaults to UTF-8
	 * 
	 * @since 1.0
	 */
	public static final String	DEFAULT_CHARACTER_SET			= CHARACTER_SET_UTF_8;

	/**
	 * Key algorithm to use if the algorithm is based on Blowfish.
	 * 
	 * @since 1.0
	 */
	public static final String	KEY_ALGORITHM_BLOWFISH			= "Blowfish";							//$NON-NLS-1$

	/**
	 * Key algorithm to use if the algorithm is based on DES.
	 * 
	 * @since 1.0
	 */
	public static final String	KEY_ALGORITHM_DES				= "DES";								//$NON-NLS-1$

	/**
	 * Key algorithm to use if the algorithm is based on DESede
	 * 
	 * @since 1.0
	 */
	public static final String	KEY_ALGORITHM_DESEDE			= "DESede";							//$NON-NLS-1$

	/**
	 * Key algorithm to use if the algorithm is based on PBEWithMD5AndDES.
	 * 
	 * @since 1.0
	 */
	public static final String	KEY_ALGORITHM_PBEWITHMD5ANDDES	= "PBEWithMD5AndDES";					//$NON-NLS-1$

	/**
	 * Security provider (SunJCE).
	 * 
	 * @since 1.0
	 */
	public static final String	SECURITY_PROVIDER				= "SunJCE";							//$NON-NLS-1$

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
	 */
	public byte[] decrypt(final byte[] dataToDecrypt) throws BadPaddingException, IllegalBlockSizeException;

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
	 */
	public String decrypt(final String dataToDecrypt) throws BadPaddingException, IllegalBlockSizeException;

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
	 */
	public String decryptBase64(final String dataToDecrypt, final String characterSet) throws IOException,
					IllegalStateException,
					IllegalBlockSizeException,
					BadPaddingException;

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
	 */
	public byte[] encrypt(final byte[] dataToEncrypt) throws BadPaddingException, IllegalBlockSizeException;

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
	 */
	public String encrypt(final String dataToEncrypt) throws BadPaddingException, IllegalBlockSizeException;

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
	 */
	public String encryptBase64(final String dataToEncrypt, final String characterSet) throws UnsupportedEncodingException,
					IllegalStateException,
					IllegalBlockSizeException,
					BadPaddingException;

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
	 */
	public Key getNewKey() throws InvalidKeySpecException;

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
	 */
	public void setKey(final Key aKey) throws InvalidKeyException, InvalidAlgorithmParameterException;

}