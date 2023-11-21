package bank;
import java.util.*;

/**
 * A Transaction is used to send money from one Account to another. A Transaction needs to be validated before the money is transfered.
 * @author James Jesus
 *
 */
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
	
	/**
	 * 
	 * @return Account the payment will be made to.
	 */
	public Account getTo() {
		return to;
	}
	
	/**
	 * 
	 * @return Account the payment will be taken from.
	 */
	public Account getFrom() {
		return from;
	}
	
	/**
	 * 
	 * @return Amount the transaction was.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * 
	 * @return Status of the transaction.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the transaction.
	 * @param s Status.
	 */
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
			to = "Withdrawal";
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
	
	/**
	 * Denies the transaction.
	 * @param reason Reason to attach to the status.
	 */
	public void deny(String reason) {
		setStatus("Denied, reason: " + reason);
	}
	
	/**
	 * Accepts the transaction.
	 */
	public void accept() {
		if (from != null)
			from.withdraw(amount);
		if (to != null)
			to.deposit(amount);
		setStatus("Approved.");
	}
	
	/**
	 * Checks if the sending account has enough funds and can make payments then accepts or denies the transaction.
	 * 
	 */
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

/**
 * A transaction that is made between accounts attached to the same user.
 * @author James Jesus
 *
 */
class Transfer extends Transaction {
	/**
	 * Creates a transfer.
	 * @param to Account to receive transfer.
	 * @param from Account to send transfer.
	 * @param amount Amount to send.
	 * @param note Note to attach to the transfer.
	 */
	public Transfer(Account to, Account from, double amount, String note) {
		super(to, from, amount, note);
	}
	
	/**
	 * Overrides the validation so that savings accounts can send money.
	 */
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

/**
 * Transaction that has data saved as Strings to make saving and loading easier.
 * @author James Jesus
 *
 */
class StringTransaction extends Transaction {
	private String toAsString, fromAsString, statusAsString, dateCreatedAsString, noteAsString;
	private double amountAsString;
	
	/**
	 * Creates a String representation of a transaction for easier saving and loading. A transaction cannot be made once it's been saved.
	 * @param to Account that received payment.
	 * @param from Account that made payment.
	 * @param amount Amount sent.
	 * @param note Note attached to transaction.
	 * @param status Final status of transaction.
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