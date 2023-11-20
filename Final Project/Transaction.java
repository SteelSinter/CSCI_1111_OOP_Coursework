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
		to = Account.getAccounts().get(0);
		from = Account.getAccounts().get(0);
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
			return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\tCREATED: %s\tNOTE: %s", "Withdraw", from.getName(), amount, status, dateCreated, note);
		}
		if (from == null) {
			return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\t CREATED: %s\tNOTE: %s", to.getName(), "Deposit", amount, status, dateCreated, note);
		}
		return String.format("TO: %s\tFROM: %s\tAMOUNT: $%.2f\t\tSTATUS: %s\t CREATED: %s\tNOTE: %s", to.getName(), from.getName(), amount, status, dateCreated, note);
	}
	
	@Override
	public String getData() {
		return "TYPE |Transaction| TO |" + to + "| FROM |" + from + "| AMOUNT |" + amount + "| STATUS |" + status + "| DATECREATED |" + dateCreated + "| NOTE |" + note;
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