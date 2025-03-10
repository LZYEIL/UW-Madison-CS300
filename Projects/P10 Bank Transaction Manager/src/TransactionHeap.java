import java.util.NoSuchElementException;

/**
 * Class for implementing a heap/priority queue on Transactions
 */
public class TransactionHeap {
	private Transaction[] transactions;
	private int size;

	
	/**
	 * Initializes transactions array with size capacity
	 * 
	 * @param capacity the length of the transactions heap array
	 */
	public TransactionHeap(int capacity) {
		transactions = new Transaction[capacity];
		size = 0;
	}

	
	
	/**
	 * This method adds a transaction to the heap if space allows.
	 * 
	 * @param transaction the transaction to add to the heap
	 * @throws IllegalStateException if the TransactionHeap is full.
	 */
	public void addTransaction(Transaction transaction) {
	  if (this.transactions.length == this.getSize()) {
	    throw new IllegalStateException("The TransactionHeap is full!");
	  }
	  
	  this.transactions[this.getSize()] = transaction;
	  this.size ++;
	  this.heapifyUp(this.getSize() - 1);
	}
	
	

	/**
	 * Reinforces the heap rules after adding a Transaction to the end
	 * 
	 * @param index the index of the new Transaction
	 */
	public void heapifyUp(int index) {
	  int parentIndex = (index - 1) / 2;
	  
	  if (index > 0 && 
	      this.transactions[parentIndex].compareTo(this.transactions[index]) < 0) {
	    Transaction temp = this.transactions[index];
	    this.transactions[index] = this.transactions[parentIndex];
	    this.transactions[parentIndex] = temp;
	    
	    this.heapifyUp(parentIndex);
	  }
	}
	

	/**
	 * Removes the next transaction from the priority queue
	 * 
	 * @return the next transaction in the priority queue
	 * @throws NoSuchElementException if there are no transactions in the heap
	 */
	public Transaction getNextTransaction() {
	  if (this.isEmpty()) {
	    throw new NoSuchElementException("No transactions in the heap!");
	  }
	  
	  Transaction res = this.transactions[0];
	  this.transactions[0] = this.transactions[this.getSize() - 1];
	  this.transactions[this.getSize() - 1] = null;
	  
	  this.size --;
	  this.heapifyDown(0);
	  return res;
	}



	/**
	 * Enforces the heap conditions after removing a Transaction from the heap
	 * 
	 * @param index the index whose subtree needs to be heapified
	 */
	public void heapifyDown(int index) {
	  int leftChildrenIndex = (2 * index) + 1;
	  int rightChildrenIndex = (2 * index) + 2;
	  int largerIndex = index;
	  
	  //Check left subtree
	  if (leftChildrenIndex < this.getSize() && 
	      this.transactions[leftChildrenIndex].compareTo(this.transactions[largerIndex]) > 0) {
	    largerIndex = leftChildrenIndex;   
	  }
	  
	  //Check right subtree
	  if (rightChildrenIndex < this.getSize() && 
          this.transactions[rightChildrenIndex].compareTo(this.transactions[largerIndex]) > 0) {
        largerIndex = rightChildrenIndex;   
      }
	  
	  if (largerIndex != index) {
	    Transaction temp = this.transactions[index];
	    this.transactions[index] = this.transactions[largerIndex];
	    this.transactions[largerIndex] = temp;
	      
	    this.heapifyDown(largerIndex);
	  }
	  
	  
	}
	
	
	

    /**
    * Returns the highest priority transaction without removing it from the heap
    *
    * @return the highest priority transaction without removing it from the heap.
    * @throws NoSuchElementException if there are no transactions in the heap
    */
    public Transaction peek() {
      if (this.isEmpty()) {
        throw new NoSuchElementException("No transactions in the heap!");
      }
      
      return this.transactions[0];
    }


	/**
	 * Getter method for the heap size
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Tells if the heap has any elements in it
	 * 
	 * @return whether or not the heap is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	/**
	* PROVIDED Creates and returns a deep copy of the heap's array of data.
	*
	* @return the deep copy of the array holding the heap's data
	*/
	public Transaction[] getHeapData() {
	  Transaction[] list = new Transaction[this.transactions.length];
	  
	  for (int i = 0; i < list.length; i++) {
	    list[i] = this.transactions[i];
	  }
	  return list;
	}

}
