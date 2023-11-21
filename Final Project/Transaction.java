package bank;
import java.util.*;

public class Transaction implements SavesData{
	private double amount = 0.00;
	private Account to, from;
	private String note, status, dateCreated;
	
	Date date = new Date();
	
	/**
	 * Creates a test transaction using the default user.
	 */
	public Transaction() {
		to = null;
		from = null;
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
	
	public Account getTo() {
		return to;
	}
	
	public Account getFrom() {
		return from;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	@Override
	public String toString() {
		if (to == null) {
			return String.format("TO: %s\t\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\tCREATED: %s\tNOTE: %s", "Withdraw", from.getName(), amount, status, dateCreated, note);
		}
		if (from == null) {
			return String.format("TO: %s\t\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\t CREATED: %s\tNOTE: %s", to.getName(), "Deposit", amount, status, dateCreated, note);
		}
		return String.format("TO: %s\t\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\t CREATED: %s\tNOTE: %s", to.getName(), from.getName(), amount, status, dateCreated, note);
	}
	
	@Override
	public String getData() {
		String to, from;
		if (this.to == null) {
			to = "Withdrawl";
		}
		else {
			to = this.to.getName();
		}
		
		if (this.from == null) {
			from = "Deposit";
		}
		else {
			from = this.from.getName();
		}
		return "TRANSACTIONTRANSACTIONTRANSACTION" + SEPARATOR + to + SEPARATOR + from + SEPARATOR + amount + SEPARATOR + note + SEPARATOR + status + SEPARATOR + dateCreated;
	}
	
	public void deny(String reason) {
		setStatus("Denied, reason: " + reason);
	}
	
	public void accept() {
		if (from != null)
			from.withdraw(amount);
		if (to != null)
			to.deposit(amount);
		setStatus("Approved.");
	}
	
	public void validate() {
		if (from == null) {
			accept();
			return;
		}
		if (!(from instanceof canMakePayment)) {
			deny("Account type cannot make payments or withdrawls.");
			return;
		}
		if (!(from.getBalance() >= getAmount())) {
			deny("Sender has insufficient funds.");
			return;
		}
		
		accept();
		return;
		
	}

}

class Transfer extends Transaction {
	public Transfer(Account to, Account from, double amount, String note) {
		super(to, from, amount, note);
	}
	@Override
	public void validate() {
		if (getFrom() == null) {
			accept();
			return;
		}
		if (!(getFrom().getBalance() >= getAmount())) {
			deny("Sending account has insufficient funds.");
			return;
		}
		
		accept();
		return;
		
	}
}

class StringTransaction extends Transaction {
	private String toAsString, fromAsString, statusAsString, dateCreatedAsString, noteAsString;
	private double amountAsString;
	
	/**
	 * 
	 * @param to
	 * @param from
	 * @param amount
	 * @param note
	 * @param status
	 * @param dateCreated
	 */
	public StringTransaction(String to, String from, double amount, String note, String status, String dateCreated) {
		toAsString = to;
		fromAsString = from;
		amountAsString = amount;
		statusAsString = status;
		dateCreatedAsString = dateCreated;
		noteAsString = note;
	}
	
	@Override
	public String toString() {
		return String.format("TO: %s\t\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\t CREATED: %s\tNOTE: %s", toAsString, fromAsString, amountAsString, statusAsString, dateCreatedAsString, noteAsString);
	}
	@Override
	public String getData() {
		return "TRANSACTIONTRANSACTIONTRANSACTION" + SEPARATOR + toAsString + SEPARATOR + fromAsString + SEPARATOR + amountAsString + SEPARATOR + noteAsString + SEPARATOR + statusAsString + SEPARATOR + dateCreatedAsString;
	}
}