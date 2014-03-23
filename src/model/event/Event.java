package model.event;

import java.io.Serializable;
import java.util.Date;

import model.user.User;

/**
 * The event class.
 * @author Tiago DOS SANTOS, François KIM, Philippe PUONG, Axel SAINTILLAN
 *
 */
public class Event implements Serializable {
	
	/**
	 * A serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The event id.
	 */
	private Integer id;
	
	/**
	 * The user that has created the event.
	 */
	private User user;
	
	/**
	 * The event title.
	 */
	private String title;
	
	/**
	 * The event address.
	 */
	private String address;
	
	/**
	 * The event latitude.
	 */
	private double latitude;
	
	/**
	 * The event longitude.
	 */
	private double longitude;
	
	/**
	 * The event category.
	 */
	private String category;
	
	/**
	 * The description of the event.
	 */
	private String description;
	
	/**
	 * The start date of the event.
	 */
	private Date dateStart;
	
	/**
	 * The end date of the event.
	 */
	private Date dateEnd;
	
	/**
	 * Empty constructor.
	 */
	public Event() {}

	/**
	 * Get the id of the event.
	 * @return Integer The event id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id of the event.
	 * @param id The event id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the user of this event.
	 * @return User The User.
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Set the user of this event.
	 * @param user The user.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the title.
	 * @return String the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title.
	 * @param title The title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the address.
	 * @return String The address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address.
	 * @param address The address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the latitude.
	 * @return double The latitude.
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set the event latitude.
	 * @param latitude The latitude.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get the event longitude.
	 * @return double The longitude.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set the event longitude.
	 * @param longitude The longitude.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get the event category.
	 * @return String The category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set the category of the event.
	 * @param category The category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get the event description.
	 * @return The description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the event.
	 * @param description The description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the start date.
	 * @return Date The date.
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * Set the start date of the event.
	 * @param dateStart The start date.
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Get the end date.
	 * @return Date The date.
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * Set the end date of the event.
	 * @param dateEnd The date.
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	@Override
	public String toString() {
		return title;
	}

}
