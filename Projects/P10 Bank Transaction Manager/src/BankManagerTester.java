import java.util.NoSuchElementException;

public class BankManagerTester {

	/**
	 * Tests the constructor for the Transaction class.
	 * 
	 * @return true if the test passes
	 */
	public static boolean testTransactionConstructor() {
	  Account a1 = new Account(1, 100);
	  
	  //Check deposit -> High priority
	  Transaction t1;
	  try {
	    t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
	  }
	  catch (Exception e) {
	    return false;
	  }
	  
      if (!t1.getPriority().equals(Transaction.Priority.HIGH)) {
        return false;
      }
      
      //Check Withdrawal -> Normal priority
      Transaction t2;
      try {
        t2 = new Transaction(a1, 50, Transaction.Type.WITHDRAWAL);
      }
      catch (Exception e) {
        return false;
      }
     
      if (!t2.getPriority().equals(Transaction.Priority.NORMAL)) {
        return false;
      }
      
      //Check Loan -> Low priority scenario
      Transaction t3;
      try {
        t3 = new Transaction(a1, 500, Transaction.Type.LOAN_APPLICATION);
      }
      catch (Exception e) {
        return false;
      }
     
      if (!t3.getPriority().equals(Transaction.Priority.LOW)) {
        return false;
      }
      
      //Check Loan -> Urgent priority scenario
      Transaction t4;
      try {
        t4 = new Transaction(a1, 5, Transaction.Type.LOAN_APPLICATION);
      }
      catch (Exception e) {
        return false;
      }
     
      if (!t4.getPriority().equals(Transaction.Priority.URGENT)) {
        return false;
      }
      
      //Check invalid case
      try {
        Transaction t5 = new Transaction(a1, -100, Transaction.Type.DEPOSIT);
        return false;
      }
      catch (IllegalArgumentException e) {
        //DO NOTHING
      }
      
      return true;
	}
	
	
	
    /**
     * tests the Transaction.compareTo when the priorities are different
     * @return true if the test passes
     */
	public static boolean testTransactionCompareToPriority() {
	  Account a1 = new Account(1, 100);
	  Transaction t1 = new Transaction(a1, 100, Transaction.Type.DEPOSIT);
	  Transaction t2 = new Transaction(a1, 10, Transaction.Type.WITHDRAWAL);
	  Transaction t3 = new Transaction(a1, 200, Transaction.Type.LOAN_APPLICATION);
	  Transaction t4 = new Transaction(a1, 1000, Transaction.Type.LOAN_APPLICATION);
	  
	  if (t1.compareTo(t2) <= 0) {
	    return false;
	  }
	  
	  if (t2.compareTo(t4) <= 0) {
        return false;
      }
	  
	  if (t3.compareTo(t1) <= 0) {
        return false;
      }
	  
	  return true;
	}
	
	

