package com.activity.appligow;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import controller.library.FrontController;

/**
 * The HomeLoadingActivity is an Activity class that display a waiting layout
 * before the application is ready to be used.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class HomeLoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_loading_activity);

		// Set Activity after 3 seconds
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				FrontController.redirect(HomeLoadingActivity.this,
						SignInActivity.class);
				finish(); // no back
			}
		}, Integer.parseInt(getString(R.string.home_loading_time)));
	}

}
