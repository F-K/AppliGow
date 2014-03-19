package controller.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Service;
import model.event.CreateEventService;
import model.event.Event;
import model.user.UserManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.activity.appligow.MainMapActivity;
import com.activity.appligow.R;

import controller.library.FrontController;

public class CreateEventListener implements OnClickListener {
	
	private EditText title, description;
	private Spinner category;
	private final String ADDRESS;
	private final double LATITUDE, LONGITUDE;
	private DatePicker datePickerStart, datePickerEnd;
	private TimePicker timePickerStart, timePickerEnd;
	
	public CreateEventListener(EditText title, String ADDRESS, double LATITUDE, double LONGITUDE, Spinner category, EditText description,
			DatePicker datePickerStart, DatePicker datePickerEnd, TimePicker timePickerStart, TimePicker timePickerEnd) {
		this.title = title;
		this.ADDRESS = ADDRESS;
		this.LATITUDE = LATITUDE;
		this.LONGITUDE = LONGITUDE;
		this.category = category;
		this.description = description;
		this.datePickerStart = datePickerStart;
		this.datePickerEnd = datePickerEnd;
		this.timePickerStart = timePickerStart;
		this.timePickerEnd = timePickerEnd;
	}

	@Override
	public void onClick(View v) {
		// Retrieve informations
		final String TITLE = title.getText().toString();
		final String CATEGORY = category.getSelectedItem().toString();
		final String DESCRIPTION = description.getText().toString();
		final String DATE_START = datePickerStart.getYear() + "-" + ((datePickerStart.getMonth()+1)%13) + "-" + datePickerStart.getDayOfMonth();
		final String DATE_END = datePickerEnd.getYear() + "-" + (datePickerEnd.getMonth()+1%13) + "-" + datePickerEnd.getDayOfMonth();
		final String TIME_START = timePickerStart.getCurrentHour() + ":" + timePickerStart.getCurrentMinute() + ":00";
		final String TIME_END = timePickerEnd.getCurrentHour() + ":" + timePickerEnd.getCurrentMinute() + ":00";
		
		//title is required
		if(TITLE == null || TITLE.equals("")) {
			Toast.makeText(v.getContext(), R.string.title_required, Toast.LENGTH_LONG).show();
			return;
		}
		
		//convert date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		Date dateStart = null, dateEnd = null;
		try {
			dateStart = sdf.parse(DATE_START + " " + TIME_START);
			dateEnd = sdf.parse(DATE_END + " " + TIME_END);
		} catch (ParseException e) {
			Toast.makeText(v.getContext(), R.string.error_server, Toast.LENGTH_SHORT).show();
			return;
		}
		
		//build the event
		Event event = new Event();
		event.setUser(UserManager.getUser());
		event.setTitle(TITLE);
		event.setAddress(ADDRESS);
		event.setLatitude(LATITUDE);
		event.setLongitude(LONGITUDE);
		event.setCategory(CATEGORY);
		event.setDescription(DESCRIPTION);
		event.setDateStart(dateStart);
		event.setDateEnd(dateEnd);
		
		// Start the service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(R.string.port));
		try {
			Service service = new CreateEventService(event, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if(service.getSuccess()) {
				Toast.makeText(v.getContext(), R.string.event_created, Toast.LENGTH_LONG).show();
				FrontController.redirect(v.getContext(), MainMapActivity.class);
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(), R.string.error_server, Toast.LENGTH_SHORT).show();
		}
	}

}
