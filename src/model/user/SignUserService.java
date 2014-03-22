package model.user;

import model.Service;
import model.library.Hash;


public abstract class SignUserService extends Service implements Runnable {
	
	private final String LOGIN, PASSWORD;
	
	public SignUserService(String login, String password, String serverIP, int port) {
		super(serverIP, port);
		LOGIN = login;
		PASSWORD = Hash.sha1(password);
	}
	
	public String getLogin() {
		return LOGIN;
	}
	
	public String getPassword() {
		return PASSWORD;
	}

}
