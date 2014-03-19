package model.protocol;

import java.io.IOException;
import java.util.List;

import library.ITransport;
import model.event.Event;
import model.user.User;

public class ProtocolClient implements IProtocolClient {
	
	private ITransport transport;

	public ProtocolClient(ITransport transport) {
		this.transport = transport;
	}

	@Override
	public void sendService(String service) throws IOException {
		transport.send(service);
	}
	
	@Override
	public void sendLogin(String login) throws IOException {
		transport.send(login);
	}
	
	@Override
	public void sendPassword(String password) throws IOException {
		transport.send(password);
	}

	@Override
	public User getUser() throws ClassNotFoundException, IOException {
		return (User) transport.receive();
	}

	@Override
	public boolean getUserExist() throws ClassNotFoundException, IOException {
		return (Boolean) transport.receive();
	}

	@Override
	public void sendUser(User user) throws IOException {
		transport.send(user);
	}

	@Override
	public void sendEvent(Event event) throws IOException {
		transport.send(event);
	}

	@Override
	public List<Event> getAllEvents() throws ClassNotFoundException, IOException {
		return (List<Event>) transport.receive();
	}

	@Override
	public List<Event> getEvents() throws ClassNotFoundException, IOException {
		return (List<Event>) transport.receive();
	}

	@Override
	public Event getEvent() throws ClassNotFoundException, IOException {
		return (Event) transport.receive();
	}

}
