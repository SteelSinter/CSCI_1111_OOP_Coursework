package bank;
import java.util.*;
/**
 * Stores the date for a user.
 * @author James Jesus
 *
 */
public class User {
	private static ArrayList<Short> userPins = new ArrayList<Short>();
	private static int totalUsers = 0;
	private String first, last, dateCreated, dob;
	private short pin;
	private int id;
	
	Date date = new Date();
	
	/**
	 * Default constructor creates a default user.
	 */
	public User() {
		first = "Default";
		last = "User";
		dateCreated = date.toString();
		dob = "00/00/0000";
		pin = (short)0000;
		id = totalUsers;
		userPins.add(id, (short)0000);
		totalUsers++;
	}
	/**
	 * Create a user with first and last name, date of birth, and pin.
	 * @param first First name.
	 * @param last Last name.
	 * @param dateOfBirth Date of birth in MM/DD/YYYY format.
	 * @param pin Pin number for the account.
	 */
	public User(String first, String last, String dateOfBirth, short pin) {
		this.first = first;
		this.last = last;
		dateCreated = date.toString();
		this.dob = dateOfBirth;
		this.pin = pin;
		id = totalUsers;
		userPins.add(id, pin);
		totalUsers++;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		dob = dateOfBirth;
	}
	
	public void setUserName(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	public void setPin(short pin) {
		this.pin = pin;
		userPins.add(id, pin);
	}
	
	public short getPin() {
		return pin;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDateOfBirth() {
		return dob;
	}
	
	@Override
	public String toString() {
		return String.format("%03d\t%04d\t%s %s\t%s\tUser created on %s", id, pin, first, last, dob, dateCreated);
	}
	
}
