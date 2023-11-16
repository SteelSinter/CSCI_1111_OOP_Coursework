package bank;
import java.util.*;

public class MainClass {
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String option;
		
		createDefaults();
		
		do {
			prompt(1);
			option = input();
			
			if (option.equalsIgnoreCase("new")) {
				// create new account
				System.out.println("create account");
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
			prompt(2);
			option = input();
			
			switch (option) {
			case "0":
				currentUser.deposit();//not done
				break;
			case "1":
				// Withdraw
				break;
			case "2":
				// Make payment
				break;
			case "3":
				//Transfer
				break;
			case "4":
				// transaction history
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
	
	public static void prompt(int p) {
		switch (p) {
		case 0:
			System.out.println("Invalid number.");
			break;
		case 1:
			System.out.println("Enter pin or 'new' to create a new user.");
			break;
		case 2: System.out.println(
				"0) Deposit\r"
				+ "1) Withdraw\r"
				+ "2) Make payment\r"
				+ "3) Transfer money\r"
				+ "4) View transaction history\r"
				+ "5) View accounts\r"
				+ "6) Sign out");
			break;
		default:
			System.out.println("Invalid");
		}
	}
	
	public static String input() {
		return input.next();
	}
	
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
	
	public static void createDefaults() {
		User.getUsers().add(new User());
		User.getUsers().get(0).getAccounts().add(new Account());
	}

}
