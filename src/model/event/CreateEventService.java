package model.event;

import java.io.IOException;

import model.Service;

public class CreateEventService extends Service {

	private Event event;
	
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
