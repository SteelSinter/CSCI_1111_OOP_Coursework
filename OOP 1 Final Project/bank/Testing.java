package bank;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Testing {
	User testUser;
	Account testSavings;

	@Test
	public void userTests() {
		assertTrue(User.SEPARATOR == "_");
		assertTrue(User.getUsers().add(new User()));
		assertTrue(User.getUsers() instanceof ArrayList);
		assertTrue(User.getUserPins() instanceof ArrayList);
		testUser = new User("00/00/0000", (short)0000);
		assertTrue(testUser.getDateOfBirth().equals("00/00/0000"));
		assertTrue(testUser.getPin() == 0000);
	}
	
	@Test
	public void accountTests() {
		assertTrue(Account.SEPARATOR == "_");
		assertTrue(Account.getAccounts() instanceof ArrayList);
		testSavings = new SavingsAccount(testUser, "Savings");
	}
	
	@Test
	public void transactionTests() {
		Transaction t = new Transaction(null, null, 0, "note");
		assertTrue(t.getStatus().equals("pending"));
		t.validate();
		assertTrue(t.getStatus().equals("Approved."));
		Transaction t1 = new Transaction(testSavings, null, 10, "note");
		assertFalse(t1.getStatus().equals("Approved."));
	}

}
