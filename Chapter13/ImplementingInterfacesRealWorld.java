package thing;
import java.util.Scanner;

public class ImplementingInterfacesRealWorld {

	public static void main(String[] args) {
		Triangle[] triangles = new Triangle[5];
		
		for (int i = 0; i < 5; i++) {
			triangles[i] = new Triangle(Math.random() * 10, Math.random() * 10, Math.random() * 10);
		}
		
		for (Triangle t: triangles) {
			System.out.printf("Area: %.3f\r", t.getArea());
			if (t instanceof Colorable) {
				System.out.println("How to color: ");
				t.howToColor();
			}
		}

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

class Triangle extends GeometricObject implements Comparable<Triangle>, Colorable {
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
	
	public void howToColor() {
		System.out.println("Color all three sides.");
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

interface Colorable {
	void howToColor();
}