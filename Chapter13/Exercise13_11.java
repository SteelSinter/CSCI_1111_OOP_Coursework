package thing;

public class Exercise13_11 {

	public static void main(String[] args) {
		Octagon oct = new Octagon(5.5);
		Octagon octClone = oct.clone();
		
		System.out.println(oct.compareTo(octClone));

	}

}

class GeometricObject {
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

class Octagon extends GeometricObject implements Cloneable, Comparable<Octagon> {
	private double sideLength;
	
	public Octagon() {
		sideLength = 1;
	}
	
	public Octagon(double sideLength) {
		this.sideLength = sideLength;
	}
	
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	
	public double getSideLength() {
		return sideLength;
	}
	
	public double getArea() {
		return (2 + (4 / Math.sqrt(2))) * sideLength * sideLength;
	}
	
	@Override
	public Octagon clone() {
		return new Octagon(sideLength);
	}
	
	@Override
	public int compareTo(Octagon o) {
		if (getArea() > o.getArea())
			return 1;
		if (getArea() < o.getArea())
			return -1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "Octagon with a side length of " + sideLength;
	}
}