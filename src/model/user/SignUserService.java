package model.user;

import model.Service;


public abstract class SignUserService extends Service implements Runnable {
	
	private final String LOGIN, PASSWORD;
	
	public SignUserService(String login, String password, String serverIP, int port) {
		super(serverIP, port);
		LOGIN = login;
		PASSWORD = password;
	}
	
	public String getLogin() {
		return LOGIN;
	}
	
	public String getPassword() {
		return PASSWORD;
	}

}
