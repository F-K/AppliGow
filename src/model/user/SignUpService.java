package model.user;

import java.io.IOException;

/**
 * The sign up service class.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class SignUpService extends SignUserService {

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
	public SignUpService(String login, String password, String serverIP,
			int port) {
		super(login, password, serverIP, port);
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();

			// Send the service's name to the server
			getProtocol().sendService("SIGN_UP");

			// Send the login and password to the server
			getProtocol().sendLogin(getLogin());
			getProtocol().sendPassword(getPassword());

			User user = null;

			// User doesn't exist
			if (!getProtocol().getUserExist())
				user = getProtocol().getUser();

			// Set the current user
			UserManager.setUser(user);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
