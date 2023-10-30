package objects;
import java.util.Scanner;

public class InheritanceFundamental {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		

	}

}

class GeometricObject {
	
}

class Triangle extends GeometricObject {
	private double side1, side2, side3;
	private String dateCreated;
	java.util.Date date = new java.util.Date();
	
	Triangle() {
		side1 = 0;
		side2 = 0;
		side3 = 0;
		dateCreated = date.toString();
	}
	
	Triangle(double s1, double s2, double s3) {
		side1 = s1;
		side2 = s2;
		side3 = s3;
		dateCreated = date.toString();
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
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	@Override
	public String toString() {
		return "Triangle: side1 = " + side1 + "side2  = " + side2 + "side3 = " + side3;
	}
}