package bank;
import java.util.*;

public class Transaction {
	private double amount;
	private Account to, from;
	private String note, status, dateCreated;
	
	Date date = new Date();
	
	/**
	 * Creates a test transaction using the default user.
	 */
	public Transaction() {
		to = Account.accounts.get(0);
		from = Account.accounts.get(0);
		amount = 00.00;
		note = "Test transaction.";
		dateCreated = date.toString();
		status = "pending";
	}
	
	@Override
	public String toString() {
		return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\tSTATUS: %s\tNOTE: %s\t CREATED: %s", to.getName(), from.getName(), amount, status, note, dateCreated);
	}

}
