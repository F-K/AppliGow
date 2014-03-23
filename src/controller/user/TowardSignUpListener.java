package controller.user;

import com.activity.appligow.SignUpActivity;

import controller.library.FrontController;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Toward sign up listener controller class.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class TowardSignUpListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		// Redirects to the sign up Activity
		FrontController.redirect(v.getContext(), SignUpActivity.class);
	}

}
