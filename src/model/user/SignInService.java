package model.user;

import java.io.IOException;

/**
 * The sign in service class.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class SignInService extends SignUserService {

	/**
	 * Constructor for the service.
	 * 
	 * @param login
	 *            The login.
	 * @param password
	 *            The password.
	 * @param serverIP
	 *            The server ip address.
	 * @param port
	 *            The server port number.
	 */
	public SignInService(String login, String password, String serverIP,
			int port) {
		super(login, password, serverIP, port);
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();

			// Send the service's name to the server
			getProtocol().sendService("SIGN_IN");

			// Send the login and password to the server
			getProtocol().sendLogin(getLogin());
			getProtocol().sendPassword(getPassword());

			User user = null;

			// User exists
			if (getProtocol().getUserExist())
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
