package bank;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		String option;
		
		do {
			prompt(0);
			option = input();
			
			if option.equ
		}

	}
	
	public static void prompt(int p) {
		switch (p) {
		case 0:
			System.out.println("Enter pin or 'new' to create a new user.");
			break;
		case 1:
			break;
		}
	}
	
	public static String input() {
		try (Scanner input = new Scanner(System.in);) {
			return input.next();
		}
	}

}
