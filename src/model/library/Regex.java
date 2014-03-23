package model.library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex class to test a regular expression.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class Regex {

	/**
	 * Test if an email address is valid
	 * 
	 * @param email
	 *            The email
	 * @return boolean True if the mail is valid, false if not.
	 */
	public static boolean isEmailAdress(String email) {
		Pattern p = Pattern
				.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

}
