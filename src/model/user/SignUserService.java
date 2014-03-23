package model.user;

import model.Service;
import model.library.Hash;

/**
 * The sign user service.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public abstract class SignUserService extends Service implements Runnable {

	/**
	 * Login and password of the user.
	 */
	private final String LOGIN, PASSWORD;

	/**
	 * Constructor for the service.
	 * 
	 * @param login
	 *            The login.
	 * @param password
	 *            The password.
	 * @param serverIP
	 *            The server IP address.
	 * @param port
	 *            The server port number.
	 */
	public SignUserService(String login, String password, String serverIP,
			int port) {
		super(serverIP, port);
		LOGIN = login;
		PASSWORD = Hash.sha1(password);
	}

	/**
	 * Get the login.
	 * 
	 * @return Sring The user login.
	 */
	public String getLogin() {
		return LOGIN;
	}

	/**
	 * Get the password.
	 * 
	 * @return String The user password.
	 */
	public String getPassword() {
		return PASSWORD;
	}

}
