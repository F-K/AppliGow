package model.event;

import java.util.ArrayList;
import java.util.List;

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
	

}
