package controller.user;

import model.Service;
import model.user.SignInService;
import model.user.UserManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.appligow.R;
import com.activity.appligow.UserInformationsActivity;

import controller.library.FrontController;

/**
 * Controller for the sign in listener.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class SignInListener implements OnClickListener {

	/**
	 * EditText objects for the login and password.
	 */
	private EditText login, password;

	/**
	 * Constructor of the class.
	 * 
	 * @param login
	 *            The login.
	 * @param password
	 *            The password.
	 */
	public SignInListener(EditText login, EditText password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public void onClick(View v) {
		String message;

		// Retrieve the login and password from fields
		final String LOGIN = login.getText().toString();
		final String PASSWORD = password.getText().toString();

		// Login and password must to be not empty
		if (LOGIN.equals("") || PASSWORD.equals("")) {
			message = v.getContext().getString(R.string.error_missing_fields);
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			return;
		}

		// Display a loading message
		message = v.getContext().getString(R.string.loading);
		Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();

		// Start the sign in service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(
				R.string.port));
		try {
			Service signUserService = new SignInService(LOGIN, PASSWORD,
					SERVER_IP, PORT);
			// Wait until the thread died
			signUserService.join();
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(),
					v.getContext().getString(R.string.error_server),
					Toast.LENGTH_SHORT).show();
		}

		// User doesn't exists in DB
		if (UserManager.getUser() == null) {
			message = v.getContext().getString(R.string.error_sign_in);
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
		} else
			FrontController.redirect(v.getContext(),
					UserInformationsActivity.class);
	}

}
