package objects;
import java.util.Scanner;

public class Exercise10_7 {
	static String mainMenu = "";
	static String menu = "Choose an option:\r1)View balance\r";

	public static void main(String[] args) {
		String option;
		do {
			//print menu
			option = input();
		}while(!option.equalsIgnoreCase("4"));

	}
	
	public static String input() {
		Scanner input = new Scanner(System.in);
		String str = input.next();
		return str;
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
	
	void printDetails() {
		System.out.printf("%04d\t$%.2f \t$%.2f\t\t\t%s\r", id, balance, getMonthlyInterest(), dateCreated);
	}
}