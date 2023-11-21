package bank;
import java.util.*;

/**
 * Accounts are for storing and transferring money.
 * @author James Jesus
 *
 */
public class Account implements SavesData {
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
	
	/**
	 * Creates an account.
	 * @param owner Instance of User.
	 * @param name Name of the account.
	 */
	public Account(User owner, String name) {
		this.owner = owner;
		id = numberOfAccounts;
		numberOfAccounts++;
		balance = 0;
		dateCreated = date.toString();
		this.name = name;
	}
	
	/**
	 * 
	 * @return The number of accounts created.
	 */
	public static int getNumberOfAccounts() {
		return numberOfAccounts;
	}
	
	/**
	 * 
	 * @return ArrayList of accounts created.
	 */
	public static ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * Sets the balance for the account.
	 * @param bal Balance to set.
	 */
	public void setBalance(double bal) {
		balance = bal;
	}
	
	/**
	 * Sets the name for the acocunt.
	 * @param name Name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the id of the account.
	 * @param id Id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return The balance of the account.
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Sets the date created.
	 * @param s Date created.
	 */
	public void setDateCreated(String s) {
		dateCreated = s;
	}
	
	/**
	 * 
	 * @return Name of the account.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return Date created.
	 */
	public String getDateCreated() {
		return dateCreated;
	}
	
	/**
	 * 
	 * @return Id of the account.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Instance of User that created the account.
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Adds to the balance.
	 * @param amount Amount to add.
	 */
	public void deposit(double amount) {
		setBalance(balance + amount);
	}
	
	/**
	 * Subtracts from the balance.
	 * @param amount Amount to subtract.
	 */
	public void withdraw(double amount) {
		setBalance(balance - amount);
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s\t\tOwner: %s\tBalance: $%.2f\tCreated on %s", name, owner.getName(), balance, dateCreated);
	}
	
	@Override
	public String getData() {
		return "ACCOUNTACCOUNTACCOUNT" + SEPARATOR + name + SEPARATOR + balance + SEPARATOR + dateCreated;
	}
}

/**
 * An account that cannot make payments or withdrawls.
 * @author James Jesus
 *
 */
class SavingsAccount extends Account {
	
	/**
	 * Default constructor should not be used.
	 */
	private SavingsAccount() {
		
	}
	
	/**
	 * Creates a savings account.
	 * @param owner User that creates the account.
	 * @param name Name of the account.
	 */
	public SavingsAccount(User owner, String name) {
		super(owner, name);
	}
}

/**
 * An account used for paying and withdrawing.
 * @author James Jesus
 *
 */
class CheckingAccount extends Account implements canMakePayment {
	/**
	 * Default constructor should not be used.
	 */
	private CheckingAccount() {
		
	}
	
	/**
	 * Creates a checking account.
	 * @param owner User that creates the account.
	 * @param name Name of the account.
	 */
	public CheckingAccount(User owner, String name) {
		super(owner, name);
	}
	
}

/**
 * Indicates that an account is allowed to pay and withdraw.
 * @author James Jesus
 *
 */
interface canMakePayment {
	
}