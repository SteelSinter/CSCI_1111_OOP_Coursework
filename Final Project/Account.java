package bank;
import java.util.*;

public class Account implements SavesData, canMakePayment {
	private int id;
	private User owner;
	private double balance;
	private String dateCreated, name;
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static int numberOfAccounts = 0;
	
	Date date = new Date();
	
	/**
	 * Default constructor creates a default account.
	 */
	public Account() {
		owner = User.getUsers().get(0);
		balance = 0;
		dateCreated = date.toString();
		name = "DEFAULT ACCOUNT";
		id = numberOfAccounts;
		numberOfAccounts++;
	}
	
	public Account(User owner, String name) {
		this.owner = owner;
		id = numberOfAccounts;
		numberOfAccounts++;
		balance = 0;
		dateCreated = date.toString();
		this.name = name;
	}
	
	public static int getNumberOfAccounts() {
		return numberOfAccounts;
	}
	
	public static ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void setBalance(double bal) {
		balance = bal;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}
	
	public int getId() {
		return id;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void deposit(double amount) {
		setBalance(balance + amount);
	}
	
	public void withdraw(double amount) {
		setBalance(balance - amount);
	}
	
	@Override
	public String toString() {
		return String.format("Id: %03d\tName: %s\t\tOwner: %s\tBalance: $%.2f\tCreated on %s", id, name, owner.getName(), balance, dateCreated);
	}
	
	@Override
	public String getData() {
		return "TYPE |Account| ID|" + id + "| OWNER |" + owner + "| BALANCE |" + balance + "| DATECREATED |" + dateCreated + "| NAME |" + name + "| NUMBEROFACCOUNTS |" + numberOfAccounts;
	}
}

class SavingsAccount extends Account {
	private double monthlyInterestRate;
	
	private SavingsAccount() {
		
	}
	
	public SavingsAccount(User owner, String name) {
		super(owner, name);
	}
}

class CheckingAccount extends Account implements canMakePayment {
	private CheckingAccount() {
		
	}
	
	public CheckingAccount(User owner, String name) {
		super(owner, name);
	}
	
}

interface canMakePayment {
	
}