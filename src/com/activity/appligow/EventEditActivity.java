package com.activity.appligow;

import java.util.Calendar;

import model.event.Event;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import controller.event.EditEventListener;
import controller.library.FrontController;

/**
 * The EventEditActivity is an Activity class used to modify the informations of
 * an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EventEditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_edit_activity);

		// retrieve event
		Bundle bundle = getIntent().getExtras();
		Event event = (Event) bundle.getSerializable("event");

		// title
		EditText title = (EditText) findViewById(R.id.editTextTitle);
		title.setText(event.getTitle());

		// address
		TextView address = (TextView) findViewById(R.id.textViewAddress);
		address.setText(event.getAddress());

		// category
		Spinner category = (Spinner) findViewById(R.id.spinnerCategory);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter
				.createFromResource(this, R.array.category_array,
						android.R.layout.simple_spinner_item);
		categoryAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		category.setAdapter(categoryAdapter);
		category.setSelection(categoryAdapter.getPosition(event.getCategory()));
		
		//define current date
		final Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.set(Calendar.MILLISECOND, Calendar.MILLISECOND - 1000);

		// date
		DatePicker datePickerStart = (DatePicker) findViewById(R.id.datePickerDateStart);
		DatePicker datePickerEnd = (DatePicker) findViewById(R.id.datePickerDateEnd);
		datePickerStart.setMinDate(currentCalendar.getTimeInMillis());
		datePickerEnd.setMinDate(currentCalendar.getTimeInMillis());

		// time
		TimePicker timePickerStart = (TimePicker) findViewById(R.id.timePickerTimeStart);
		timePickerStart.setIs24HourView(true);
		timePickerStart.setCurrentHour(currentCalendar.get(Calendar.HOUR_OF_DAY));
		TimePicker timePickerEnd = (TimePicker) findViewById(R.id.timePickerTimeEnd);
		timePickerEnd.setIs24HourView(true);
		timePickerEnd.setCurrentHour(currentCalendar.get(Calendar.HOUR_OF_DAY));

		// description
		EditText description = (EditText) findViewById(R.id.editTextDescription);
		description.setText(event.getDescription());

		// button edit
		Button submit = (Button) findViewById(R.id.buttonEdit);
		submit.setOnClickListener(new EditEventListener(event, title, category,
				description, datePickerStart, datePickerEnd, timePickerStart,
				timePickerEnd));

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
