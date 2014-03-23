package controller.library;

import android.content.Context;
import android.content.Intent;

/**
 * This class allow to change Activity.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class FrontController {

	/**
	 * Redirect from a context to a class.
	 * 
	 * @param context
	 *            The context.
	 * @param cls
	 *            The class to redirect to.
	 */
	public static void redirect(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		context.startActivity(intent);
	}

}
