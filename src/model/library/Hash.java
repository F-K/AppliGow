package model.library;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash a string
 */
public class Hash {
    
    /**
     * Hash a string in SHA1
     * @param string to hash
     * @return a hash string
     */
    public static String sha1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e) {
            return null;
        } catch(NullPointerException e) {
        	return null;
        }
    }
    
}
