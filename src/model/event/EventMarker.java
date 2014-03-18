package model.event;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventMarker {
	
	MarkerOptions marker ; //marqueur qui apparaitra sur la Map
	int id_event ; //id de l'evenement (à récupérer dans la base de données)
	
	public EventMarker(int id, LatLng pos, String tit, String sub){
		this.id_event = id ;
		marker = new MarkerOptions().position(pos).title(tit).snippet(sub).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	}

	public MarkerOptions getMarker() {
		return marker;
	}

	public void setMarker(MarkerOptions marker) {
		this.marker = marker;
	}

	public int getId_event() {
		return id_event;
	}

	public void setId_event(int id_event) {
		this.id_event = id_event;
	}

}
