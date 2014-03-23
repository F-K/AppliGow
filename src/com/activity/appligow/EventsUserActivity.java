package com.activity.appligow;

import java.util.List;

import model.event.Event;
import model.event.EventManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import controller.event.TowardMainMap;
import controller.library.FrontController;

/**
 * The EventsUserActivity an Activity class used to show the several events of
 * one user.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EventsUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_user_activity);

		// Retrieve user own events
		EventManager.initAllEvents(getString(R.string.server_ip),
				Integer.parseInt(getString(R.string.port)));
		List<Event> events = EventManager.getEvents();

		// User haven't event
		if (events.size() == 0) {
			TextView textView = new TextView(this);
			textView.setText(R.string.no_event);
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(18);
			textView.setOnClickListener(new TowardMainMap());
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			addContentView(textView, params);
			return;
		}

		// User have events
		ListView viewListEvents = (ListView) findViewById(R.id.listViewEvents);
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, events);
		// Assign adapter to ListView
		viewListEvents.setAdapter(adapter);
		viewListEvents.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {
				Event event = (Event) parentAdapter.getItemAtPosition(position);
				Intent intent = new Intent(EventsUserActivity.this,
						EventInformationsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("event", event);
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
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
