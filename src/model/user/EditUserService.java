package model.user;

import java.io.IOException;

import model.Service;
import model.library.Hash;

public class EditUserService extends Service {
	
	private User user;
	
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
			if(getProtocol().getUserExist()) {
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
