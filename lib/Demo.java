package github.hansoryyy.webboard.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Demo {

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		char [] key = "as838$(!9#ckasdk3e".toCharArray();
		
		String plainText = "123456";
		                  //H2CRonSV0yMdXgqAL29R2zoBwrDWiQiUDXz8I+/5rWr/i7/5OnwW5EWCQPBmWim+
		                  //l6xCogDk+wdLFLLxcEte0L8KRoW0dLJawtmQWHSg48uPwa/VxF+LMr+hkB2g7WQF
		                  //GAaGu9d/nRbYunWRIF1WjIvdsQUH9ULoFXkNZ+BT53RryfnfBI4bXNXnSm7s62wQBddDVQnUQnkJWh4JyLwuV0Gk0XoppwOYS5H1bszEABU=
		
		AES256Util enc = new AES256Util(key);
		Arrays.fill(key, '_');
		
		String encrypted = enc.encrypt(plainText);
		System.out.println(encrypted);
		
		String decrypted = enc.decrypt("GAaGu9d/nRbYunWRIF1WjIvdsQUH9ULoFXkNZ+BT53RryfnfBI4bXNXnSm7s62wQBddDVQnUQnkJWh4JyLwuV0Gk0XoppwOYS5H1bszEABU=");
		System.out.println(decrypted);
		
	}
}
