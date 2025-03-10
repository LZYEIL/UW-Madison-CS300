import java.util.Random;

/**
 * Account class which holds basic info about accounts.
 * 
 * @author Caleb
 */
public class Account {
	static final Random rand = new Random();
	private final long accountNumber;
	private double balance;

	public Account(long accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	/**
	 * Initialize a random account with balance 0
	 */
	public Account() {
		this.accountNumber = rand.nextLong();
		this.balance = 0;
	}

	/**
	 * Deposit the given amount into an account balance
	 * 
	 * @param amount the amount to deposit
	 * @throws IllegalArgumentException if the amount is negative
	 */
	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("The deposit amount is negative!");
		}
		this.balance += amount;
	}

	/**
	 * Withdraw the given amount from an account balance
	 * 
	 * @param amount the amount to withdraw
	 * @throws IllegalStateException if there is an attempt to overdraft the
	 *                               account.
	 */
	public void withdraw(double amount) {
		if (amount > this.balance) {
			throw new IllegalStateException("Insufficient funds (WE DON'T DO OVERDRAFTS!)");
		}
		this.balance -= amount;
	}

	/**
	 * getter for balance
	 * 
	 * @return the balance of the account
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * getter for the account number
	 * 
	 * @return the account number
	 */
	public long getAccountNumber() {
		return this.accountNumber;
	}

}
