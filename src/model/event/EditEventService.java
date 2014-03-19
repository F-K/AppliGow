package model.event;

import java.io.IOException;
import java.util.List;

import model.Service;
import model.user.UserManager;

public class EditEventService extends Service {

	private Event event;
	
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
