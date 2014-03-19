package model.user;

import java.io.IOException;
import java.util.List;

import model.event.Event;
import model.event.EventManager;

public class SignUpService extends SignUserService {
	
	public SignUpService(String login, String password, String serverIP, int port) {
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
			if(!getProtocol().getUserExist()) {
				user = getProtocol().getUser();
				// Retrieve events
				List<Event> allEvents = getProtocol().getAllEvents();
				EventManager.setAllEvents(allEvents);
			}
			
			// Set the current user
			UserManager.setUser(user);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
