//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 LinkedStack class
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
 * A generic singly-linked stack implementation.
 * @param <T>
 */
public class LinkedStack<T> implements StackADT<T>{
  
  private LinkedNode<T> top;
  
  
  
  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * 
   * @param value  the value to add
   */
  public void push(T value) {
    LinkedNode<T> newNode = new LinkedNode<T>(value);
    
    if (this.isEmpty()) {
      top = newNode;
    }
    else {
      LinkedNode<T> prevTop = this.top;
      this.top = newNode;
      newNode.setNext(prevTop); 
    }
  }
  
  
  
  /**
   * Removes and returns the value added to this stack most recently
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T pop() {
    if (this.isEmpty()) {
      return null;
    }
    else {
      LinkedNode<T> current = this.top;
      this.top = current.getNext();
      return current.getData();
    }
  }
  
  
  
  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * 
   * @return  the most recently-added value, or null if the stack is empty
   */
  public T peek() {
    if (this.isEmpty()) {
      return null;
    }
    else {
      return this.top.getData();
    }
  }
  
  
  
  /**
   * Returns true if this stack contains no elements.
   * 
   * @return  true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return this.top == null;
  }
  
  
  
  /**
   * Returns true if this stack contains the element, false otherwise
   * 
   * @param value  the value to check
   * @return  true if the stack contains the element, false otherwise
   */
  public boolean contains(T value) {
    LinkedNode<T> current = this.top;
    
    while (current != null) {
      if (current.getData().equals(value)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }
  
  
  
  /**
   * Creates a copy of the current contents of this stack in the order they are present here, 
   * in ArrayList form. This method should traverse the stack without removing any elements, 
   * and add the values (not the nodes!) to an ArrayList in the order they appear in the stack, 
   * with the top of the stack at index 0.
   * 
   * @return  an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> arrList = new ArrayList<T>();
    LinkedNode<T> current = this.top;
    
    while (current != null) {
      arrList.add(current.getData());
      current = current.getNext();
    }
    
    return arrList;
  }
  


}
