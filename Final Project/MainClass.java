package bank;
import java.util.*;
import java.io.*;

public class MainClass {
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String option;
		
		createDefaults();
		
		do {
			try {
				save();
			}
			catch (IOException e) {
				System.out.println("IOException");
			}
			prompt(1);
			option = input();
			
			if (option.equalsIgnoreCase("new")) {
				User.createUser();
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
			for (Transaction t: currentUser.getTransactions()) {
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
				// Make payment
				break;
			case "3":
				//Transfer
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
			case "7":
				currentUser.createAccount();// not done
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
				+ "6) Sign out\r"
				+ "7) Create account\r");
			break;
		default:
			System.out.println("Invalid");
		}
	}
	
	public static String input() {
		return input.nextLine();
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
	
	public static void save() throws IOException{
		File file = new File("SaveFile.txt");
		String fileContents = new String();

		if (file.createNewFile())
			System.out.println("Save file created.");
		else
			System.out.println("Existing save file found.");

		try (PrintWriter write = new PrintWriter(file);
		) {
			for (User u: User.getUsers()) {
				write.append(u.toString());
			}
			
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

		try (Scanner read = new Scanner(file);
		) {
			while (read.hasNext()) {
				fileContents = fileContents.concat(read.next());
			}
			
			System.out.println(fileContents);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

}

