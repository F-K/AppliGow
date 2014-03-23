package com.activity.appligow;

import model.user.User;
import model.user.UserManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import controller.library.FrontController;
import controller.user.EditUserListener;

/**
 * The UserEditActivity is an Activity class used to update the user
 * informations.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class UserEditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_edit_activity);

		// get the edit text objects
		EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
		EditText lastName = (EditText) findViewById(R.id.editTextLastName);
		EditText mail = (EditText) findViewById(R.id.editTextMail);
		EditText password = (EditText) findViewById(R.id.editTextPassword);

		// get the user using a UserManager
		User user = UserManager.getUser();

		// if the first name is not null
		if (user.getFirstName() != null) {
			firstName.setText(user.getFirstName());
		}
		
		// if the last name is not null
		if (user.getLastName() != null) {
			lastName.setText(user.getLastName());
		}
		
		// if the mail is not null
		if (user.getMail() != null) {
			mail.setText(user.getMail());
		}

		Button btnEdit = (Button) findViewById(R.id.buttonEdit);
		btnEdit.setOnClickListener(new EditUserListener(firstName, lastName,
				mail, password));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_map:
			FrontController.redirect(this, MainMapActivity.class);
			return true;
		case R.id.menu_events:
			FrontController.redirect(this, EventsUserActivity.class);
			return true;
		case R.id.menu_account_informations:
			FrontController.redirect(this, UserInformationsActivity.class);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
