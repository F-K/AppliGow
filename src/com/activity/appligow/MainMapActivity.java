package com.activity.appligow;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import controller.library.FrontController;

public class MainMapActivity extends FragmentActivity  {
	
	private GoogleMap googleMap;

    
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
	
}
