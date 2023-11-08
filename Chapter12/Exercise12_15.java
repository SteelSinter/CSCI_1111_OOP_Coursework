package exceptionHandling;
import java.io.*;
import java.util.*;

public class Exercise12_15 {

	public static void main(String[] args) throws IOException {
		File file = new File("Exercise12_15.txt");
		int[] array = new int[100];
		
		if (!file.createNewFile())
			System.out.println("File already exists.");
		
		try (
			PrintWriter output = new PrintWriter(file);
		) {
			for (int i = 0; i < 100; i++) {
				int rand = (((int)(Math.random() * 10)));
				array[i] = rand;
				output.print(" " + rand);
			}
		}
		
		Arrays.sort(array);
		
		try (
			Scanner input = new Scanner(file);
		) {
			for (int i = 0; i < 100; i++) {
				System.out.print(array[i] + " ");
			}
		}
		
	}

}
