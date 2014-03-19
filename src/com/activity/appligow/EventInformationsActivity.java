package com.activity.appligow;

import model.event.Event;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import controller.event.DeleteEventListener;
import controller.event.EditEventListener;
import controller.library.FrontController;

public class EventInformationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_informations_activity);
		
		//retrieve event
		Bundle bundle = getIntent().getExtras();
		Event event = (Event) bundle.getSerializable("event");
		
		//title
		TextView title = (TextView) findViewById(R.id.textViewTitle);
		title.setText(event.getTitle() + "   " + event.getId());
		
		//address
		TextView address = (TextView) findViewById(R.id.textViewAddress);
		address.setText(event.getAddress());
		
		//category
		TextView category = (TextView) findViewById(R.id.textViewCategory);
		category.setText(event.getCategory());
		
		//date and hour start
		TextView viewDateStart = (TextView) findViewById(R.id.textViewDateStart);
		viewDateStart.setText(event.getDateStart().toLocaleString());
		
		//date and hour end
		TextView viewDateEnd = (TextView) findViewById(R.id.textViewDateEnd);
		viewDateEnd.setText(event.getDateEnd().toLocaleString());
		
		//description
		TextView description = (TextView) findViewById(R.id.textViewDescription);
		description.setText(event.getDescription());
		
		//button
		Button btnEdit = (Button) findViewById(R.id.buttonEdit);
		//btnEdit.setOnClickListener(new EditEventListener(event));
		Button btnDelete = (Button) findViewById(R.id.buttonDelete);
		btnDelete.setOnClickListener(new DeleteEventListener(event));
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
	
}
