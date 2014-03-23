package model;

import java.io.IOException;
import java.net.UnknownHostException;

import library.ITransport;
import library.Transport;
import model.protocol.IProtocolClient;
import model.protocol.ProtocolClient;

/**
 * Service class that implements Runnable, used to connect to the server.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public abstract class Service implements Runnable {

	/**
	 * The server IP address.
	 */
	private final String SERVER_IP;

	/**
	 * The server port value.
	 */
	private final int PORT;

	/**
	 * The transport interface.
	 */
	private ITransport transport;

	/**
	 * The client protocole interface.
	 */
	private IProtocolClient protocol;

	/**
	 * A thread.
	 */
	private Thread thread;

	/**
	 * Boolean attribute telling if the connection have failed or succeed.
	 */
	private boolean success = false;

	/**
	 * Constructor.
	 * 
	 * @param serverIP
	 *            The server ip address.
	 * @param port
	 *            The server port number.
	 */
	public Service(String serverIP, int port) {
		SERVER_IP = serverIP;
		PORT = port;
		this.thread = new Thread(this);
		this.thread.start();
	}

	/**
	 * Initialise the service.
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void initService() throws UnknownHostException, IOException {
		transport = new Transport(SERVER_IP, PORT);
		protocol = new ProtocolClient(transport);
	}

	/**
	 * Get the server IP address.
	 * 
	 * @return String The ip address.
	 */
	public String getServerIP() {
		return SERVER_IP;
	}

	/**
	 * Get the server port number.
	 * 
	 * @return int The port number.
	 */
	public int getPort() {
		return PORT;
	}

	/**
	 * Get the client protocol interface.
	 * 
	 * @return IProtocolClient The protocol interface.
	 */
	public IProtocolClient getProtocol() {
		return protocol;
	}

	/**
	 * Set the protocol class.
	 * 
	 * @param protocol
	 *            The protocol.
	 */
	public void setProtocol(IProtocolClient protocol) {
		this.protocol = protocol;
	}

	/**
	 * Join the server.
	 * 
	 * @throws InterruptedException
	 */
	public void join() throws InterruptedException {
		thread.join();
	}

	/**
	 * Get the value of the success attribute.
	 * 
	 * @return boolean Success value.
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * Set the value of the success attribute.
	 * 
	 * @param success
	 *            The success value.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
