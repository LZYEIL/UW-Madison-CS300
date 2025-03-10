import java.util.ArrayList;
import java.util.Collections;

/**
 * An class to manage, edit, and build partitions. 
 * @author Michelle Jensen
 *
 */
public class Partition implements Comparable<Partition>{

  private final int N; //The value N for this partition.
   
  private ArrayList<Integer> numbers; //ArrayList to hold the set of numbers in a partition.
  private int sum; //The total of all the numbers in this partition's current set.
  protected static boolean orderMatters; //Keeps track if equality should consider sets of numbers
  //in different order are considered the same.
  
  /**Creates a new empty partition of N
   * @param n , the N value for the partition
   */
  public Partition(int n) {
    this.N = n;
    this.numbers = new ArrayList<Integer>();
  }
  
  /**
   * Gets the length (number of numbers) in the partition.
   * @return the length of this partition
   */
  public int length() {return numbers.size();}
  
  /**
   * Gets the N value of the partition
   * @return the n value of this partition
   */
  public int getN() {return this.N;}
  
  /**
   * Gets the number in the partition's set that is at the given index. 
   * @param index, the index of the number to retrieve
   * @return the number in the set at the given index
   * @throws IllegalArgumentException if index is not in range [0, length)
   */
  public int getNumAt(int index) {
    if(index < 0 || index >=numbers.size())
      throw new IllegalArgumentException("Invalid index, must be in the interval "
          + "[0," + (numbers.size()-1) + "]." );
    
    return numbers.get(index);
  }
  
  /**
   * Gets the list of numbers in this partition.
   * @return a deep copy of the numbers list
   */
  public ArrayList<Integer> getAllNums(){
    return deepCopy(this.numbers);
  }
  
  /**
   * Gets the sum of the numbers in the partition.
   * @return the sum of all numbers in the set of this partition
   */
  public int getSum() {return this.sum;}
  
  
  /**
   * Adds a number to the end of the partition set. 
   * @param number the number to add
   */
  public void addNumber(int number) {
    numbers.add(number);
    sum += number;
  }
  
  /**
   * Removes a number from the partition set.
   * @param number the number to remove
   */
  public void removeNumber(int number) {
    numbers.remove((Integer)number);
    sum -= number;
  }

  /**
   * Removes the last number (at the end) of the partition set.
   */
  public void removeLast() {
    int removed = numbers.remove(numbers.size()-1);
    sum -= removed;
  }
  
  
  /**
   * Swaps two numbers in the partition set.
   * @param index1 index of the first number
   * @param index2 index of the second number
   */
  public void swapNumbers(int index1, int index2) {
    int temp = numbers.get(index1);
    numbers.set(index1, numbers.get(index2));
    numbers.set(index2, temp);
  }
  
  /**
   * Resets this partitions to be an empty set.
   */
  public void reset() {
    this.numbers.clear();
    this.sum = 0;
  }
  
  /**
   * Returns a String representation of this partition
   */
  @Override
  public String toString() {
    return "Partition of N=" + this.N + ": " + numbers.toString() + " --"
      + (this.sum == this.N ? "VALID" : "INVALID"); 
  }
  
  /**
   * Makes a copy of this partition.
   * @return a deep copy of this partition  
   */
  public Partition copyOf() {
    Partition copy = new Partition(this.N);
    copy.sum = this.sum;

    copy.numbers = deepCopy(this.numbers);
    
    return copy;
  }
  
  
  /**
   * Checks whether the given partition has the same numbers as this partition.
   * Based on the current value of orderMatters, it will check accordingly.
   * @param p the partition to check against
   * @return true if the list of numbers are the same with respect to orderMatters, false otherwise
   */
  private boolean hasSameNumbers(Partition p) {
    if(orderMatters)
      return this.numbers.equals(p.numbers);
    else {
      //make deep copy and sort them in order
      ArrayList<Integer> sortedThis = deepCopy(this.numbers);
      Collections.sort(sortedThis);
      
      ArrayList<Integer> sortedP = deepCopy(p.numbers);
      Collections.sort(sortedP);
      
      //now use ArrayList equals()
      return sortedThis.equals(sortedP);
    }
  }

  /**
   * Private helper method that creates a deep copy of an ArrayList of integers.
   * @param list the list to make a deep copy of
   * @return the deep copy of list
   */
  private static ArrayList<Integer> deepCopy(ArrayList<Integer> list){
    ArrayList<Integer> copy = new ArrayList<Integer>(list.size());
    for(Integer num : list)
      copy.add(num);
    return copy;
  }
  
  /**
   * Checks if the given object is equal to this partition.
   * @param obj the Object to check against
   * @return true if obj is a Partition, has the same N, sum, and numbers 
   *    (with respect to orderMatters), false otherwise
   *  
   */
  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Partition) {
      Partition p = (Partition)obj;
      return this.N == p.N && this.sum == p.sum && hasSameNumbers(p);
    }  
    return false;
  }


  /**
   * Compares Paritions based on their length. Partitions that have the same length will then compare based on the contents of their number list.
   * (e.g. if you have the list [2,3,1,1] and [2,1,3,1], the latter would be considered smaller because 2 = 2 and 1 < 3)
   * This is used implicilty to sort ArrayLists of Partitions in the TesterUtility class. This assumes compared Partitions have the same N value.
   * @param o the partition to compare to this one
   * @return 0 if they are equal (exactly the same) and , < 0 if this partition is smaller than o, and
   * > 0 if this partition is greater
   */
  @Override
  public int compareTo(Partition o) {
    if(this.length() != o.length()) 
      return this.length() - o.length();
    
    for(int i=0; i < this.length(); i++) {
      int diff = this.numbers.get(i) - o.numbers.get(i);
      if(diff != 0) 
        return diff;
    }
      return 0;
  }
}
