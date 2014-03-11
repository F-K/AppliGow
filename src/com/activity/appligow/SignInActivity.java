package com.activity.appligow;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import controller.user.SignInListener;
import controller.user.TowardSignUpListener;

public class SignInActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in_activity);
		
		EditText login = (EditText) findViewById(R.id.editTextLogin);
		EditText password = (EditText) findViewById(R.id.editTextPassword);
		Button signIn = (Button) findViewById(R.id.buttonSignIn);
		signIn.setOnClickListener(new SignInListener(login, password));
		Button signUp = (Button) findViewById(R.id.buttonSignUp);
		signUp.setOnClickListener(new TowardSignUpListener());
	}

}
