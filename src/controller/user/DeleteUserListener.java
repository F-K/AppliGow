package controller.user;

import model.Service;
import model.user.DeleteUserService;
import model.user.UserManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.activity.appligow.R;
import com.activity.appligow.SignInActivity;

import controller.library.FrontController;

/**
 * A controller class for deleting a user.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class DeleteUserListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		final Context CONTEXT = v.getContext();
		final String MESSAGE_ERROR = v.getContext().getString(R.string.error_server);
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
						Service service = new DeleteUserService(UserManager.getUser(), SERVER_IP, PORT);
						// Wait until the thread died
						service.join();
						if (service.getSuccess())
							FrontController.redirect(CONTEXT, SignInActivity.class);
						else
							Toast.makeText(CONTEXT, MESSAGE_ERROR, Toast.LENGTH_SHORT).show();
					} catch (InterruptedException e) {
						Toast.makeText(CONTEXT, MESSAGE_ERROR, Toast.LENGTH_SHORT).show();
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
