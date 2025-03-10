//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 LinkedQueue class
// Course:   CS 300 Spring 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A generic singly-linked queue implementation.
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T>{

  private LinkedNode<T> front;
  private LinkedNode<T> back;
  private int size;
  
  
  
  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   * 
   * @param value  the value to add
   */
  public void enqueue(T value) {
    LinkedNode<T> current = new LinkedNode<T>(value);
    
    if (this.isEmpty()) {
      this.front = current;
      this.back = current;
    }
    else {
      this.back.setNext(current);
      this.back = current;
    }
    
    this.size ++;
  }
  
  
  /**
   * Removes and returns the value added to this queue least recently
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T dequeue() {
    if (this.isEmpty()) {
      return null;
    }
    LinkedNode<T> current = this.front;
    
    if (this.size() == 1) {  
      this.front = null;
      this.back = null;
    } else {  
      this.front = current.getNext();
    }
    
    this.size--;  
    return current.getData();
  }
  
  
  
  
  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   * 
   * @return  the least recently-added value, or null if the queue is empty
   */
  public T peek() {
    if (this.isEmpty()) {
      return null;
    }
    return this.front.getData();
  }
  
  
  
  /**
   * Returns true if this queue contains no elements.
   * 
   * @return  true if the queue contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return (this.front == null && this.back == null);
  }
  
  
  /**
   * Returns the number of elements in the queue.
   * 
   * @return  the number of elements in the queue
   */
  public int size() {
    return this.size;
  }
  
  
  
  /**
   * Returns true if this queue contains the element, false otherwise
   * 
   * @param value  the value to check
   * @return  true if the queue contains the element, false otherwise
   */
  public boolean contains(T value) {
    LinkedNode<T> current = this.front;
    
    while (current != null) {
      if (current.getData().equals(value)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }
  
  
  
  /**
   * Removes all of the elements from the queue. The queue will be empty after this call returns.
   */
  public void clear() {
    this.front = null;
    this.back = null;
    this.size = 0;
  }
  
  
  
  /**
   * Creates a copy of the current contents of this queue in the order they are present here, 
   * in ArrayList form. This method should traverse the queue without removing any elements, 
   * and add the values (not the nodes!) to an ArrayList in the order they appear in the queue, 
   * with the front of the queue at index 0.
   * 
   * @return  an ArrayList representation of the current state of this queue
   */
  public ArrayList<T> getList() {
    LinkedNode<T> current = this.front;
    ArrayList<T> arrList = new ArrayList<T>();
    
    while (current != null) {
      arrList.add(current.getData());
      current = current.getNext();
    }
    return arrList;
  }

  
}
