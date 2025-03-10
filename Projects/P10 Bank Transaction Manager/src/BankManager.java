import java.util.NoSuchElementException;

public class BankManager {
	protected TransactionHeap low;
	protected TransactionHeap medium;
	protected TransactionHeap high;

	public BankManager(int capacity) {
		low = new TransactionHeap(capacity);
		medium = new TransactionHeap(capacity);
		high = new TransactionHeap(capacity);
	}

	/**
	 * Gets  and removes the next transaction from the priority queues. Take the transaction from
	 * the highest available priority queue (high -> medium -> low).
	 * 
	 * @return The next transaction to process, null if there are no transactions.
	 */
	public Transaction getNextTransaction() {
	  if (!this.high.isEmpty()) {
	    return this.high.getNextTransaction();
	  }
	  else if (!this.medium.isEmpty()) {
	    return this.medium.getNextTransaction();
	  }
	  else if (!this.low.isEmpty()) {
	    return this.low.getNextTransaction();
	  }
	  else {
	    return null;
	  }
	}
	
	
	
	/**
	 * Gets the highest priority transaction from the priority queues without removing it. 
	 * Take the transaction from the highest available priority queue (high -> medium -> low).
	 * 
	 * @return the transaction with highest priority from all heaps 
	 * and null if there are no transactions.
	 */
	public Transaction peekHighestPriorityTransaction() {
	  if (!this.high.isEmpty()) {
	    return this.high.peek();
	  }
	  else if (!this.medium.isEmpty()) {
	    return this.medium.peek();
	  }
	  else if (!this.low.isEmpty()) {
	    return this.low.peek();
	  }
	  else {
	    return null;
	  }
	}
	
	

	/**
	 * Adds a transaction to the BankManager according to the amount that the
	 * transaction is for low: < 1000 medium: 1000 <= t < 1000000 high: >= 1000000
	 * 
	 * @param transaction the transaction to add to the BankManager
	 */
	public void queueTransaction(Transaction transaction) {
	  if (transaction.getAmount() < 1000) {
	    this.low.addTransaction(transaction);
	  }
	  else if (transaction.getAmount() < 1000000) {
	    this.medium.addTransaction(transaction);
	  }
	  else {
	    this.high.addTransaction(transaction);
	  }
	}
	
	

	/**
	 * Removes and processes the next transaction in the priority queue. Withdrawals
	 * should remove the amount from the balance, deposits should add the amount to
	 * the balance, and loan applications add the amount to the balance only if the
	 * loan amount is less than ten (10) times the account's current balance.
	 * 
	 * @throws NoSuchElementException if there are no transactions to process
	 * @throws IllegalStateException  if the account would overdraft
	 */
	public void performTransaction() {
	  Transaction processTran = this.getNextTransaction();
	  
	  if (processTran == null) {
        throw new NoSuchElementException("No transactions to process!");
      }
	  
	  if (processTran.getType().equals(Transaction.Type.DEPOSIT)) {
	    processTran.getUser().deposit(processTran.getAmount());
	  }
	  
	  else if (processTran.getType().equals(Transaction.Type.WITHDRAWAL)) {
	    if (processTran.getUser().getBalance() < processTran.getAmount()) {
	      throw new IllegalStateException("Account overdraft!");
	    }
	    else {
	      processTran.getUser().withdraw(processTran.getAmount());
	    }
	  }
	  
	  else {
	    if (processTran.getAmount() < (10 * processTran.getUser().getBalance())) {
	      processTran.getUser().deposit(processTran.getAmount());
	    }
	  }
	}

}
