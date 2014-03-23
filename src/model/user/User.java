package model.user;

import java.io.Serializable;

/**
 * The user model.
 * 
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 * 
 */
public class User implements Serializable {

	/**
	 * A serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The user id.
	 */
	private Integer id;

	/**
	 * The user login.
	 */
	private String login;

	/**
	 * The user password.
	 */
	private String password;

	/**
	 * The user first name.
	 */
	private String firstName;

	/**
	 * The user last name.
	 */
	private String lastName;

	/**
	 * The user mail.
	 */
	private String mail;

	/**
	 * The user status.
	 */
	private Boolean statut;

	/**
	 * Empty constructor.
	 */
	public User() {
	}

	/**
	 * Constructor with a login and a password.
	 * 
	 * @param login
	 *            The login.
	 * @param password
	 *            The password.
	 */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * Get the user ID.
	 * 
	 * @return Integer The id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the user ID.
	 * 
	 * @param id
	 *            The id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the user login.
	 * 
	 * @return String The login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the user login.
	 * 
	 * @param login
	 *            The login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get the user password.
	 * 
	 * @return String The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the user password.
	 * 
	 * @param password
	 *            The password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the user first name.
	 * 
	 * @return String The user first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the user first name.
	 * 
	 * @param firstName
	 *            The user first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the user last name.
	 * 
	 * @return String The last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the user last name.
	 * 
	 * @param lastName
	 *            The last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the user mail.
	 * 
	 * @return String The mail.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Set the user mail.
	 * 
	 * @param mail
	 *            The mail.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Get the user status.
	 * 
	 * @return Boolean True is the user is valid, false if not.
	 */
	public Boolean getStatut() {
		return statut;
	}

	/**
	 * Set the status.
	 * 
	 * @param statut
	 *            The status.
	 */
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}

}
