package bank;
import java.util.*;
/**
 * Stores the data for a user.
 * @author James Jesus
 *
 */
public class User {
	private static ArrayList<Short> userPins = new ArrayList<Short>();
	private static ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private String first, last, dateCreated, dob;
	private short pin;
	private int id;
	
	static Scanner input = new Scanner(System.in);
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
		id = users.size();
		userPins.add(id, (short)0000);
		users.add(id, this);
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
		id = users.size();
		userPins.add(id, pin);
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		dob = dateOfBirth;
	}
	
	public void setName(String first, String last) {
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
	
	public String getName() {
		return first + " " + last;
	}
	
	public String getDateOfBirth() {
		return dob;
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public void createAccount() {
		String option;
		do {
			System.out.println("Account type: ");
			System.out.println("0) checking");
			System.out.println("1) savings");
			option = input.next();
			
			switch (option) {
			case "0":
				getAccounts().add(new CheckingAccount(this, "CHECKING"));
				break;
			case "1":
				getAccounts().add(new SavingsAccount(this, "SAVINGS"));
				break;
			}
		}while (!option.equalsIgnoreCase("exit"));
	}
	
	public void deposit() {
		String option;
		double amount = 0;
		Account acc;
		boolean confirm = false;
		do {
			try {
				System.out.println("Enter amount to deposit: ");
				amount = input.nextDouble();
				input.nextLine();
				System.out.println("Which account would you like to deposit into?");
				for (int i = 0; i < getAccounts().size(); i++) {
					System.out.println(i + ") " + getAccounts().get(i).getName());
				}
				acc = getAccounts().get(input.nextInt());
				input.nextLine();
				confirm = yesNoPrompt();
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid amount.");
				input.nextLine();
			}
		}while(!confirm);
	}
	
	public static boolean yesNoPrompt() {
		String option;
		do {
			System.out.println("Y/N?");
			option = input.next();
		}while (!(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")));
		
		if (option.equalsIgnoreCase("y"))
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%03d\t%04d\t%s %s\t%s\tUser created on %s", id, pin, first, last, dob, dateCreated);
	}
	
}
