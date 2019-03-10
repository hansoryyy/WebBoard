package github.hansoryyy.webboard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	/**
	 * loginId-> login_id
	 * login_id
	 * thisIdTest -> this_id_test
	 * @param camel
	 * @return
	 */
	final public static String camelToSnake ( String camel ) {
		StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[a-z][A-Z]"); // [???][XXX]
        Matcher m = p.matcher(camel);
        int offset = 0;
        while ( m.find() ) {
            sb.append( camel.substring(offset, m.start()+1)); // "login"
            sb.append('_');  // "_"
            sb.append(Character.toLowerCase(camel.charAt(m.end()-1)));
            offset = m.end();
        }
        sb.append(camel.substring(offset));
        return sb.toString();

	}
}
