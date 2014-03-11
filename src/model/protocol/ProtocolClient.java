package model.protocol;

import java.io.IOException;

import library.ITransport;
import model.user.User;

public class ProtocolClient implements IProtocolClient {
	
	private ITransport transport;

	public ProtocolClient(ITransport transport) {
		this.transport = transport;
	}

	@Override
	public void sendService(String service) throws IOException {
		transport.send(service);
	}
	
	@Override
	public void sendLogin(String login) throws IOException {
		transport.send(login);
	}
	
	@Override
	public void sendPassword(String password) throws IOException {
		transport.send(password);
	}

	@Override
	public User getUser() throws ClassNotFoundException, IOException {
		return (User) transport.receive();
	}

	@Override
	public boolean getUserExist() throws ClassNotFoundException, IOException {
		return (Boolean) transport.receive();
	}

	@Override
	public void sendUser(User user) throws IOException {
		transport.send(user);
	}

}
