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

/**
 * CreateEventListener is an on click listener class used when creates an event.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class CreateEventListener implements OnClickListener {

	/**
	 * EditText objects for the event title and description.
	 */
	private EditText title, description;

	/**
	 * A spinner to choose the category.
	 */
	private Spinner category;

	/**
	 * The string containing the event address.
	 */
	private final String ADDRESS;

	/**
	 * Doubles for the latitude and longitude of the event's place.
	 */
	private final double LATITUDE, LONGITUDE;

	/**
	 * DatePicker objects for the event dates.
	 */
	private DatePicker datePickerStart, datePickerEnd;

	/**
	 * TimePicker objects for the event times.
	 */
	private TimePicker timePickerStart, timePickerEnd;

	/**
	 * Constructor of the class.
	 * 
	 * @param title
	 *            The event title.
	 * @param ADDRESS
	 *            The address value.
	 * @param LATITUDE
	 *            The latitude of the event.
	 * @param LONGITUDE
	 *            The longitude of the event.
	 * @param category
	 *            The category of the event.
	 * @param description
	 *            The description of the event.
	 * @param datePickerStart
	 *            The start date.
	 * @param datePickerEnd
	 *            The end date.
	 * @param timePickerStart
	 *            The start time.
	 * @param timePickerEnd
	 *            The end time.
	 */
	public CreateEventListener(EditText title, String ADDRESS, double LATITUDE,
			double LONGITUDE, Spinner category, EditText description,
			DatePicker datePickerStart, DatePicker datePickerEnd,
			TimePicker timePickerStart, TimePicker timePickerEnd) {
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
		final String DATE_START = datePickerStart.getYear() + "-"
				+ ((datePickerStart.getMonth() + 1) % 13) + "-"
				+ datePickerStart.getDayOfMonth();
		final String DATE_END = datePickerEnd.getYear() + "-"
				+ (datePickerEnd.getMonth() + 1 % 13) + "-"
				+ datePickerEnd.getDayOfMonth();
		final String TIME_START = timePickerStart.getCurrentHour() + ":"
				+ timePickerStart.getCurrentMinute() + ":00";
		final String TIME_END = timePickerEnd.getCurrentHour() + ":"
				+ timePickerEnd.getCurrentMinute() + ":00";

		// title is required
		if (TITLE == null || TITLE.equals("")) {
			Toast.makeText(v.getContext(), R.string.title_required,
					Toast.LENGTH_LONG).show();
			return;
		}

		// convert date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		Date dateStart = null, dateEnd = null;
		try {
			dateStart = sdf.parse(DATE_START + " " + TIME_START);
			dateEnd = sdf.parse(DATE_END + " " + TIME_END);
		} catch (ParseException e) {
			Toast.makeText(v.getContext(), R.string.error_server,
					Toast.LENGTH_SHORT).show();
			return;
		}

		// build the event
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
		final int PORT = Integer.parseInt(v.getContext().getString(
				R.string.port));
		try {
			Service service = new CreateEventService(event, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if (service.getSuccess()) {
				Toast.makeText(v.getContext(), R.string.event_created,
						Toast.LENGTH_LONG).show();
				FrontController.redirect(v.getContext(), MainMapActivity.class);
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(), R.string.error_server,
					Toast.LENGTH_SHORT).show();
		}
	}

}
