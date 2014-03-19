package model.protocol;

import java.io.IOException;
import java.util.List;

import model.event.Event;
import model.user.User;

public interface IProtocolClient {
	
	public void sendService(String service) throws IOException;
	public void sendLogin(String login) throws IOException;
	public void sendPassword(String password) throws IOException;
	public boolean getUserExist() throws ClassNotFoundException, IOException;
	public User getUser() throws ClassNotFoundException, IOException;
	public void sendUser(User user) throws IOException;
	public void sendEvent(Event event) throws IOException;
	public List<Event> getAllEvents() throws ClassNotFoundException, IOException;
	public List<Event> getEvents() throws ClassNotFoundException, IOException;
	public Event getEvent() throws ClassNotFoundException, IOException;

}
