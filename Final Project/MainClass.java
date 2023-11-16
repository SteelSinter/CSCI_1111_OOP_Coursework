package bank;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		String option;
		
		do {
			prompt(1);
			option = input();
			
			if (option.equalsIgnoreCase("new")) {
				// create new account
				System.out.println("create account");
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
		
		for (User u: User.users) {
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
			option = input();
			
			switch (option) {
			case "0":
				//
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
		default:
			System.out.println("Invalid");
		}
	}
	
	public static String input() {
		Scanner input = new Scanner(System.in);
		return input.next();
	}

}
