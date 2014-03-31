package com.activity.appligow;

import com.google.android.gms.internal.fn;

import model.event.Event;
import model.user.UserManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import controller.event.DeleteEventListener;
import controller.library.FrontController;

/**
 * The EventInformationsActivity is an Activity class that is used to show the
 * informations of an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EventInformationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_informations_activity);

		// retrieve event
		Bundle bundle = getIntent().getExtras();
		Event event = (Event) bundle.getSerializable("event");
		
		// creator
		TextView creator = (TextView) findViewById(R.id.textViewCreator);
		creator.setText(event.getUser().getLogin());

		// title
		TextView title = (TextView) findViewById(R.id.textViewTitle);
		title.setText(event.getTitle());

		// address
		TextView address = (TextView) findViewById(R.id.textViewAddress);
		address.setText(event.getAddress());

		// category
		TextView category = (TextView) findViewById(R.id.textViewCategory);
		category.setText(event.getCategory());

		// date and hour start
		TextView viewDateStart = (TextView) findViewById(R.id.textViewDateStart);
		viewDateStart.setText(event.getDateStart().toLocaleString());

		// date and hour end
		TextView viewDateEnd = (TextView) findViewById(R.id.textViewDateEnd);
		viewDateEnd.setText(event.getDateEnd().toLocaleString());

		// description
		TextView description = (TextView) findViewById(R.id.textViewDescription);
		description.setText(event.getDescription());

		// button
		Button btnEdit = (Button) findViewById(R.id.buttonEdit);
		Button btnDelete = (Button) findViewById(R.id.buttonDelete);
		if (UserManager.getUser().getId().intValue() == event.getUser().getId()
				.intValue()) {
			btnEdit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = getIntent().getExtras();
					Event event = (Event) bundle.getSerializable("event");

					Intent intent = new Intent(EventInformationsActivity.this,
							EventEditActivity.class);
					bundle = new Bundle();
					bundle.putSerializable("event", event);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
			btnDelete.setOnClickListener(new DeleteEventListener(event));
		} else {
			btnEdit.setVisibility(View.INVISIBLE);
			btnDelete.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_map:
			FrontController.redirect(this, MainMapActivity.class);
			return true;
		case R.id.menu_events:
			FrontController.redirect(this, EventsUserActivity.class);
			return true;
		case R.id.menu_account_informations:
			FrontController.redirect(this, UserInformationsActivity.class);
			return true;
		case R.id.menu_account_edit:
			FrontController.redirect(this, UserEditActivity.class);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
