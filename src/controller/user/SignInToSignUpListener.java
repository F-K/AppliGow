package controller.user;

import com.activity.appligow.SignUpActivity;

import controller.library.FrontController;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Controller for the sign up listener.
 * 
 * @author Tiago DOS SANTOS, Fran�ois KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class SignInToSignUpListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		// Redirects to the sign up Activity
		FrontController.redirect(v.getContext(), SignUpActivity.class);
	}

}
