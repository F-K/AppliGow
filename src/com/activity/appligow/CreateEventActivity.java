package com.activity.appligow;

import controller.library.FrontController;
import android.os.Bundle;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEventActivity extends FragmentActivity implements OnClickListener{

	private Button startDateButton ;
	private Button endDateButton ;
	private Button startTimeButton ;
	private Button endTimeButton ;
	private Button submitButton ;
	
	private TextView addressTextView ;
	
	private Spinner categorySpinner ;
	
	DialogFragment datePickerFragment ;
	DialogFragment timePickerFragment ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		//intent
		Intent i = getIntent() ;
		
		//start button and end button
		startDateButton = (Button) findViewById(R.id.create_event_date_start_button);
		endDateButton = (Button) findViewById(R.id.create_event_date_end_button);
		startTimeButton = (Button) findViewById(R.id.create_event_time_start_button);
		endTimeButton = (Button) findViewById(R.id.create_event_time_end_button);
		
		//datepicker dialog and timepicker dialog
		datePickerFragment = new DatePickerFragment() ;
		timePickerFragment = new TimePickerFragment() ;
		
		//action on click
		startDateButton.setOnClickListener(this);
		endDateButton.setOnClickListener(this);
		startTimeButton.setOnClickListener(this);
		endTimeButton.setOnClickListener(this);
		
		//address
		addressTextView = (TextView) findViewById(R.id.create_event_address2) ;
		addressTextView.setText(i.getStringExtra("address"));
		
		//category
		categorySpinner = (Spinner) findViewById(R.id.create_event_category_spinner);
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.create_event_category_spinner, android.R.layout.simple_spinner_item);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		categorySpinner.setAdapter(categoryAdapter);
		
		submitButton = (Button) findViewById(R.id.create_event_submit);
		submitButton.setOnClickListener(this);
		
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
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.create_event_date_start_button :
				datePickerFragment.show(getFragmentManager(), "date_picker_start");
			
				break ;
			case R.id.create_event_date_end_button :
				datePickerFragment.show(getFragmentManager(), "date_picker_end");
				break ;
			case R.id.create_event_time_start_button :
				timePickerFragment.show(getFragmentManager(), "time_picker_start");
				break ;
			case R.id.create_event_time_end_button :
				timePickerFragment.show(getFragmentManager(), "time_picker_end");
				break ;
			case R.id.create_event_submit :
				Toast.makeText(this, "SUBMIT", Toast.LENGTH_SHORT).show();
		}
	}	

}
