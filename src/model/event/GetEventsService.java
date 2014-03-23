package model.event;

import java.io.IOException;
import java.util.List;

import model.Service;
import model.user.UserManager;

/**
 * Class to get the events service.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class GetEventsService extends Service {

	/**
	 * Constructor of the class.
	 * 
	 * @param serverIP
	 *            The server IP address.
	 * @param port
	 *            The server port number.
	 */
	public GetEventsService(String serverIP, int port) {
		super(serverIP, port);
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();
			getProtocol().sendService("GET_EVENTS");

			// MAJ events
			getProtocol().sendUser(UserManager.getUser());
			List<Event> allEvents = getProtocol().getAllEvents();
			List<Event> events = getProtocol().getEvents();
			EventManager.setAllEvents(allEvents);
			EventManager.setEvents(events);

			setSuccess(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
