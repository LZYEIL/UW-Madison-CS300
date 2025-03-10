// DO NOT SUBMIT THIS FILE TO GRADESCOPE

/**
 * This interface represents the list abstract data type
 * 
 * @param <T> type of elements stored in this list
 */
public interface ListADT<T> {

  /**
   * Checks whether this list is empty
   * 
   * @return true if this list is empty or false otherwise
   */
  public boolean isEmpty();

  /**
   * Gets the total number of elements stored in this list
   * 
   * @return the size of this list
   */
  public int size();

  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  public void clear();

  /**
   * Adds newElement to this list
   * 
   * @param newElement element to be added to this list
   * @throws NullPointerException if newElement is null
   */
  public void add(T newElement);

  /**
   * Returns the element at the specified position in this list.
   * 
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &ge; size())
   */
  public T get(int index);

  /**
   * Returns true if this list contains the specified element toFind. More formally, returns true if
   * and only if this list contains at least one element e such that toFind.equals(e) == true.
   * 
   * @param toFind element to find
   * @return true if this list contains at least one match with toFind
   */
  public boolean contains(T toFind);

  /**
   * Removes the element at the specified position in this list.
   * 
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &ge; size())
   */
  public T remove(int index);


}
