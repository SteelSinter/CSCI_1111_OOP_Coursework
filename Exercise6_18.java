package exercises;
import java.util.Scanner;

public class Exercise6_18 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a password: ");
		String password = input.nextLine();
		
		if (isValid(password))
			System.out.println("Valid password.");
		else
			System.out.println("Invalid password.");

	}
	
	public static boolean isValid(String s) {
		char[] array = s.toCharArray();
		int digits = 0;
		
		if (s.length() < 8)
			return false;
		
		for (int i = 0; i < array.length; i++) {
			if (!Character.isLetter(array[i]) && !Character.isDigit(array[i]))
				return false;
			if (Character.isDigit(array[i]))
				digits++;
		}
		
		if (digits < 2)
			return false;
		return true;
	}

}
