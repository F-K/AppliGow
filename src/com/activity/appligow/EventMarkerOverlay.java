package com.activity.appligow;

import java.util.ArrayList;
import java.util.List;

import model.event.EventMarker;

import com.google.android.gms.maps.model.LatLng;

public class EventMarkerOverlay {
		  private List<EventMarker> listEventMarker ;
		    
		  
		  public EventMarkerOverlay () {
			  this.listEventMarker = new ArrayList<EventMarker>();
			  fetchEvents();
		  }
		  
		  //get Events from database
		  public void fetchEvents(){
			  this.listEventMarker.add(new EventMarker(1,new LatLng(48.798616, 2.382622),"Basket","13/03/14 - 15h-16h"));
			  this.listEventMarker.add(new EventMarker(2,new LatLng(48.826156, 2.359834),"Apéro","12/03/13 - 16h-18h"));
			  
		  }
		
		public List<EventMarker> getListEventMarker() {
			return listEventMarker;
		}
		
		
		
		public void setListEventMarker(List<EventMarker> listEventMarker) {
			this.listEventMarker = listEventMarker;
		}
		
		
		
}