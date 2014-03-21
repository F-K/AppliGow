package model.user;

import java.io.IOException;

import model.Service;

public class DeleteUserService extends Service {
	
	private User user;
	
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
