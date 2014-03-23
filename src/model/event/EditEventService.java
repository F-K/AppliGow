package model.event;

import java.io.IOException;
import java.util.List;

import model.Service;
import model.user.UserManager;

/**
 * The service used for editing an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EditEventService extends Service {

	/**
	 * The event object.
	 */
	private Event event;

	/**
	 * Constructor for the class.
	 * 
	 * @param event
	 *            The event.
	 * @param serverIP
	 *            The server IP address.
	 * @param port
	 *            The server port number.
	 */
	public EditEventService(Event event, String serverIP, int port) {
		super(serverIP, port);
		this.event = event;
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();
			getProtocol().sendService("EDIT_EVENT");
			getProtocol().sendEvent(event);

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
