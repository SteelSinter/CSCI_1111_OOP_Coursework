package bank;
import java.util.*;

public class Account {
	private int id;
	private User owner;
	private double balance;
	private String dateCreated;
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	
	Date date = new Date();
	
	/**
	 * Default constructor creates a default account.
	 */
	public Account() {
		id = 0000;
		owner = User.users.get(0);
		balance = 0;
		dateCreated = date.toString();
	}
	
	public Account(User owner) {
		this.owner = owner;
		id = accounts.size();
		balance = 0;
		dateCreated = date.toString();
	}
	
	public void setBalance(double bal) {
		balance = bal;
	}
	
	public double getBalance() {
		return balance;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %03d\tOwner:%s\tBalance$%.2f\tCreated on %s", id, owner, balance, dateCreated);
	}
}

class SavingsAccount extends Account {
	private double monthlyInterestRate;
}
