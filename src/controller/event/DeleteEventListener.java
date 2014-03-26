package controller.event;

import model.Service;
import model.event.DeleteEventService;
import model.event.Event;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.activity.appligow.EventsUserActivity;
import com.activity.appligow.R;

import controller.library.FrontController;

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
		final Context CONTEXT = v.getContext();
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(R.string.port));
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
		// set title
		alertDialogBuilder.setTitle(v.getContext().getString(R.string.dialog_title_delete));
		// set dialog message
		alertDialogBuilder
			.setMessage(v.getContext().getString(R.string.dialog_delete))
			.setCancelable(false)
			.setPositiveButton(v.getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					try {
						Service service = new DeleteEventService(event, SERVER_IP, PORT);
						// Wait until the thread died
						service.join();
						if(service.getSuccess()) {
							Toast.makeText(CONTEXT, R.string.event_deleted, Toast.LENGTH_LONG).show();
							FrontController.redirect(CONTEXT, EventsUserActivity.class);
						}
					} catch (InterruptedException e) {
						Toast.makeText(CONTEXT, R.string.error_server, Toast.LENGTH_SHORT).show();
					}
				}
			}).setNegativeButton(CONTEXT.getString(R.string.no), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
		});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show it
		alertDialog.show();
	}

}
