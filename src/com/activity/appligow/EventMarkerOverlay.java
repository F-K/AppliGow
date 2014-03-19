package com.activity.appligow;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class EventMarkerOverlay {
		  private List<MarkerOptions> listEventMarker ;
		    
		  
		  public EventMarkerOverlay () {
			  this.listEventMarker = new ArrayList<MarkerOptions>();
			  fetchEvents();
		  }
		  
		  //get Events from database
		  public void fetchEvents(){
			  this.listEventMarker.add(new MarkerOptions().position(new LatLng(48.798616, 2.382622)).title("Basket").snippet("13/03/14 - 15h-16h").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			  this.listEventMarker.add(new MarkerOptions().position(new LatLng(48.826156, 2.359834)).title("Apéro").snippet("12/03/13 - 16h-18h").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			  
		  }
		
		public List<MarkerOptions> getListEventMarker() {
			return listEventMarker;
		}
		
		public void setListEventMarker(List<MarkerOptions> listEventMarker) {
			this.listEventMarker = listEventMarker;
		}
		
		
		
}