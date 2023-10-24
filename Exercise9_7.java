package objects;

public class Exercise9_7 {

	public static void main(String[] args) {
		Account defaultAccount = new Account();
		Account acc1 = new Account(6431, 1000);
		Account acc2 = new Account(2523, 7640.76);
		Account acc3 = new Account(3833, 645.66);
		Account acc4 = new Account(1122, 20_000);
		
		defaultAccount.deposit(100);
		defaultAccount.setAnnualInterestRate(6);
		
		acc4.setAnnualInterestRate(4.5);
		acc4.withdraw(2_500);
		acc4.deposit(3_000);
		
		System.out.println("Id\tBalance \tMonthly Interest\tDate Created");
		
		printAccountDetails(defaultAccount);
		printAccountDetails(acc1);
		printAccountDetails(acc2);
		printAccountDetails(acc3);
		printAccountDetails(acc4);

	}
	
	public static void printAccountDetails(Account acc) {
		int id = acc.getId();
		double balance = acc.getBalance();
		double annualInterestRate = acc.getAnnualInterestRate();
		double monthlyInterest = acc.getMonthlyInterest();
		String dateCreated = acc.getDateCreated();
		
		System.out.printf("%04d\t$%.2f \t$%.2f\t\t\t%s\r", id, balance, monthlyInterest, dateCreated);
	}

}

class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
	private String dateCreated;
	
	java.util.Date date = new java.util.Date();
	
	Account() {
		id = 0;
		balance = 0;
		dateCreated = date.toString();
		annualInterestRate = 0;
	}
	
	Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
		dateCreated = date.toString();
		annualInterestRate = 0;
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
	
	void setAnnualInterestRate(double rate) {
		annualInterestRate = rate;
	}
	
	String getDateCreated() {
		return dateCreated;
	}
	
	double getMonthlyInterestRate() {
		return annualInterestRate / 12.0;
	}
	
	double getMonthlyInterest() {
		return balance * getMonthlyInterestRate() / 100;
	}
	
	void withdraw(double amount) {
		balance -= amount;
	}
	
	void deposit(double amount) {
		balance += amount;
	}
}