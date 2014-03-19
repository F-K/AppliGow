package controller.event;

import model.Service;
import model.event.EditEventService;
import model.event.Event;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.activity.appligow.EventsUserActivity;
import com.activity.appligow.R;

import controller.library.FrontController;

public class EditEventListener implements OnClickListener {
	
	private Event event;
	
	public EditEventListener(Event event) {
		this.event = event;
	}

	@Override
	public void onClick(View v) {
		// Start the service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(R.string.port));
		try {
			Service service = new EditEventService(event, SERVER_IP, PORT);
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
