public class Transaction implements Comparable<Transaction> {

	/**
	 * ENUM are values you can access directly. For example, if you wanted to set
	 * the priority of a transaction to HIGH, you could do this.priority =
	 * Priority.HIGH; If you want to access the priorities from a different class,
	 * the call becomes Transaction.Priority.HIGH and so on.
	 */
	public enum Priority {
		LOW, NORMAL, HIGH, URGENT
	}

	public enum Type {
		LOAN_APPLICATION, WITHDRAWAL, DEPOSIT
	}

	private final Priority priority;
	private final Account user;
	private final double amount;
	private final Type type;

	/**
	 * compareTo implementation. This compares the Priority value, and the higher
	 * the value, the more important. If the priorities are equal, then compare the
	 * account balances of the two accounts, with a higher account balance being
	 * more important. Refer to
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html for help on how
	 * to work with enums, there are a few ways to do this!
	 * 
	 * @param other the other transaction
	 * @return a positive value if this transaction is MORE important, a negative
	 *         number if it less important, and 0 if they are equal.
	 */
	@Override
	public int compareTo(Transaction other) {
	  if (this.getPriority().compareTo(other.getPriority()) > 0) {
	    return 10;
	  }
	  else if (this.getPriority().compareTo(other.getPriority()) < 0) {
	    return -10;
	  }
	  else {
	    return Double.compare(this.getUser().getBalance(), other.getUser().getBalance());
	  }
	}
	
	

	/**
	 * Constructor for the Transaction class
	 *
	 * Rules for the priority are: If it is a deposit, the priority is HIGH. If it is
	 * a withdrawal, the priority is NORMAL. If it is a loan application which
	 * exceeds 3x the account balance, it is LOW If it is a loan application which
	 * does not exceed the 3x threshold, it is URGENT
	 *
	 * One tip: enums can be used in switch statements!
	 *
	 * @param user   The account associated with the transaction
	 * @param amount the amount related to this transaction
	 * @param type   the transaction type
	 * @throws IllegalArgumentException if the amount is 0 or less
	 */
	public Transaction(Account user, double amount, Type type) {
	  if (amount <= 0) {
        throw new IllegalArgumentException("Invalid amount!");
      }
	  
	  this.user = user;
	  this.amount = amount;
	  this.type = type;
	  
	  switch(type) {
	    case DEPOSIT:
          this.priority = Priority.HIGH;
          break;
        case WITHDRAWAL:
          this.priority = Priority.NORMAL;
          break;
        case LOAN_APPLICATION:
          if (amount > 3 * user.getBalance()) {
            this.priority = Priority.LOW;
          } 
          else {
            this.priority = Priority.URGENT;
          }
          break;
        default:
          throw new IllegalArgumentException("Invalid type!");
	  }
	}
	

	/**
	 * Getter for the account
	 * 
	 * @return the account for this transaction
	 */
	public Account getUser() {
		return user;
	}

	/**
	 * Getter for the amount
	 * 
	 * @return the amount for this transaction
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Getter for the type
	 * 
	 * @return the type for this transaction
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Getter for the priority
	 * 
	 * @return the priority for this transaction
	 */
	public Priority getPriority() {
		return priority;
	}

}
