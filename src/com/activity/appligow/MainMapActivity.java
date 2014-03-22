package com.activity.appligow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.event.Event;
import model.event.EventManager;
import model.user.UserManager;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import controller.library.FrontController;

public class MainMapActivity extends FragmentActivity implements OnMapLongClickListener, OnInfoWindowClickListener  {
	
	private GoogleMap googleMap;
	private HashMap<Marker, Event> markers = new HashMap<Marker, Event>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_map_activity);
		
		// get a handle to the map
		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		// if the map is available
		if(googleMap != null) {
			
			// enable the user to recenter the map to its position (using GPS)
			// BUG : Google Play Services not found error with that method, but application works properly
			googleMap.setMyLocationEnabled(true);
			
			// get the user current location
			Location userLocation = getCurrentLocation();
			
			// if the user current location has been found
			if(userLocation != null) {
				// get the user current latitude and longitude
				LatLng currentCoordinates = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
				
				// zoom on his location
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentCoordinates, 12));
			}
			
			// reaction about a long click
			googleMap.setOnMapLongClickListener(this);
			
			// reaction about an infoWindow Marker
			googleMap.setOnInfoWindowClickListener(this);
			
			// initialize all the events in the map
			initEvents();
		}
	}
	
	/**
	 * Get the user current location
	 * @return Location user location
	 */
	private Location getCurrentLocation() {
		// instanciate a location manager
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);	
		
		// get the current location using GPS
		Location currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		// if no location has been found
		if (currentLocation == null) {
			// instance a criteria
	        Criteria criteria = new Criteria();
	        
	        // set the criteria to accuracy coarse
	        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

	        // find a provider that match the criteria
	        String provider = lm.getBestProvider(criteria, true);

	        // get the user location from the provider
	        currentLocation = lm.getLastKnownLocation(provider);
	    }
		
		// return the user current location
		return currentLocation;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
    protected void onStop() {
        super.onStop();
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.menu_map :
				FrontController.redirect(this, MainMapActivity.class);
				return true;
			case R.id.menu_events :
				FrontController.redirect(this, EventsUserActivity.class);
				return true;
			case R.id.menu_account_informations :
				FrontController.redirect(this, UserInformationsActivity.class);
				return true ;
			case R.id.menu_account_edit :
				FrontController.redirect(this, UserEditActivity.class);
				return true ;
			default :
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onMapLongClick(final LatLng point){
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String address =  coordonateToAdress(point) ;
				
				Intent intent = new Intent(MainMapActivity.this, CreateEventActivity.class);
				intent.putExtra("address", address);
				intent.putExtra("latitude", point.latitude);
				intent.putExtra("longitude", point.longitude);
				
				startActivity(intent);
				
			}
		}).start(); 
		
	}
	
	/**
	 * Translate coordonate (LatLng object) to a formatted address (understanding by user)
	 * @param point where the user click
	 * @return formatted address
	 */
	 public String coordonateToAdress(LatLng point) {
		 String address = getString(R.string.error_address);
		 String request = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ point.latitude + "," + point.longitude + "&sensor=true"; // url to generate JSON file (reverse geocoding)
		
			try {				
				
				//fetch JSON file by Http request
				URL url = new URL(request);
				HttpURLConnection co = (HttpURLConnection) url.openConnection();
				InputStream in = co.getInputStream() ;
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String answer ="", temp="";
				while((temp=reader.readLine())!=null){
					answer += temp ;
				}
				reader.close();
				
				//if get JSON file
				if (answer != ""){
					
					//navigate in JSON File node
					JSONObject json1 = new JSONObject(answer);
					JSONArray json2 = json1.getJSONArray("results");
					JSONObject json3 = new JSONObject(json2.getString(0));

					//get the full formatted address
					address = json3.getString("formatted_address") ;
				}
				return address ;
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
	 
		//return at this point means an error occurs in fetch JSON file from the URL above
		 return address ;
     }
	 
	private void initEvents() {
		// retrieve all events
		EventManager.initAllEvents(getString(R.string.server_ip), Integer.parseInt(getString(R.string.port)));
		List<Event> allEvents = EventManager.getAllEvents();
		
		// retrieve the current user id
		int userId = UserManager.getUser().getId();
		
		// retrieve today date in order to compare with the event's end date
		Date dateToday = new Date();
		
		// for each event
		for(Event event : allEvents) {
			// add the event to the map if today < event end date
			if(dateToday.compareTo(event.getDateEnd()) < 0) {
				// build the MarkerOptions
				LatLng position = new LatLng(event.getLatitude(), event.getLongitude());
				MarkerOptions opts = new MarkerOptions().position(position);
				opts.title(event.getTitle());
				opts.snippet(event.getDateStart().toLocaleString());
				
				// display a green marker for the current user
				if(event.getUser().getId() == userId)
					opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				// display a violet marker for the others users
				else
					opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
				
				// add the marker to the Google Maps
				Marker marker = googleMap.addMarker(opts);
				markers.put(marker, event);
			}
		}
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Event event = markers.get(marker);
		Intent intent = new Intent(MainMapActivity.this, EventInformationsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("event", event);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
