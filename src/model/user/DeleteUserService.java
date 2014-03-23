package model.user;

import java.io.IOException;

import model.Service;

/**
 * The service for deleting a user.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class DeleteUserService extends Service {
	/**
	 * The user to be deleted.
	 */
	private User user;

	/**
	 * Constructor for the service.
	 * 
	 * @param user
	 *            The user.
	 * @param serverIP
	 *            The server ip address.
	 * @param port
	 *            The server port number.
	 */
	public DeleteUserService(User user, String serverIP, int port) {
		super(serverIP, port);
		this.user = user;
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();

			// Send the service's name to the server
			getProtocol().sendService("DELETE_USER");

			// Send the user to the server
			getProtocol().sendUser(user);

			setSuccess(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
