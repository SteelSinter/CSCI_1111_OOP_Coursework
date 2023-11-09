package scscz;
import java.util.Scanner;

public class ExtendingClassesRealWorld {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter 3 sides separated by spaces to create a triangle. ");
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		
		System.out.println("Enter a color: ");
		String color = input.next();
		
		System.out.println("Enter true or false to fill color:");
		boolean filled = input.nextBoolean();
		
		Triangle tri = new Triangle(side1, side2, side3);
		
		tri.setColor(color);
		tri.setFilled(filled);
		
		System.out.println(tri.toString());

	}

}

abstract class GeometricObject {
	protected String color;
	protected Boolean filled;
	protected String dateCreated;
	protected java.util.Date date = new java.util.Date();
	
	protected GeometricObject() {
		color = "White";
		filled = false;
		dateCreated = date.toString();
	}
	
	public void setColor(String str) {
		color = str;
	}
	
	public void setFilled(boolean b) {
		filled = b;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public boolean isFilled() {
		return filled;
	}
}

class Triangle extends GeometricObject implements Comparable<Triangle> {
	private double side1, side2, side3;
	
	public Triangle() {
		side1 = 0;
		side2 = 0;
		side3 = 0;
	}
	
	public Triangle(double s1, double s2, double s3) {
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	
	public double getSide1() {
		return side1;
	}
	
	public double getSide2() {
		return side2;
	}
	
	public double getSide3() {
		return side3;
	}
	
	public double getArea() {
		return (side1 + side2 + side3) / 2;
	}
	
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	@Override
	public int compareTo(Triangle o) {
		if (getArea() > o.getArea())
			return 1;
		if (getArea() < o.getArea())
			return -1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return String.format("Triangle with area: %.2f perimiter: %.2f color: %s filled: %b\r\n", getArea(), getPerimeter(), getColor(), isFilled());
	}
}