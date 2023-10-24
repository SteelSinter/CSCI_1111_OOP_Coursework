package objects;

public class InstantiatingObjectsFundamental {

	public static void main(String[] args) {
		Rectangle rec1 = new Rectangle(4, 40);
		Rectangle rec2 = new Rectangle(3.5, 35.9);
		
		System.out.println("Rectangle 1:");
		rec1.print();
		System.out.println("Rectangle 2:");
		rec2.print();

	}
	
}

class Rectangle {
	double width;
	double height;
	
	Rectangle() {
		width = 1;
		height = 1;
	}
	
	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	double getArea() {
		return width * height;
	}
	
	double getPerimeter() {
		return width * 2 + height * 2;
	}
	
	void print() {
		System.out.printf("Width: %.3f\rHeight: %.3f\rArea: %.3f\rPerimeter: %.3f\r", width, height, getArea(), getPerimeter());
	}
}