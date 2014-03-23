package model.library;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class to hash a string.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class Hash {

	/**
	 * Hash a string in SHA1.
	 * 
	 * @param input String
	 *            to hash.
	 * @return String A hashed string.
	 */
	public static String sha1(String input) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

}
