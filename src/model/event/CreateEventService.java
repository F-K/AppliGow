package model.event;

import java.io.IOException;

import model.Service;

/**
 * Service use to create an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class CreateEventService extends Service {

	/**
	 * The event object.
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
	public CreateEventService(Event event, String serverIP, int port) {
		super(serverIP, port);
		this.event = event;
	}

	@Override
	public void run() {
		try {
			// Initialize the transport and protocol
			initService();
			getProtocol().sendService("CREATE_EVENT");
			getProtocol().sendEvent(event);
			setSuccess(true);
			Event eventDAO = getProtocol().getEvent();
			EventManager.addEvent(eventDAO);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
