package objects;
import java.util.*;

public class Exercise10_7 {
	static String mainMenu = "Enter your id";
	static String menu = "Choose an option:\r1) View balance\r2) Withdraw\r3) Deposit\r4) Exit";
	static int option, acc;
	static double balance, amount;
	static Account[] accounts;

	public static void main(String[] args) {
		accounts = createAccounts();
		
		while(true) {
			printPrompt(0);
			acc = Integer.parseInt(input());
			if(acc <= 10 && acc >= 0) {
				menu();
			}
			else
				System.out.println("Enter a valid id.");
			
		}

	}
	
	public static void menu() {
		do {
			printPrompt(1);
			Account account = accounts[acc];
			option = Integer.parseInt(input());
			balance = account.getBalance();
			
			switch(option) {
			case 1:
				System.out.println("------------------------");
				System.out.printf("Current balance: $%.2f\n", balance);
				System.out.println("------------------------");
				break;
			case 2:
				System.out.println("Amount to withdraw: ");
				amount = (double)(Double.parseDouble(input()));
				account.withdraw(amount);
				balance = account.getBalance();
				System.out.println("---------------------------------------------------");
				System.out.printf("$%.2f withdrawn from account. New balance: $%.2f\n", amount, balance);
				System.out.println("---------------------------------------------------");
				break;
			case 3:
				System.out.println("Amount to deposit: ");
				amount = (double)(Double.parseDouble(input()));
				account.deposit(amount);
				balance = account.getBalance();
				System.out.println("---------------------------------------------------");
				System.out.printf("$%.2f deposited into account. New balance: $%.2f\n", amount, balance);
				System.out.println("---------------------------------------------------");
				break;
			}
		}while(!(option == 4));
	}
	
	public static String input() {
		Scanner input = new Scanner(System.in);
		String str = input.next();
		return str;
	}
	
	public static void printPrompt(int p) {
		switch(p) {
		case 0:
			System.out.println(mainMenu);
			break;
		case 1:
			System.out.println(menu);
			break;
		}
	}
	
	public static Account[] createAccounts() {
		Account[] accounts = new Account[10];
		
		for(int i = 0; i < 10; i++) {
			accounts[i] = new Account(i, 100);
		}
		
		return accounts;
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
	
	public void withdraw(double amount) {
		balance -= amount;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public String getType() {
		return "Account";
	}
	
	@Override
	public String toString() {
		return String.format("Id: %2d Balance: $%.2f Monthly Interest: %04.2%% Date Created: %s", getId(), getBalance(), getMonthlyInterest(), getDateCreated());
	}
}

class CheckingAccount extends Account {
	private double overdraftLimit;
	
	public CheckingAccount() {
		overdraftLimit = 200;
	}
	
	public CheckingAccount(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	
	public void setOverdraftLimit(double limit) {
		overdraftLimit = limit;
	}
	
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	
	@Override
	public void withdraw(double amount) {
		double bal = getBalance();
		if(amount <= bal + overdraftLimit)
			bal -= amount;
	}
	
	@Override
	public String getType() {
		return "Checking";
	}
	
	@Override
	public String toString() {
		return String.format("Id: %2d Type: %s Balance: $%.2f Monthly Interest: %04.2%% Date Created: %s", getId(), getType(), getBalance(), getMonthlyInterest(), getDateCreated());
	}
}

class SavingsAccount extends Account {
	@Override
	public void withdraw(double amount) {
		double bal = getBalance();
		if(amount <= bal)
			bal -= amount;
	}
	
	@Override
	public String getType() {
		return "Savings";
	}
	
	@Override
	public String toString() {
		return String.format("Id: %2d Type: %s Balance: $%.2f Monthly Interest: %04.2%% Date Created: %s", getId(), getType(), getBalance(), getMonthlyInterest(), getDateCreated());
	}
}