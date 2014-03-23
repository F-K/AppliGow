package model.event;

import java.io.IOException;
import java.util.List;

import model.Service;

/**
 * The service used for deleting an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class DeleteEventService extends Service {

	/**
	 * The event.
	 */
	private Event event;

	/**
	 * Constructor of the class.
	 * 
	 * @param event
	 *            The event.
	 * @param serverIP
	 *            The server IP.
	 * @param port
	 *            The server port.
	 */
	public DeleteEventService(Event event, String serverIP, int port) {
		super(serverIP, port);
		this.event = event;
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();
			getProtocol().sendService("DELETE_EVENT");
			getProtocol().sendEvent(event);

			// MAJ events
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
