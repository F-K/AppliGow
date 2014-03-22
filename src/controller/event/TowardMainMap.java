package controller.event;

import com.activity.appligow.MainMapActivity;

import controller.library.FrontController;
import android.view.View;
import android.view.View.OnClickListener;

public class TowardMainMap implements OnClickListener {

	@Override
	public void onClick(View v) {
		FrontController.redirect(v.getContext(), MainMapActivity.class);
	}

}
