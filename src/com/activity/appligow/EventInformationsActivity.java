package com.activity.appligow;

import controller.library.FrontController;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EventInformationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_informations_activity);
		
		//retrieve event
		//on going ...
		
		//title
		TextView title = (TextView) findViewById(R.id.textViewTitle);
		title.setText("My title");
		
		//address
		TextView address = (TextView) findViewById(R.id.textViewAddress);
		address.setText("My address");
		
		//category
		TextView category = (TextView) findViewById(R.id.textViewCategory);
		category.setText("Restaurant for example");
		
		//date and hour start
		TextView dateStart = (TextView) findViewById(R.id.textViewDateStart);
		dateStart.setText("03-04-2014 14:00:00");
		
		//date and hour end
		TextView dateEnd = (TextView) findViewById(R.id.textViewDateEnd);
		dateEnd.setText("04-04-2014 18:30:00");
		
		//description
		TextView description = (TextView) findViewById(R.id.textViewDescription);
		description.setText("I am a description for this test biatch !");
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
