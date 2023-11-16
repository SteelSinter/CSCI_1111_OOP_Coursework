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
	
	/**
	 * Creates a transaction.
	 * @param to Account to receive the payment.
	 * @param from Account to send the payment.
	 * @param amount Amount to pay.
	 * @param note Additional notes.
	 */
	public Transaction(Account to, Account from, double amount, String note) {
		this.to = to;
		this.from = from;
		this.amount = Math.abs(amount);
		this.note = note;
		dateCreated = date.toString();
		status = "pending";
	}
	
	@Override
	public String toString() {
		if (to == null) {
			return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\tSTATUS: %s\tNOTE: %s\t CREATED: %s", "Withdraw", from.getName(), amount, status, note, dateCreated);
		}
		if (from == null) {
			return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\tSTATUS: %s\tNOTE: %s\t CREATED: %s", to, "Deposit", amount, status, note, dateCreated);
		}
		return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\tSTATUS: %s\tNOTE: %s\t CREATED: %s", to.getName(), from.getName(), amount, status, note, dateCreated);
	}

}
