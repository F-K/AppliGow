package model.protocol;

import java.io.IOException;

import model.user.User;

public interface IProtocolClient {
	
	public void sendService(String service) throws IOException;
	public void sendLogin(String login) throws IOException;
	public void sendPassword(String password) throws IOException;
	public boolean getUserExist() throws ClassNotFoundException, IOException;
	public User getUser() throws ClassNotFoundException, IOException;

}
