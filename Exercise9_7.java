package exercises;

public class Exercise9_7 {

	public static void main(String[] args) {
		Account defaultAccount = new Account();
		Account acc1 = new Account(1, 1000);
		Account acc2 = new Account(2, 7640.76);
		Account acc3 = new Account(3, 645.66);
		
		defaultAccount.setAnnualInterestRate(0.7);
		
		System.out.println("Account\t\t\t\tId\tBalance   \tAnnual%\tMonthly Interest");
		
		printAccountDetails(defaultAccount);
		printAccountDetails(acc1);
		printAccountDetails(acc2);
		printAccountDetails(acc3);
		

	}
	
	public static void printAccountDetails(Account acc) {
		int id = acc.getId();
		double balance = acc.getBalance();
		double annualInterestRate = acc.getAnnualInterestRate();
		double monthlyInterest = acc.getMonthlyInterest();
		
		System.out.printf("%s:\t%02d\t$%.2f   \t%04.2f%%\t$%.2f\r", acc, id, balance, annualInterestRate, monthlyInterest);
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
	
	Account(int id, double balance) {
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