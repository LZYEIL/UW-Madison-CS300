/**
 * An abstract data type describing common stack functionality. A stack observes a last-in-first-out
 * protocol, where the only accessible value is the most-recently added value.
 * 
 * @param <T> the type of data contained in this stack
 */
public interface StackADT<T> {

  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * 
   * @param value the value to add
   */
  public void push(T value);

  /**
   * Removes and returns the value added to this stack most recently
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T pop();

  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T peek();

  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty();

  /**
   * Returns true if this stack contains the element, false otherwise
   * 
   * @param value the value to check
   * @return true if the stack contains the element, false otherwise
   */
  public boolean contains(T value);
}
