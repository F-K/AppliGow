package model.user;

import java.io.IOException;
import java.net.UnknownHostException;

import library.ITransport;
import library.Transport;
import model.protocol.IProtocolClient;
import model.protocol.ProtocolClient;


public abstract class SignUserService implements Runnable {
	
	private final String LOGIN, PASSWORD, SERVER_IP;
	private final int PORT;
	private ITransport transport;
	private IProtocolClient protocol;
	private Thread thread;
	
	public SignUserService(String login, String password, String serverIP, int port) {
		LOGIN = login;
		PASSWORD = password;
		SERVER_IP = serverIP;
		PORT = port;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void initService() throws UnknownHostException, IOException {
		transport = new Transport(SERVER_IP, PORT);
		protocol = new ProtocolClient(transport);
	}
	
	public String getLogin() {
		return LOGIN;
	}
	
	public String getPassword() {
		return PASSWORD;
	}
	
	public String getServerIP() {
		return SERVER_IP;
	}
	
	public int getPort() {
		return PORT;
	}
	
	public IProtocolClient getProtocol() {
		return protocol;
	}
	
	public void setProtocol(IProtocolClient protocol) {
		this.protocol = protocol;
	}
	
	public void join() throws InterruptedException {
		thread.join();
	}

}
