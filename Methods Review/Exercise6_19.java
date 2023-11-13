package exercises;
import java.util.Scanner;

public class Exercise6_19 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the length of the sides of a triangle separated by spaces.");
		double side1, side2, side3;
		
		System.out.print("Side1: ");
		side1 = input.nextDouble();
		System.out.print("Side2: ");
		side2 = input.nextDouble();
		System.out.print("Side3: ");
		side3 = input.nextDouble();
		
		if (!isValid(side1, side2, side3)) {
			System.out.println("Not a valid triangle.");
			System.exit(0);
		}
		
		System.out.printf("Side 1: %.1f\rSide 1: %.1f\rSide 1: %.1f\rArea: %.5f", side1, side2, side3, area(side1, side2, side3));

	}
	
	public static boolean isValid(double side1, double side2, double side3) {
		if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side1 > side2)
			return true;
		return false;
	}
	
	public static double area(double side1, double side2, double side3) {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

}
