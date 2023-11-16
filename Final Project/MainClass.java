package bank;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		String option;
		
		do {
			prompt(0);
			option = input();
			
			if (option.equalsIgnoreCase("new")) {
				// create new account
				System.out.println("create account");
			}
			else {
				// sign into account
				System.out.println("sing in");
			}
				
		}while (!option.equalsIgnoreCase("exit"));

	}
	
	public static void prompt(int p) {
		switch (p) {
		case 0:
			System.out.println("Enter pin or 'new' to create a new user.");
			break;
		case 1:
			break;
		default:
			System.out.println("Invalid");
		}
	}
	
	public static String input() {
		String in;
		try (Scanner input = new Scanner(System.in);) {
			in = input.next();
			return in;
		}
		catch (NoSuchElementException e) {
			return "";
		}
	}

}
