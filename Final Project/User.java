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
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
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
	public User(String dateOfBirth, short pin) {
		dateCreated = date.toString();
		this.dob = dateOfBirth;
		this.pin = pin;
		id = users.size() - 1;
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
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public static ArrayList<Short> getUserPins() {
		return userPins;
	}
	
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
					break;
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid number number mismatch");
					input.nextLine();
					pin = 0;
					continue;
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
