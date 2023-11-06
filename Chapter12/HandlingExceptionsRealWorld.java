package exceptionHandling;
import java.util.Scanner;

public class HandlingExceptionsRealWorld {

	public static void main(String[] args) {
		int[] integers = new int[100];
		
		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 100; i++) {
			integers[i] = (int)(Math.random() * 10);
		}
		
		while (true) {
			System.out.println(">");
			try {
			System.out.println(integers[input.nextInt()]);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Out of bounds.");
			}
		}

	}

}
