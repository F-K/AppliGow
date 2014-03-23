package model.user;

import java.io.IOException;

import model.Service;
import model.library.Hash;

/**
 * The user editing service.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EditUserService extends Service {

	/**
	 * The user object.
	 */
	private User user;

	/**
	 * Constructor for the service.
	 * 
	 * @param user
	 *            The user.
	 * @param serverIP
	 *            The server IP Address.
	 * @param port
	 *            The server port number.
	 */
	public EditUserService(User user, String serverIP, int port) {
		super(serverIP, port);
		user.setPassword(Hash.sha1(user.getPassword()));
		this.user = user;
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();

			// Send the service's name to the server
			getProtocol().sendService("EDIT_USER");

			// Send the user to the server
			getProtocol().sendUser(user);
			if (getProtocol().getUserExist()) {
				// Set the current user
				UserManager.setUser(user);
				setSuccess(true);
			} else
				setSuccess(false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
