package com.activity.appligow;

import java.util.ArrayList;
import java.util.List;

import model.event.Event;
import model.event.EventManager;
import model.user.UserManager;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class EventMarkerOverlay {
	
	private List<MarkerOptions> listEventMarker;

	public EventMarkerOverlay () {
		this.listEventMarker = new ArrayList<MarkerOptions>();
		List<Event> allEvents = EventManager.getAllEvents();
		int userId = UserManager.getUser().getId();
		for(Event event : allEvents) {
			LatLng position = new LatLng(event.getLatitude(), event.getLongitude());
			MarkerOptions opts = new MarkerOptions().position(position);
			opts.title(event.getTitle());
			opts.snippet(event.getDateStart().toLocaleString() + "\n" + event.getDateEnd().toLocaleString());
			if(event.getUser().getId() == userId)
				opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			else
				opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
			listEventMarker.add(opts);
		}
	}

	public List<MarkerOptions> getListEventMarker() {
		return listEventMarker;
	}

	public void setListEventMarker(List<MarkerOptions> listEventMarker) {
		this.listEventMarker = listEventMarker;
	}

}