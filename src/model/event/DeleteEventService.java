package model.event;

import java.io.IOException;
import java.util.List;

import model.Service;
import model.user.UserManager;

public class DeleteEventService extends Service {

	private Event event;
	
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
