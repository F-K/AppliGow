package model.user;

import model.user.User;

public class UserManager {
	
	// The current user for app
	private static User user;
	
	public static User getUser() {
		return user;
	}
	
	public static void setUser(User user) {
		UserManager.user = user;
	}

}
