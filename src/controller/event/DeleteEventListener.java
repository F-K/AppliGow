package controller.event;

import com.activity.appligow.EventsUserActivity;
import com.activity.appligow.R;

import controller.library.FrontController;
import model.Service;
import model.event.DeleteEventService;
import model.event.Event;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * DeleteEventListener is an on click listener to delete an event.
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 *
 */
public class DeleteEventListener implements OnClickListener {
	
	/**
	 * The event object.
	 */
	private Event event;
	
	/**
	 * Constructor of the class.
	 * @param event The event to be deleted.
	 */
	public DeleteEventListener(Event event) {
		this.event = event;
	}

	@Override
	public void onClick(View v) {
		// Start the service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(R.string.port));
		try {
			Service service = new DeleteEventService(event, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if(service.getSuccess()) {
				Toast.makeText(v.getContext(), R.string.event_deleted, Toast.LENGTH_LONG).show();
				FrontController.redirect(v.getContext(), EventsUserActivity.class);
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(), R.string.error_server, Toast.LENGTH_SHORT).show();
		}
	}

}
