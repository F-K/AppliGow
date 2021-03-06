package controller.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Service;
import model.event.EditEventService;
import model.event.Event;
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
 * This class add an on click listener to edit an event
 * 
 * @author Tiago DOS SANTOS, Fran�ois KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class EditEventListener implements OnClickListener {

	/**
	 * The event object.
	 */
	private Event event;

	/**
	 * EditText objects for the event title and the event description.
	 */
	private EditText title, description;

	/**
	 * A Spinner object to choose the event category.
	 */
	private Spinner category;

	/**
	 * DatePicker objects for the debut and end dates.
	 */
	private DatePicker datePickerStart, datePickerEnd;

	/**
	 * TimePicker objects for the debut and end times.
	 */
	private TimePicker timePickerStart, timePickerEnd;

	/**
	 * Constructor of an EditEventListener.
	 * 
	 * @param event
	 *            The event object.
	 * @param title
	 *            The event title.
	 * @param category
	 *            The category of the event.
	 * @param description
	 *            The description of the event.
	 * @param datePickerStart
	 *            The debut date.
	 * @param datePickerEnd
	 *            The end date.
	 * @param timePickerStart
	 *            The debut time.
	 * @param timePickerEnd
	 *            The end time.
	 */
	public EditEventListener(Event event, EditText title, Spinner category,
			EditText description, DatePicker datePickerStart,
			DatePicker datePickerEnd, TimePicker timePickerStart,
			TimePicker timePickerEnd) {
		this.event = event;
		this.title = title;
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

		// set the event
		event.setTitle(TITLE);
		event.setCategory(CATEGORY);
		event.setDescription(DESCRIPTION);
		event.setDateStart(dateStart);
		event.setDateEnd(dateEnd);

		// Start the service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(
				R.string.port));
		try {
			Service service = new EditEventService(event, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if (service.getSuccess()) {
				Toast.makeText(v.getContext(), R.string.event_updated,
						Toast.LENGTH_LONG).show();
				FrontController.redirect(v.getContext(), MainMapActivity.class);
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(), R.string.error_server,
					Toast.LENGTH_SHORT).show();
		}
	}

}
