package exercises;

public class Exercise9_7 {

	public static void main(String[] args) {
		Account acc1 = new Account(2, 1000);
		
		System.out.println(acc1.getDateCreated());
		
		acc1.setAnnualInterestRate(.7);
		
		System.out.println(acc1.getMonthlyInterest());

	}

}

class Account {
	private int id;
	private double balance;
	private static double annualInterestRate;
	private String dateCreated;
	
	java.util.Date date = new java.util.Date();
	
	Account() {
		id = 0;
		balance = 0;
		dateCreated = date.toString();
	}
	
	Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
		dateCreated = date.toString();
	}
	
	int getId() {
		return id;
	}
	
	double getBalance() {
		return balance;
	}
	
	double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	void setBalance(double balance) {
		this.balance = balance;
	}
	
	void setAnnualInterestRate(double annualInterestRate) {
		Account.annualInterestRate = annualInterestRate;
	}
	
	String getDateCreated() {
		return dateCreated;
	}
	
	double getMonthlyInterestRate() {
		return Account.annualInterestRate / 12.0;
	}
	
	double getMonthlyInterest() {
		return this.balance * getMonthlyInterestRate();
	}
	
	void withdraw(double amount) {
		this.balance -= amount;
	}
	
	void deposit(double amount) {
		this.balance += amount;
	}
}