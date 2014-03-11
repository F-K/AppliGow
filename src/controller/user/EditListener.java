package controller.user;

import model.Service;
import model.user.EditService;
import model.user.User;
import model.user.UserManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.appligow.R;
import com.activity.appligow.UserInformationsActivity;

import controller.library.FrontController;

public class EditListener implements OnClickListener {
	
	private EditText firstName, lastName, mail, password;
	
	public EditListener(EditText firstName, EditText lastName, EditText mail, EditText password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
	}

	@Override
	public void onClick(View v) {
		String message;

		// Retrieve edit text
		final String FIRSTNAME = firstName.getText().toString();
		final String LASTNAME = lastName.getText().toString();
		final String MAIL = mail.getText().toString();
		final String PASSWORD = password.getText().toString();
		
		// Login and password must to be not empty
		if(PASSWORD.equals("")) {
			message = v.getContext().getString(R.string.error_missing_password);
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Start the sign in service
		final String SERVER_IP = v.getContext().getString(R.string.server_ip);
		final int PORT = Integer.parseInt(v.getContext().getString(R.string.port));
		User user = new User();
		user.setId(UserManager.getUser().getId());
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setLogin(UserManager.getUser().getLogin());
		user.setMail(MAIL);
		user.setPassword(PASSWORD);
		try {
			Service service = new EditService(user, SERVER_IP, PORT);
			// Wait until the thread died
			service.join();
			if(service.getSuccess())
				FrontController.redirect(v.getContext(), UserInformationsActivity.class);
			else {
				message = v.getContext().getString(R.string.error_password);
				Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
			}
		} catch (InterruptedException e) {
			Toast.makeText(v.getContext(), v.getContext().getString(R.string.error_server), Toast.LENGTH_SHORT).show();
		}
		/*
		// User doesn't exists in DB
		if(UserManager.getUser() == null) {
			message = v.getContext().getString(R.string.error_sign_in);
			Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
		} else
			FrontController.redirect(v.getContext(), UserInformationsActivity.class);
		*/
	}

}
