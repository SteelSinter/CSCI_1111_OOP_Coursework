package bank;
import java.util.*;

/**
 * Indicates if a class needs to save data.
 * @author James Jesus
 *
 */
interface SavesData {
	/**
	 * Delimiter to separate the data.
	 */
	String SEPARATOR = "_";
	
	/**
	 * Turns the important data into a string to be saved and read.
	 * @return Data for saving.
	 */
	String getData();
}

/**
 * Stores the data for a user.
 * @author James Jesus
 *
 */
public class User implements SavesData {
	private static ArrayList<Short> userPins = new ArrayList<Short>();
	private static ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private String first, last, dateCreated, dob;
	private short pin;
	private int id;
	private static int numberOfUsers = 0;
	
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
		id = numberOfUsers;
		userPins.add(id, (short)0000);
		users.add(id, this);
	}
	/**
	 * Create a user with first and last name, date of birth, and pin.
	 * @param first Name.
	 * @param last Name.
	 * @param dateOfBirth Date of birth in MM/DD/YYYY format.
	 * @param pin Pin number for the account.
	 */
	public User(String dateOfBirth, short pin) {
		dateCreated = date.toString();
		this.dob = dateOfBirth;
		this.pin = pin;
		id = numberOfUsers;
		userPins.add(id, pin);
	}
	
	/**
	 * Sets the date of birth.
	 * @param s String to set the date of birth to.
	 */
	public void setDateOfBirth(String s) {
		dob = s;
	}
	
	/**
	 * Sets first and last name.
	 * @param first First name.
	 * @param last Last name.
	 */
	public void setName(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	/**
	 * Sets the pin.
	 * @param pin Pin.
	 */
	public void setPin(short pin) {
		this.pin = pin;
		userPins.add(id, pin);
	}
	
	/**
	 * Sets the date created.
	 * @param s Date created.
	 */
	public void setDateCreated(String s) {
		dateCreated = s;
	}
	
	/**
	 * Gets the pin.
	 * @return A 4 digit short.
	 */
	public short getPin() {
		return pin;
	}
	
	/**
	 * Gets the id number.
	 * @return An int id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the name.
	 * @return First and last name.
	 */
	public String getName() {
		return first + " " + last;
	}
	
	/**
	 * Gets the date of birth.
	 * @return Date of birth String.
	 */
	public String getDateOfBirth() {
		return dob;
	}
	
	/**
	 * 
	 * @return ArrayList of accounts.
	 */
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * 
	 * @return ArrayList of transactions.
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * 
	 * @return ArrayList of users.
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Used for choosing which account to use when signing in.
	 * @return ArrayList of user pins.
	 */
	public static ArrayList<Short> getUserPins() {
		return userPins;
	}
	
	/**
	 * Dialog prompts for creating a new User.
	 */
	public static void createUser() {
		String option, firstAndLast, dateOfBirth;
		int pin = 0;
		do {
			System.out.println("Enter first and last name: ");
			firstAndLast = input.nextLine();
			System.out.println("Enter date of birth with forward slashes(MM/DD/YYYY): ");
			dateOfBirth = input.nextLine();
			option = dateOfBirth;
			System.out.println("Choose a 4-digit pin: ");
			while (pin == 0) {
				try {
					pin = (int)input.nextInt();
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid number number format");
					input.nextLine();
					pin = 0;
					return;
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid number number mismatch");
					input.nextLine();
					pin = 0;
					return;
				}
				for (Short s: User.getUserPins()) {
					if (s == pin) {
						System.out.println("Pin already taken.");
						pin = 0;
						continue;
					}
				}
				if (String.format("%04d", pin).length() != 4) {
					System.out.println("Pin must be 4 digits");
					pin = 0;
					continue;
				}
				
			}
			input.nextLine();
			User newUser = new User(dateOfBirth, (short)pin);
			String[] firstLast = firstAndLast.split(" ");
			if (firstLast.length == 2) {
				newUser.setName(firstLast[0], firstLast[1]);
			}
			else {
				newUser.setName(firstAndLast, " ");
			}
			if (User.getUsers().add(newUser)) {
				System.out.println("Account created.");
			}
			else
				System.out.println("One or more inputs were invalid. Account not created. Try again.");
			break;
		}while (!option.equalsIgnoreCase("exit"));
	}
	
	/**
	 * Dialog prompts for creating an Account.
	 */
	public void createAccount() {
		String option;
		boolean accCreated = false;
		Account newAcc;
		do {
			System.out.println("Account type: ");
			System.out.println("0) checking");
			System.out.println("1) savings");
			option = input.next();
			
			switch (option) {
			case "0":
				if (getAccounts().add(newAcc = new CheckingAccount(this, "CHECKING"))) {
					accCreated = true;
					System.out.println("Account created");
					newAcc.setId(Account.getNumberOfAccounts());
					newAcc.setName(newAcc.getName() + newAcc.getId());
				}
				break;
			case "1":
				if (getAccounts().add(newAcc = new SavingsAccount(this, "SAVINGS"))) {
					accCreated = true;
					System.out.println("Account created");
					newAcc.setId(Account.getNumberOfAccounts());
					newAcc.setName(newAcc.getName() + newAcc.getId());
				}
				break;
			}
		}while (!accCreated);
	}
	
	/**
	 * Dialog prompts for creating a deposit.
	 */
	public void deposit() {
		String option;
		String note = null;
		double amount = 0;
		Account acc = null;
		boolean confirm = false;
		int i;
		do {
			try {
				System.out.println("Enter amount to deposit: ");
				amount = input.nextDouble();
				input.nextLine();
				if (getAccounts().size() == 0) {
					System.out.println("You have no accounts to deposit into.");
					return;
				}
				System.out.println("Which account would you like to deposit into?");
				for (i = 0; i < getAccounts().size(); i++) {
					System.out.println(i + ") " + getAccounts().get(i).getName());
				}
				acc = getAccounts().get(input.nextInt());
				input.nextLine();
				System.out.println("Additional note:");
				note = input.nextLine();
				confirm = yesNoPrompt();
			}
			catch (NumberFormatException | IndexOutOfBoundsException | InputMismatchException e) {
				System.out.println("Invalid input.");
				input.nextLine();
			}
			if (confirm)
				getTransactions().add(new Transaction(acc, null, amount, note));
				break;
		}while(!confirm);
	}
	
	/**
	 * Dialog prompts for creating a withdrawal.
	 */
	public void withdraw() {
		String option;
		String note = null;
		double amount = 0;
		Account acc = null;
		boolean confirm = false;
		int i;
		do {
			try {
				System.out.println("Enter amount to withdraw: ");
				amount = input.nextDouble();
				input.nextLine();
				if (getAccounts().size() == 0) {
					System.out.println("You have no accounts to withdraw from.");
					return;
				}
				System.out.println("Which account would you like to withdraw from?");
				for (i = 0; i < getAccounts().size(); i++) {
					System.out.println(i + ") " + getAccounts().get(i).getName());
				}
				acc = getAccounts().get(input.nextInt());
				input.nextLine();
				System.out.println("Additional note:");
				note = input.nextLine();
				confirm = yesNoPrompt();
			}
			catch (NumberFormatException | IndexOutOfBoundsException | InputMismatchException e) {
				System.out.println("Invalid input.");
				input.nextLine();
			}
			if (confirm)
				getTransactions().add(new Transaction(null, acc, amount, note));
				break;
		}while(!confirm);
	}
	
	/**
	 * Creates a loop until 'y' or 'n' is entered.
	 * @return True if 'y' false if 'n'.
	 */
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
	
	/**
	 * Dialog prompts for transferring money.
	 */
	public void transfer() {
		String option;
		String note = null;
		double amount = 0;
		Account acc = null;
		Account acc2 = null;
		boolean confirm = false;
		int i;
		do {
			try {
				System.out.println("Enter amount to transfer: ");
				amount = input.nextDouble();
				input.nextLine();
				if (getAccounts().size() == 0) {
					System.out.println("You have no accounts to pay with.");
					return;
				}
				
				System.out.println("Which account would you like to pay from?");
				for (i = 0; i < getAccounts().size(); i++) {
					System.out.println(i + ") " + getAccounts().get(i).getName());
				}
				acc = getAccounts().get(input.nextInt());
				System.out.println("Which account would you like to pay to?");
				for (i = 0; i < getAccounts().size(); i++) {
					System.out.println(i + ") " + getAccounts().get(i).getName());
				}
				acc2 = getAccounts().get(input.nextInt());
				input.nextLine();
				System.out.println("Additional note:");
				note = input.nextLine();
				confirm = yesNoPrompt();
			}
			catch (NumberFormatException | IndexOutOfBoundsException | InputMismatchException e) {
				System.out.println("Invalid input.");
				input.nextLine();
			}
			if (confirm)
				getTransactions().add(new Transfer(acc2, acc, amount, note));
				break;
		}while(!confirm);
	}
	
	@Override
	public String toString() {
		return String.format("ID: %03d\tPIN: %04d\t%s %s\t%s\tUser created on %s\t", id, pin, first, last, dob, dateCreated);
	}
	
	@Override
	public String getData() {
		return "USERUSERUSER" + SEPARATOR + dob + SEPARATOR + pin + SEPARATOR + first + SEPARATOR + last + SEPARATOR + dateCreated;
	}
}