    /**
     * tests the Transaction.compareTo when the priorities are the same
     * @return true if the test passes
     */
    public static boolean testTransactionCompareToAccountBalance() {
      Account a1 = new Account(1, 200);
      Account a2 = new Account(2, 300);
      
      Transaction t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
      Transaction t2 = new Transaction(a2, 1000, Transaction.Type.DEPOSIT);
      
      Transaction t3 = new Transaction(a1, 40, Transaction.Type.WITHDRAWAL);
      Transaction t4 = new Transaction(a2, 60, Transaction.Type.WITHDRAWAL);
      
      Transaction t5 = new Transaction(a1, 400, Transaction.Type.LOAN_APPLICATION);
      Transaction t6 = new Transaction(a2, 600, Transaction.Type.LOAN_APPLICATION);
      
      if (t2.compareTo(t1) <= 0) {
        return false;
      }
      
      if (t4.compareTo(t3) <= 0) {
        return false;
      }
      
      if (t6.compareTo(t5) <= 0) {
        return false;
      }
      
      return true;
	}

    
	/**
	 * Tests the TransactionHeap.addTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testAddTransactionToHeap() {
	  Account a1 = new Account(1, 1000);
      Account a2 = new Account(2, 2000);
      Transaction t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
      Transaction t2 = new Transaction(a1, 10, Transaction.Type.LOAN_APPLICATION);
      Transaction t3 = new Transaction(a1, 200, Transaction.Type.WITHDRAWAL);
      Transaction t4 = new Transaction(a2, 100, Transaction.Type.DEPOSIT);
      Transaction t5 = new Transaction(a2, 20, Transaction.Type.WITHDRAWAL);
      Transaction t6 = new Transaction(a2, 10000, Transaction.Type.LOAN_APPLICATION);
      
      TransactionHeap th1 = new TransactionHeap(6);
      
      //1
      th1.addTransaction(t1);
      
      Transaction[] resultingArr = th1.getHeapData();
      if (th1.getSize() != 1 || !resultingArr[0].equals(t1)) {
        return false;
      }
      
      //2
      th1.addTransaction(t2);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 2 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t1)) {
        return false;
      }
      
      //3
      th1.addTransaction(t3);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 3 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t1)
          || !resultingArr[2].equals(t3)) {
        return false;
      }
      
      //4
      th1.addTransaction(t4);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 4 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1)) {
        return false;
      }
      
      //5
      th1.addTransaction(t5);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 5 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1) 
          || !resultingArr[4].equals(t5)) {
        return false;
      }
      
      
      //6
      th1.addTransaction(t6);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 6 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1) 
          || !resultingArr[4].equals(t5) || !resultingArr[5].equals(t6)) {
        return false;
      }
      
      //Add more than the capacity
      try {
        th1.addTransaction(t6);
        return false;
      }
      catch (IllegalStateException e) {
        //DO NOTHING
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 6 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1) 
          || !resultingArr[4].equals(t5) || !resultingArr[5].equals(t6)) {
        return false;
      }
      
      return true;
	}

	
	/**
	 * Tests the TransactionHeap.heapifyUp() and TransactionHeap.heapifyDown()
	 * 
	 * @return true if the test passes
	 */
	public static boolean testHeapify() {
	  
	  //First check heapifyUp():
	  Account a1 = new Account(1, 1000);
	  Account a2 = new Account(2, 2000);
	  Transaction t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
	  Transaction t2 = new Transaction(a1, 10, Transaction.Type.LOAN_APPLICATION);
	  Transaction t3 = new Transaction(a1, 200, Transaction.Type.WITHDRAWAL);
	  Transaction t4 = new Transaction(a2, 100, Transaction.Type.DEPOSIT);
	  Transaction t5 = new Transaction(a2, 20, Transaction.Type.WITHDRAWAL);
	  Transaction t6 = new Transaction(a2, 10000, Transaction.Type.LOAN_APPLICATION);
	  
	  TransactionHeap th1 = new TransactionHeap(6);

	  
	  //1
	  th1.addTransaction(t1);
	  
	  Transaction[] resultingArr = th1.getHeapData();
	  if (th1.getSize() != 1 || !resultingArr[0].equals(t1)) {
	    return false;
	  }
	  
	  //2
	  th1.addTransaction(t2);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 2 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t1)) {
        return false;
      }
      
      //3
      th1.addTransaction(t3);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 3 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t1)
          || !resultingArr[2].equals(t3)) {
        return false;
      }
      
      //4
      th1.addTransaction(t4);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 4 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1)) {
        return false;
      }
      
      //5
      th1.addTransaction(t5);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 5 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1) 
          || !resultingArr[4].equals(t5)) {
        return false;
      }
      
      //6
      th1.addTransaction(t6);
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 6 || !resultingArr[0].equals(t2) || !resultingArr[1].equals(t4)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t1) 
          || !resultingArr[4].equals(t5) || !resultingArr[5].equals(t6)) {
        return false;
      }
      
      
      //Check heapifyDown():
      
      //1
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 5 || !resultingArr[0].equals(t4) || !resultingArr[1].equals(t1)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t6) ||
          !resultingArr[4].equals(t5)) {
        return false;
      }
      
      //2
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 4 || !resultingArr[0].equals(t1) || !resultingArr[1].equals(t5)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t6)) {
        return false;
      }
      
      //3
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 3 || !resultingArr[0].equals(t5) || !resultingArr[1].equals(t6)
          || !resultingArr[2].equals(t3)) {
        return false;
      }
      
      //4
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 2 || !resultingArr[0].equals(t3) || !resultingArr[1].equals(t6)) {
        return false;
      }
      
      //5
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 1 || !resultingArr[0].equals(t6)) {
        return false;
      }
      
      //6
      th1.getNextTransaction();
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 0) {
        return false;
      }

	  return true;
	}
	

	/**
	 * Tests the TransactionHeap.getNextTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testGetNextTransactionFromHeap() {
	  Account a1 = new Account(1, 1000);
      Account a2 = new Account(2, 2000);
      Transaction t1 = new Transaction(a1, 1000, Transaction.Type.DEPOSIT);
      Transaction t2 = new Transaction(a1, 10, Transaction.Type.LOAN_APPLICATION);
      Transaction t3 = new Transaction(a1, 200, Transaction.Type.WITHDRAWAL);
      Transaction t4 = new Transaction(a2, 100, Transaction.Type.DEPOSIT);
      Transaction t5 = new Transaction(a2, 20, Transaction.Type.WITHDRAWAL);
      Transaction t6 = new Transaction(a2, 10000, Transaction.Type.LOAN_APPLICATION);
      
      TransactionHeap th1 = new TransactionHeap(6);
      
      th1.addTransaction(t1);
      th1.addTransaction(t2);
      th1.addTransaction(t3);
      th1.addTransaction(t4);
      th1.addTransaction(t5);
      th1.addTransaction(t6);
      
      //1
      if (!th1.getNextTransaction().equals(t2)) {
        return false;
      }
      
      Transaction[] resultingArr = th1.getHeapData();
      if (th1.getSize() != 5 || !resultingArr[0].equals(t4) || !resultingArr[1].equals(t1)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t6) ||
          !resultingArr[4].equals(t5)) {
        return false;
      }
      
      //2
      if (!th1.getNextTransaction().equals(t4)) {
        return false;
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 4 || !resultingArr[0].equals(t1) || !resultingArr[1].equals(t5)
          || !resultingArr[2].equals(t3) || !resultingArr[3].equals(t6)) {
        return false;
      }
      
      //3
      if (!th1.getNextTransaction().equals(t1)) {
        return false;
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 3 || !resultingArr[0].equals(t5) || !resultingArr[1].equals(t6)
          || !resultingArr[2].equals(t3)) {
        return false;
      }
      
      //4
      if (!th1.getNextTransaction().equals(t5)) {
        return false;
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 2 || !resultingArr[0].equals(t3) || !resultingArr[1].equals(t6)) {
        return false;
      }
      
      //5
      if (!th1.getNextTransaction().equals(t3)) {
        return false;
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 1 || !resultingArr[0].equals(t6)) {
        return false;
      }
      
      //6
      if (!th1.getNextTransaction().equals(t6)) {
        return false;
      }
      
      resultingArr = th1.getHeapData();
      if (th1.getSize() != 0) {
        return false;
      }
      
      //Remove more
      try {
        th1.getNextTransaction();
        return false;
      }
      catch (NoSuchElementException e) {
        //DO NOTHING
      }
	  
	  return true;
	}
	

	/**
	 * Tests the BankManager.queueTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testQueueTransaction() {
	  Account a1 = new Account(1, 10000000);
	  Transaction t1 = new Transaction(a1, 100, Transaction.Type.WITHDRAWAL);
      Transaction t2 = new Transaction(a1, 1000, Transaction.Type.LOAN_APPLICATION);
      Transaction t3 = new Transaction(a1, 100000000, Transaction.Type.DEPOSIT);

	  BankManager bm = new BankManager(1);
	  
	  bm.queueTransaction(t1);
	  Transaction[] resultingArr = bm.low.getHeapData();
	  if (bm.low.getSize() != 1 || !resultingArr[0].equals(t1)) {
	    return false;
	  }
	  
	  bm.queueTransaction(t2);
	  resultingArr = bm.medium.getHeapData();
      if (bm.medium.getSize() != 1 || !resultingArr[0].equals(t2)) {
        return false;
      }
      
      
      bm.queueTransaction(t3);
      resultingArr = bm.high.getHeapData();
      if (bm.high.getSize() != 1 || !resultingArr[0].equals(t3)) {
        return false;
      }
	  
	  return true;
	}
	
	

	/**
	 * Tests the BankManager.performTransaction() method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testPerformTransaction() {
	  Account a1 = new Account(1, 5);
	  
	  Transaction t1 = new Transaction(a1, 5, Transaction.Type.WITHDRAWAL);
	  Transaction t2 = new Transaction(a1, 50, Transaction.Type.DEPOSIT);
	  Transaction t3 = new Transaction(a1, 5, Transaction.Type.LOAN_APPLICATION);
	  Transaction t4 = new Transaction(a1, 555, Transaction.Type.LOAN_APPLICATION);
	  
	  Transaction t5 = new Transaction(a1, 57, Transaction.Type.WITHDRAWAL);
	  
	  BankManager bm = new BankManager(4);
	  BankManager bm2 = new BankManager(1);
	  BankManager bm3 = new BankManager(3);
	  
	  bm.queueTransaction(t1);
	  bm.queueTransaction(t2);
	  bm.queueTransaction(t3);
	  bm.queueTransaction(t4);
	  
	  bm2.queueTransaction(t5);
	  

	  bm.performTransaction();
	  if (bm.low.getSize() != 3 || t3.getUser().getBalance() != 10) {
	    return false;
	  }
	  
	  bm.performTransaction();
	  if (bm.low.getSize() != 2 || t2.getUser().getBalance() != 60) {
        return false;
      }
	  
	  bm.performTransaction();
	  if (bm.low.getSize() != 1 || t1.getUser().getBalance() != 55) {
        return false;
      }
	  
	  bm.performTransaction();
	  if (bm.low.getSize() != 0 || t4.getUser().getBalance() != 55) {
        return false;
      }
	  
	  //Check account overflow exception
	  try {
	    bm2.performTransaction();
	    return false;
	  }
	  catch (IllegalStateException e) {
	    // DO NOTHING
	  }
	  
	  //Check no transaction Exception
	  try {
	    bm3.performTransaction();
	    return false;
	  }
	  catch (NoSuchElementException e) {
	    //DO NOTHING
	  }

	  return true;
	}
	
	
	
	/**
	 * Tests the BankManager.peekHighestPriorityTransaction method
	 * 
	 * @return true if the test passes
	 */
	public static boolean testPeekHighestPriorityTransaction() {
	  Account a1 = new Account(1, 10000000);
      Transaction t1 = new Transaction(a1, 100, Transaction.Type.WITHDRAWAL);
      Transaction t2 = new Transaction(a1, 10, Transaction.Type.LOAN_APPLICATION);
      Transaction t3 = new Transaction(a1, 500, Transaction.Type.DEPOSIT);

      BankManager bm = new BankManager(3);
      bm.queueTransaction(t1);
      bm.queueTransaction(t2);
      bm.queueTransaction(t3);
      
      if (!bm.peekHighestPriorityTransaction().equals(t2) || bm.low.getSize() != 3) {
        return false;
      }
      bm.getNextTransaction();
      
      if (!bm.peekHighestPriorityTransaction().equals(t3) || bm.low.getSize() != 2) {
        return false;
      }
      bm.getNextTransaction();
      
      if (!bm.peekHighestPriorityTransaction().equals(t1) || bm.low.getSize() != 1) {
        return false;
      }

      return true;
	}
	

	public static void main(String[] args) {
		System.out.println("Transaction Constructor Tests: " + (testTransactionConstructor() ? "PASS" : "FAIL"));
		System.out.println("CompareTo Tests for Priority: " + (testTransactionCompareToPriority() ? "PASS" : "FAIL"));
		System.out.println("CompareTo Tests for Account Balance: " + (testTransactionCompareToAccountBalance() ? "PASS" : "FAIL"));
		System.out.println("Testing Add Transaction to Heap: " + (testAddTransactionToHeap() ? "PASS" : "FAIL"));
		System.out.println("Testing Heapify: " + (testHeapify() ? "PASS" : "FAIL"));
		System.out.println("Testing Get Next Transaction: " + (testGetNextTransactionFromHeap() ? "PASS" : "FAIL"));
		System.out.println("Testing Queue Transaction: " + (testQueueTransaction() ? "PASS" : "FAIL"));
		System.out.println("Testing Perform Transaction: " + (testPerformTransaction() ? "PASS" : "FAIL"));
		System.out.println("Testing Peek Highest Priority Transaction: " + (testPeekHighestPriorityTransaction() ? "PASS" : "FAIL"));
	}
}
