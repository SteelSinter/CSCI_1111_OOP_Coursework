package bank;
import java.util.*;
import java.io.*;

/**
 * Main class for navigating the menu.
 * @author James Jesus
 *
 */
public class MainClass {
	public static Scanner input = new Scanner(System.in);
	public static final String SEPARATOR = "_";
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		String option;
		
		//createDefaults();
		try {
			System.out.println("loading data");
			load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		do {
			prompt(1);
			option = input();
			
			if (option.equalsIgnoreCase("new")) {
				User.createUser();
				try {
					save();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (!(option.length() == 4)) {
				prompt(0);
			}
			else {
				try {
					mainMenu(Short.parseShort(option));
				}
				catch (NumberFormatException e) {
					prompt(0);
				}
			}
				
		}while (!option.equalsIgnoreCase("exit"));

	}
	
	/**
	 * Main menu where the user can do everything. Data is saved every time the main menu is displayed.
	 * @param pin User to sign in.
	 */
	public static void mainMenu(short pin) {
		String option = "";
		User currentUser = null;
		
		for (User u: User.getUsers()) {
			if (pin == u.getPin()) {
				currentUser = u;
				break;
			}
		}
		
		if (currentUser == null) {
			option = "exit";
			System.out.println("User not found.");
		}
		
		while (!option.equalsIgnoreCase("exit")) {
			try {
				save();
			}
			catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			}
			prompt(2);
			for (Transaction t: currentUser.getTransactions()) {
				if (t.getStatus().equalsIgnoreCase("pending"))
					t.validate();
			}
			option = input();
			
			switch (option) {
			case "0":
				currentUser.deposit();
				break;
			case "1":
				currentUser.withdraw();
				break;
			case "2":
				currentUser.createAccount();//fix
				break;
			case "3":
				currentUser.transfer();
				break;
			case "4":
				for (Transaction t: currentUser.getTransactions()) {
					System.out.println(t.toString());
				}
				do {
					System.out.println("Return?");
				}while (!yesNoPrompt());
				break;
			case "5":
				for (Account a: currentUser.getAccounts()) {
					System.out.println(a.toString());
				}
				do {
					System.out.println("Return?");
				}while (!yesNoPrompt());
				break;
			case "6":
				option = "exit";
				break;
			default:
				System.out.println("Invalid option.");
			}
			
		}
		
	}
	
	/**
	 * Displays prompts for main menu.
	 * @param p Prompt to display.
	 */
	public static void prompt(int p) {
		switch (p) {
		case 0:
			System.out.println("Invalid number.");
			break;
		case 1:
			System.out.println("Enter your pin to sign in or 'new' to create a new user.");
			break;
		case 2: System.out.print(
				"0) Deposit\r"
				+ "1) Withdraw\r"
				+ "2) Create account\r"
				+ "3) Transfer money\r"
				+ "4) View transaction history\r"
				+ "5) View accounts and balances\r"
				+ "6) Sign out\r");
			break;
		default:
			System.out.println("Invalid");
		}
	}
	
	/**
	 * Gets input from Scanner.
	 * @return Input.
	 */
	public static String input() {
		return input.nextLine();
	}
	
	/**
	 * Loops until 'y' or 'n' is entered.
	 * @return True for 'y' false for 'n'.
	 */
	public static boolean yesNoPrompt() {
		Scanner input = new Scanner(System.in);
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
	 * Creates default accounts for testing.
	 */
	public static void createDefaults() {
		User.getUsers().add(new User());
		User.getUsers().get(0).getAccounts().add(new Account());
	}
	
	/** 
	 * Saves data as text.
	 * @throws IOException
	 */
	public static void save() throws IOException{
		File file = new File("SaveFile.txt");
		String fileContents = new String();

		file.delete();
		
		file = new File("SaveFile.txt");

		try (PrintWriter write = new PrintWriter(file);
		) {
			for (User u: User.getUsers()) {
				write.append(u.getData());
				write.println();
				
				for (Account a: u.getAccounts()) {
					write.append(a.getData());
					write.println();
				}
				for (Transaction t: u.getTransactions()) {
					write.append(t.getData());
					write.println();
				}
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

		
	}
	
	/**
	 * Loads data from file and creates objects with the data.
	 * @throws IOException
	 */
	public static void load() throws IOException{
		File file = new File("SaveFile.txt");
		try (Scanner read = new Scanner(file);) {
			read.useDelimiter(SEPARATOR);
			User newUser = null;
			
			while (read.hasNextLine()) {
				String next = read.next();
				
				if (next.equalsIgnoreCase("useruseruser")) {
					//System.out.println("creating user");
					
					String dob = read.next();
					
					Short pin = read.nextShort();
					String first = read.next();
					String last = read.next();
					String dateCreated = read.nextLine();
					
					//System.out.println(dob + pin + first + last + dateCreated);
							
					newUser = new User(dob, pin);
					newUser.setName(first, last);
					newUser.setDateCreated(dateCreated);
					
					if (User.getUsers().add(newUser)) {
						//System.out.println("user loaded.");
					}
				}
				
				if (read.hasNextLine() && next.equalsIgnoreCase("accountaccountaccount")) {
					//System.out.println("creating account");
					
					String name = read.next();
					double balance = Double.parseDouble(read.next());
					String dateCreated0 = read.nextLine();
					
					//System.out.println(name + balance + dateCreated0);
					
					Account newAccount = new Account(newUser, name);
					newAccount.setBalance(balance);
					newAccount.setDateCreated(dateCreated0);
					
					if (newUser.getAccounts().add(newAccount)) {
						//System.out.println("account loaded.");
					}
					
				}
				
				if (read.hasNextLine() && next.equalsIgnoreCase("transactiontransactiontransaction")) {
					//System.out.println("creating transaction");
					
					String to = read.next();
					String from = read.next();
					double amount = Double.parseDouble(read.next());
					String note = read.next();
					String status = read.next();
					String dateCreated1 = read.nextLine();
					
					//System.out.println(to + from + amount + note + status + dateCreated1);
					
					Transaction newTransaction = new StringTransaction(to, from, amount, note, status, dateCreated1);
					
					if (newUser.getTransactions().add(newTransaction)) {
						//System.out.println("transaction loaded.");
					}
				}
				
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

}
