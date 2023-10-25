package objects;

public class DesigningClassesRealWorld {

	public static void main(String[] args) {
		char[] ch = {'1', '2', '6', '4', '7', '9'};
		
		MyInteger myInt = new MyInteger(5);
		MyInteger myInt1 = new MyInteger(8);
		
		int chAsInt = myInt.parseInt(ch);
		
		System.out.println("char[] as int: " + chAsInt);
		
		myInt.printInfo();
		myInt1.printInfo();

	}

}

class MyInteger {
	int value;
	
	MyInteger(int i) {
		value = i;
	}
	
	int getInt() {
		return value;
	}
	
	boolean isEven() {
		if(value % 2 == 0)
			return true;
		else
			return false;
	}
	
	boolean isOdd() {
		if(!(value % 2 == 0))
			return true;
		else
			return false;
	}
	
	boolean isPrime() {
		if(value % value != 1 && value % 1 == value)
			return false;
		else
			return true;
	}
	
	static boolean isEven(int i) {
		if(i % 2 == 0)
			return true;
		else
			return false;
	}
	
	static boolean isOdd(int i) {
		if(!(i % 2 == 0))
			return true;
		else
			return false;
	}
	
	static boolean isPrime(int i) {
		if(i % i != 1 && i % 1 == i)
			return false;
		else
			return true;
	}
	
	static boolean isEven(MyInteger i) {
		if(i.value % 2 == 0)
			return true;
		else
			return false;
	}
	
	static boolean isOdd(MyInteger i) {
		if(!(i.value % 2 == 0))
			return true;
		else
			return false;
	}
	
	static boolean isPrime(MyInteger i) {
		if(i.value % i.value != 1 && i.value % 1 == i.value)
			return false;
		else
			return true;
	}
	
	boolean equals(int i) {
		if(i == value)
			return true;
		else
			return false;
	}
	
	boolean equals(MyInteger i) {
		if(i.value == value)
			return true;
		else
			return false;
	}
	
	static int parseInt(char[] ch) {
		int result = Integer.parseInt(new String(ch));
		
		return result;
	}
	
	static int parseInt(String str) {
		return Integer.parseInt(str);
	}
	
	void printInfo() {
		System.out.printf("Value: %d\risEven: %b\risOdd: %b\risPrime: %b\requals 5: %b\r", getInt(), isEven(), isOdd(), isPrime(), equals(5));
	}
	
}
