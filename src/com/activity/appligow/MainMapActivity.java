package com.activity.appligow;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import android.location.Address;

import controller.library.FrontController;

public class MainMapActivity extends FragmentActivity implements OnMapLongClickListener, OnInfoWindowClickListener  {
	
	private GoogleMap googleMap;
	private EventMarkerOverlay eventMarker ;
    
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
			
			//réaction aux clics longs sur la map
			googleMap.setOnMapLongClickListener(this);
			
			//réaction aux clics sur les infoWindow des Marker
			googleMap.setOnInfoWindowClickListener(this);
			
			//créé la liste des marqueurs d'evenements
			this.eventMarker = new EventMarkerOverlay();
			
			//on affiche tous les marqueurs de la liste
			for (int i = 0 ; i < eventMarker.getListEventMarker().size() ; i++){
				this.googleMap.addMarker(eventMarker.getListEventMarker().get(i));

			}
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
	//		case R.id.menu_events :
	//			FrontController.redirect(this, EventManagerActivity.class);
	//			return true;
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
	public void onMapLongClick(LatLng point){
		//Toast.makeText(this, coordonateToAdress(point), Toast.LENGTH_SHORT).show();
		FrontController.redirect(this, CreateEventActivity.class);
	}
	/*
	 public String coordonateToAdress(LatLng position) {
		 Geocoder geocoder = new Geocoder(this, Locale.getDefault());

         List<Address> addresses = null;

         try {
             addresses = geocoder.getFromLocation(position.latitude, position.longitude,1);

         } catch (IOException e) {
             e.printStackTrace();
         }

      // If the reverse geocode returned an address
         if (addresses != null && addresses.size() > 0) {
             // Get the first address
             Address address = addresses.get(0);

             String addressText = String.format("%s, %s, %s",
                     // If there's a street address, add it
                     address.getMaxAddressLineIndex() > 0 ?
                             address.getAddressLine(0) : "",
                     // Locality is usually a city
                     address.getLocality(),
                     // The country of the address
                     address.getCountryName());
             // Return the text
             return addressText;
         } else {
             return "No address found";
         }

     }
     */

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(this, marker.getTitle().toString(), Toast.LENGTH_SHORT).show();
		FrontController.redirect(this, EventInformationsActivity.class);
	}
}
