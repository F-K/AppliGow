package model.protocol;

import java.io.IOException;
import java.util.List;

import model.event.Event;
import model.user.User;

/**
 * Interface for the client protocol.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public interface IProtocolClient {

	/**
	 * Send a service.
	 * 
	 * @param service
	 *            The service to be sent.
	 * @throws IOException
	 */
	public void sendService(String service) throws IOException;

	/**
	 * Send a login.
	 * 
	 * @param login
	 *            The login.
	 * @throws IOException
	 */
	public void sendLogin(String login) throws IOException;

	/**
	 * Send a password.
	 * 
	 * @param password
	 *            The password.
	 * @throws IOException
	 */
	public void sendPassword(String password) throws IOException;

	/**
	 * Test if a user exists.
	 * 
	 * @return bolean True if exist, false if not.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean getUserExist() throws ClassNotFoundException, IOException;

	/**
	 * Get a user.
	 * 
	 * @return User The user.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public User getUser() throws ClassNotFoundException, IOException;

	/**
	 * Send a user.
	 * 
	 * @param user
	 *            The user.
	 * @throws IOException
	 */
	public void sendUser(User user) throws IOException;

	/**
	 * Send an event.
	 * 
	 * @param event
	 *            The event.
	 * @throws IOException
	 */
	public void sendEvent(Event event) throws IOException;

	/**
	 * Get all the events.
	 * 
	 * @return List the list of the events;
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public List<Event> getAllEvents() throws ClassNotFoundException,
			IOException;

	/**
	 * Get an event.
	 * 
	 * @return List List containing the event.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public List<Event> getEvents() throws ClassNotFoundException, IOException;

	/**
	 * Get an event.
	 * 
	 * @return Event The event.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Event getEvent() throws ClassNotFoundException, IOException;

}
