package com.activity.appligow;

import controller.user.SignUpListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * The SignUpActivity is an Activity class used by the user to sign up.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_activity);

		EditText login = (EditText) findViewById(R.id.editTextLogin);
		EditText password = (EditText) findViewById(R.id.editTextPassword);
		Button signUp = (Button) findViewById(R.id.buttonSignUp);
		signUp.setOnClickListener(new SignUpListener(login, password));
	}

}
