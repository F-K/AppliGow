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

public class UserEditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_edit_activity);
		
		EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
		EditText lastName = (EditText) findViewById(R.id.editTextLastName);
		EditText mail = (EditText) findViewById(R.id.editTextMail);
		EditText password = (EditText) findViewById(R.id.editTextPassword);
		
		User user = UserManager.getUser();
		
		if(user.getFirstName() != null) {
			firstName.setText(user.getFirstName());
		}
		if(user.getLastName() != null) {
			lastName.setText(user.getLastName());
		}
		if(user.getMail() != null) {
			mail.setText(user.getMail());
		}
		
		Button btnEdit = (Button) findViewById(R.id.buttonEdit);
		btnEdit.setOnClickListener(new EditUserListener(firstName, lastName, mail, password));
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
	//		case R.id.menu_events :
	//			FrontController.redirect(this, EventManagerActivity.class);
	//			return true;
			case R.id.menu_account_informations :
				FrontController.redirect(this, UserInformationsActivity.class);
				return true ;
//			case R.id.menu_account_edit :
//				FrontController.redirect(this, UserEditActivity.class);
//				return true ;
			default :
				return super.onOptionsItemSelected(item);
		}
	}

}
