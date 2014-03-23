package controller.user;

import model.Service;
import model.user.DeleteUserService;
import model.user.User;
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

	/**
	 * The user object.
	 */
	private User user;

	/**
	 * Constructor the this class.
	 * 
	 * @param user
	 *            The user to be deleted.
	 */
	public DeleteUserListener(User user) {
		this.user = user;
	}

	@Override
	public void onClick(View v) {
		// Start the sign in service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(
				R.string.port));
		try {
			Service service = new DeleteUserService(user, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if (service.getSuccess())
				FrontController.redirect(v.getContext(), SignInActivity.class);
			else {
				String message = v.getContext()
						.getString(R.string.error_server);
				Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT)
						.show();
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(),
					v.getContext().getString(R.string.error_server),
					Toast.LENGTH_SHORT).show();
		}
	}

}
