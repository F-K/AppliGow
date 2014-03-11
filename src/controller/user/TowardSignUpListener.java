package controller.user;

import com.activity.appligow.SignUpActivity;

import controller.library.FrontController;

import android.view.View;
import android.view.View.OnClickListener;

public class TowardSignUpListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		// Redirects to the sign up Activity
		FrontController.redirect(v.getContext(), SignUpActivity.class);
	}

}
