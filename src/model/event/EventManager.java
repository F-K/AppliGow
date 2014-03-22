package model.event;

import java.util.ArrayList;
import java.util.List;

import model.Service;

public class EventManager {
	
	private static List<Event> allEvents = new ArrayList<Event>();
	private static List<Event> events = new ArrayList<Event>();
	
	public static List<Event> getAllEvents() {
		return allEvents;
	}
	
	public static void setAllEvents(List<Event> allEvents) {
		EventManager.allEvents = allEvents;
	}
	
	public static List<Event> getEvents() {
		return events;
	}
	
	public static void setEvents(List<Event> events) {
		EventManager.events = events;
	}
	
	public static void addEvent(Event event) {
		allEvents.add(event);
		events.add(event);
	}
	
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
