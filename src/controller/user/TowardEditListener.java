package controller.user;

import android.view.View;
import android.view.View.OnClickListener;

import com.activity.appligow.UserEditActivity;

import controller.library.FrontController;

public class TowardEditListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		// Redirects to user edition
		FrontController.redirect(v.getContext(), UserEditActivity.class);
	}

}
