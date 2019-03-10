package github.hansoryyy.webboard.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * 대칭키 암호화 복호화 !
 * p1 -> 320923092840-982109
 * p2 -> 320923092840-982109 
 * 
 */
public class AES256Util {
	private String iv;
	private Key keySpec;

	/**
	 * 16자리의 키값을 입력하여 객체를 생성한다.
	 * 
	 * @param key
	 *            암/복호화를 위한 키값
	 * @throws UnsupportedEncodingException
	 *             키값의 길이가 16이하일 경우 발생
	 */
	// String key;

	public AES256Util(char [] key) {
		this.iv = new String(key).substring(0, 16);
		/*
		 * keySpec 
		 * 
		 * K K K K
		 * K K K K
		 * K K K K
		 * K K K K
		 *    +
		 * plainText : java pyton nojs |javascriptxxxxx 40bytes
		 * plainText.substring(i, i+16);
		 * P P P P
		 * P P P P
		 * P P P P
		 * P P P P 
		 * 
		 * E E E E
		 * E E E E
		 * E E E E
		 * E E E E
		 */
		byte[] b;
		try {
			b = new String(key).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		byte[] keyBytes = new byte[16]; // !!!
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		this.keySpec = keySpec;
		
		Arrays.fill(keyBytes, (byte)255);
		Arrays.fill(b, (byte)0);
	}

	/**
	 * AES256 으로 암호화 한다.
	 * 
	 * @param plainText 암호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String plainText) {
		Cipher c;
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(plainText.getBytes("UTF-8")); // 바이너리 데이터
			// [ 45, 12, 02, 43]
			
			String enStr = new String(Base64.encodeBase64(encrypted));
			// tMCr
			// xkES938D3dkdk=
			return enStr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} // 128, 256 
		// ECB or CBC
		
	}

	/**
	 * AES256으로 암호화된 txt 를 복호화한다.
	 * 
	 * @param str
	 *            복호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String decrypt(String str) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		return new String(c.doFinal(byteStr), "UTF-8");
	}
}