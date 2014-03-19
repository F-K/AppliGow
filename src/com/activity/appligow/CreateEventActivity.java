package com.activity.appligow;

import controller.event.CreateEventListener;
import controller.library.FrontController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateEventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_create_activity);
		
		Intent intent = getIntent();
		
		//title
		EditText title = (EditText) findViewById(R.id.editTextTitle);
		
		//address
		TextView address = (TextView) findViewById(R.id.textViewAddress);
		address.setText(intent.getStringExtra("address"));
		double latitude = intent.getDoubleExtra("latitude", 0);
		double longitude = intent.getDoubleExtra("longitude", 0);
		
		//category
		Spinner category = (Spinner) findViewById(R.id.spinnerCategory);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		category.setAdapter(categoryAdapter);
		
		//date
		DatePicker datePickerStart = (DatePicker) findViewById(R.id.datePickerDateStart);
		//dateStart.setCalendarViewShown(false);
		DatePicker datePickerEnd = (DatePicker) findViewById(R.id.datePickerDateEnd);
		//dateEnd.setCalendarViewShown(false);
		
		//time
		TimePicker timePickerStart = (TimePicker) findViewById(R.id.timePickerTimeStart);
		timePickerStart.setIs24HourView(true);
		TimePicker timePickerEnd = (TimePicker) findViewById(R.id.timePickerTimeEnd);
		timePickerEnd.setIs24HourView(true);

		//description
		EditText description = (EditText) findViewById(R.id.editTextDescription);
		
		//submit
		Button submit = (Button) findViewById(R.id.buttonSubmit);
		submit.setOnClickListener(new CreateEventListener(title, address.getText().toString(), latitude, longitude,
				category, description, datePickerStart, datePickerEnd, timePickerStart, timePickerEnd));
		
		
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
