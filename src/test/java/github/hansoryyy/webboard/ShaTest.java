package github.hansoryyy.webboard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShaTest extends Object {
	// values ( passwd('1234') );
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String password = "1233";
		byte[] loginPwByte= password.getBytes();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(loginPwByte);
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("val = " +hexString.toString());
        
        // 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4 => 1234
        // 4654d793972c3b6a1d48fb0ab58d9cb0de46c3d33d605f9222c283dfaa12d420 => 1233
        
        HashMap<String, Object> param = new HashMap<>();
        param.put("test", "yes!");
        
        Object value = param.get("test"); // null, "doekkdk" ?dkdkdkkd ArrayList();
        
        /// value.length();
        
        
        ArrayList<String> sl;
        String s;
 //       s.length();
        
//        if( ! ((String)param.get("test").equals("y")) ) {
//        	
//        }
//        Strinb value = (String) param.get("test");
//        if( ! ( ( (String)param.get("test") ) .length() < 10) ) {
//        	;
//        }
        
        ShaTest sh = new ShaTest();
        sh.equals("dkdkd");
	}
}
