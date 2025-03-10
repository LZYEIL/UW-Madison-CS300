
public class LinkedNode {
  
  /**
   * The Pod carried by this node
   */
  private Pod data;
  
  /**
   * A reference to the previous node in this linked list
   */
  private LinkedNode prev;
  
  /**
   * A reference to the next node in this linked list
   */
  private LinkedNode next;

  /**
   * Creates a new LinkedNode with the given data, previous and next nodes
   * @param data the Pod to be contained in this node
   * @param prev a reference to the previous node in this list (may be null)
   * @param next a reference to the next node in this list (may be null)
   */
  public LinkedNode(Pod data, LinkedNode prev, LinkedNode next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
  }
  
  /**
   * Creates a new LinkedNode with the given data which is not currently linked
   * to any other nodes
   * @param data the data to be contained in this node
   */
  public LinkedNode(Pod data) {
    this(data, null, null);
  }
  
  /**
   * Accesses the data stored in this node
   * @return the data stored in this node
   */
  public Pod getPod() { 
    return this.data;
  }
  
  // NOTE: there is no "setPod()" method!!
  
  /**
   * Accesses the previous node in the list
   * @return a reference to the previous node in this list (may be null)
   */
  public LinkedNode getPrev() {
    return this.prev; 
  }
  
  /**
   * Updates the previous node for this node
   * @param newPrev the new previous node in this list (may be null)
   */
  public void setPrev(LinkedNode newPrev) {
    this.prev = newPrev;
  }
  
  /**
   * Accesses the next node in the list
   * @return a reference to the nextnode in this list (may be null)
   */
  public LinkedNode getNext() {
    return this.next; 
  }
  
  /**
   * Updates the next node for this node
   * @param newNext the new next node in this list (may be null)
   */
  public void setNext(LinkedNode newNext) {
    this.next = newNext;
  }
}
