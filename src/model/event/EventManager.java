package model.event;

import java.util.ArrayList;
import java.util.List;

import model.Service;

/**
 * Class to manage the events.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EventManager {

	/**
	 * A list containing all the events.
	 */
	private static List<Event> allEvents = new ArrayList<Event>();

	/**
	 * A list containing an event.
	 */
	private static List<Event> events = new ArrayList<Event>();

	/**
	 * Get all the events.
	 * 
	 * @return List The list of the events.
	 */
	public static List<Event> getAllEvents() {
		return allEvents;
	}

	/**
	 * Set the event list.
	 * 
	 * @param allEvents
	 *            List containing the events.
	 */
	public static void setAllEvents(List<Event> allEvents) {
		EventManager.allEvents = allEvents;
	}

	/**
	 * Get an event.
	 * 
	 * @return List The list containing an event.
	 */
	public static List<Event> getEvents() {
		return events;
	}

	/**
	 * Set the events.
	 * 
	 * @param events
	 *            The event list.
	 */
	public static void setEvents(List<Event> events) {
		EventManager.events = events;
	}

	/**
	 * Add an event to the list.
	 * 
	 * @param event
	 *            The event to be added.
	 */
	public static void addEvent(Event event) {
		allEvents.add(event);
		events.add(event);
	}

	/**
	 * Initialise the events list.
	 * 
	 * @param serverIP
	 *            The server ip address.
	 * @param port
	 *            The server port number.
	 */
	public static void initAllEvents(String serverIP, int port) {
		try {
			Service service = new GetEventsService(serverIP, port);
			// Wait until the thread died
			service.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
