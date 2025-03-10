//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hyperloop Track
// Course:   CS 300 Spring 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Reginald Yuan
// Partner Email:   yuan253@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////



/**
 * This class models Track objects as a doubly-linked list for the CS300 Hyperloop project.
 */
public class Track implements ListADT<Pod> {

  protected LinkedNode head;
  protected LinkedNode tail;
  private int size;

  /**
   * Adds a passenger to the first available seat in a Pod which matches their class designation.
   *
   * @param name         the name of the passenger to add
   * @param isFirstClass whether this passenger is first class
   * @return true if they were successfully added to an available seat of their corresponding class,
   * false if there were no seats or Pods available for their class
   */
  public boolean addPassenger(String name, boolean isFirstClass) {
    if (head == null || head.getPod() == null) {
      // No pods available
      return false;
    }

    LinkedNode current = head;

    while (current != null) {
      try {
        Pod pod = current.getPod();

          int podClass = pod.getPodClass();
          boolean isClassMatch = (isFirstClass && podClass == Pod.FIRST) ||
              (!isFirstClass && podClass == Pod.ECONOMY);

          if (isClassMatch && !pod.isFull()) {
            pod.addPassenger(name);
            return true;
          }

      } catch (MalfunctioningPodException e) {
        System.out.println("POD NOT WORKING");
      } catch (IllegalStateException e) {
        System.out.println("Pod is full.");
      }

      current = current.getNext();
    }

    return false;
  }



  /**
   * Searches all Pods in the track to find the given passenger
   *
   * @param name the name of the passenger to find
   * @return the index of the Pod this passenger was located in, or -1 if they were not found (or
   * the Track is currently empty)
   */
  public int findPassenger(String name) {
    if (head == null || head.getPod() == null) {
      // Track is empty
      return -1;
    }

    int index = 0;
    LinkedNode current = head;

    while (current != null) {
      Pod pod = current.getPod();
      try {
        if (pod.containsPassenger(name)) {
          return index;
        }
      } catch (MalfunctioningPodException e) {
        System.out.println("POD NOT WORKING");
      }

      current = current.getNext();
      index++;
    }

    return -1;
  }



  /**
   * Finds the index of the first non-functional Pod on the track. If all Pods are functioning,
   * returns -1
   *
   * @return the lowest index of a non-functional Pod on the track, or -1 if all Pods are currently
   * functioning (or the Track is currently empty)
   */
  public int findFirstNonFunctional() {
    int index = 0;
    LinkedNode current = head;

    while (current != null) {
      if (!current.getPod().isFunctional()) {
        return index;
      }

      current = current.getNext();
      index++;
    }

    return -1;
  }



  /**
   * Reports whether the track is currently empty
   *
   * @return true if the track is currently empty, false otherwise
   */
  public boolean isEmpty() {
    return this.head == null && this.size == 0 && this.tail == null;
  }



  /**
   * Reports the current number of Pods currently on this track. This number includes both
   * functional and non-functional Pods.
   *
   * @return the number of Pods on this track
   */
  public int size() {
    return this.size;
  }



  /**
   * Removes ALL Pods from this track
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }



  /**
   * Adds a Pod to the track in the correct location. FIRST class Pods should be added to the front
   * of the list; ECONOMY class Pods should be added to the back of the list. If the Pod is not
   * functional, do NOT add it to the track, but also do NOT allow any exception to be thrown.
   * Attempting to add a non-functional Pod should simply not cause the list to change.
   *
   * @param newElement the Pod to add to this track
   */
  public void add(Pod newElement) {

    if (!newElement.isFunctional()) {
      return;
    }

    try {
      LinkedNode newNode = new LinkedNode(newElement);
      if (newElement.getPodClass() == Pod.FIRST) {
        if (this.isEmpty()) {
          this.head = newNode;
          this.tail = newNode;
        } else {
          newNode.setNext(this.head);
          this.head.setPrev(newNode);
          head = newNode;
        }
      } else {
        if (this.isEmpty()) {
          this.head = newNode;
          this.tail = newNode;
        } else {
          newNode.setPrev(this.tail);
          this.tail.setNext(newNode);
          this.tail = newNode;
        }
      }
      this.size++;

    } catch (MalfunctioningPodException e) {
      //DO NOTHING HERE
    }
  }



  /**
   * Accesses the Pod at a given index
   *
   * @param index the index of the Pod to access
   * @return a reference to the Pod at a given index in the track
   * @throws IndexOutOfBoundsException - if the given index is invalid
   */
  public Pod get(int index) {
    LinkedNode current = this.head;
    int currentIndex = 0;

    while (current != null) {
      if (currentIndex == index) {
        return current.getPod();
      }

      current = current.getNext();
      currentIndex++;
    }
    throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
  }



  /**
   * Determines whether a particular Pod is contained in the track
   *
   * @param toFind the Pod to search for in the track
   * @return true if the Pod is contained in the track, false otherwise
   */
  public boolean contains(Pod toFind) {
    LinkedNode current = this.head;

    while (current != null) {
      if (current.getPod().equals(toFind)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }



  /**
   * Removes a Pod at a given index from the track
   *
   * @param index the index of the Pod to remove
   * @return a reference to the Pod removed from the track
   * @throws IndexOutOfBoundsException - if the given index is invalid
   */
  public Pod remove(int index) {
    if (index < 0 || index >= this.size()) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    LinkedNode current = this.head;
    Pod podToRemove;

    if (this.size() == 1) {
      podToRemove = current.getPod();
      this.clear();
      return podToRemove;
    }

    int currentIndex = 0;
    while (currentIndex < index) {
      current = current.getNext();
      currentIndex++;
    }
    podToRemove = current.getPod();


    if (currentIndex == 0) {
      this.head = current.getNext();
      this.head.setPrev(null);
    } else if (currentIndex == this.size() - 1) {
      this.tail = current.getPrev();
      this.tail.setNext(null);
    } else {
      current.getPrev().setNext(current.getNext());
      current.getNext().setPrev(current.getPrev());
    }

    this.size--;
    return podToRemove;
  }



  /**
   * Returns a String representation of the entire contents of the track (OUTPUT NOT GRADED). This
   * method traverses the entire track and includes a String representation of each Pod, which you
   * may wish to use for testing purposes.
   *
   * @return a String representation of all Pods currently on the track
   */
  @Override
  public String toString() {
    String result = "Track: [";
    LinkedNode current = this.head;

    while (current != null) {
      result = result + current.getPod().toString() + "\n";
      current = current.getNext();
    }
    result += "]";
    return result;
  }



}
