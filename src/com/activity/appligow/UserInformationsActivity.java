package com.activity.appligow;

import model.user.User;
import model.user.UserManager;
import controller.library.FrontController;
import controller.user.TowardEditListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class UserInformationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_informations_activity);
		
		User user = UserManager.getUser();

		if(user.getFirstName() != null) {
			TextView firstName = (TextView) findViewById(R.id.textViewFirstName);
			firstName.setText(user.getFirstName());
		}
		if(user.getLastName() != null) {
			TextView lastName = (TextView) findViewById(R.id.textViewLastName);
			lastName.setText(user.getLastName());
		}
		if(user.getMail() != null) {
			TextView mail = (TextView) findViewById(R.id.textViewMail);
			mail.setText(user.getMail());
		}
		if(user.getLogin() != null) {
			TextView login = (TextView) findViewById(R.id.textViewLogin);
			login.setText(user.getLogin());
		}
		
		Button btnEdit = (Button) findViewById(R.id.buttonEdit);
		btnEdit.setOnClickListener(new TowardEditListener());
		//Button btnDelete = (Button) findViewById(R.id.buttonDelete);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.menu_map :
				FrontController.redirect(this, MainMapActivity.class);
				return true;
			case R.id.menu_events :
				FrontController.redirect(this, EventsUserActivity.class);
				return true;
			case R.id.menu_account_edit :
				FrontController.redirect(this, UserEditActivity.class);
				return true ;
			default :
				return super.onOptionsItemSelected(item);
		}
	}

}
