package model.user;

import model.user.User;

/**
 * The user manager model.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class UserManager {

	/**
	 * The current user of the application.
	 */
	private static User user;

	/**
	 * Get the current user.
	 * 
	 * @return User The user.
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Set the user.
	 * 
	 * @param user
	 *            The user.
	 */
	public static void setUser(User user) {
		UserManager.user = user;
	}

}
