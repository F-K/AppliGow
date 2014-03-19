package model.user;

import java.io.IOException;
import java.util.List;

import model.event.Event;
import model.event.EventManager;

public class SignInService extends SignUserService {
	
	public SignInService(String login, String password, String serverIP, int port) {
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
			if(getProtocol().getUserExist())
				user = getProtocol().getUser();
			
			// Set the current user
			UserManager.setUser(user);
			
			// Retrieve events
			List<Event> allEvents = getProtocol().getAllEvents();
			List<Event> events = getProtocol().getEvents(user.getId());
			EventManager.setAllEvents(allEvents);
			EventManager.setEvents(events);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
