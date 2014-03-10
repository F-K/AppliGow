package controller.library;

import android.content.Context;
import android.content.Intent;

/** 
 * This class allow to change Activity
 */
public class FrontController {
	
	public static void redirect(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		context.startActivity(intent);
	}

}
