package exercises;
import java.util.Scanner;

public class Exercise6_20 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String str = input.nextLine();
		
		int nLetters = countLetters(str);
		System.out.printf("'%s' is %d characters long.\rThere are %d letters in '%s'", str, str.length(), countLetters(str), str);

	}
	
	public static int countLetters(String s) {
		int letters = 0;
		char[] array = s.toCharArray();
		
		for (int i = 0; i < array.length; i++) {
			if ((array[i] >= 65 && array[i] <= 90) || (array[i] >= 97 && array[i] <= 122))
				letters++;
		}
		
		return letters;
	}

}
