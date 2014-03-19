package com.activity.appligow;

import java.util.List;

import model.event.Event;
import model.event.EventManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import controller.library.FrontController;

public class EventsUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_user_activity);
		
		// Retrieve user own events
		List<Event> events = EventManager.getEvents();
		
		ListView viewListEvents = (ListView) findViewById(R.id.listViewEvents);
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, events);
		// Assign adapter to ListView
		viewListEvents.setAdapter(adapter);
		viewListEvents.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
				Event event = (Event) parentAdapter.getItemAtPosition(position);
				Intent intent = new Intent(EventsUserActivity.this, EventInformationsActivity.class);
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
		switch(item.getItemId()){
			case R.id.menu_map :
				FrontController.redirect(this, MainMapActivity.class);
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
