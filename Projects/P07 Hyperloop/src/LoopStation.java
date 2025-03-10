//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hyperloop LoopStation
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

import java.util.NoSuchElementException;

/**
 * This class models a LoopStation, which manages three tracks
 */
public class LoopStation {
  
  protected Track waitingFirst;
  protected Track waitingEconomy;
  protected Track launched;
  
  
  /**
   * Creates a new LoopStation with empty tracks
   */
  public LoopStation() {
    this.waitingFirst = new Track();
    this.waitingEconomy = new Track();
    this.launched = new Track();
  }
  
  
  
  /**
   * Creates a new Pod of the appropriate class and loads it onto the correct waiting track. 
   * This method also returns a reference to this newly-created Pod so that passengers may board
   * 
   * @param capacity   the capacity of the new Pod to create
   * @param isFirstClass   whether the new Pod is a first class Pod
   * @return  a reference to the newly-created Pod
   */
  public Pod createPod(int capacity, boolean isFirstClass) {
    int podClass = (isFirstClass ? 0 : 1);
    Pod podObject = new Pod(capacity, podClass);
    
    if (isFirstClass) {
      this.waitingFirst.add(podObject); 
    } else {
      this.waitingEconomy.add(podObject); 
    }
    
    return podObject;
  }
  
  
  
  
  /**
   * Launches the highest-priority, least-recently-created Pod that is currently waiting:
   * FIRST class Pods are launched first Then ECONOMY class Pods are launched
   * 
   * @throws NoSuchElementException   if no Pods are waiting to launch
   */
  public void launchPod() {
    if (this.waitingFirst.isEmpty() && this.waitingEconomy.isEmpty()) {
      throw new NoSuchElementException("No Pods are waiting to launch");
    }
    Pod podToLaunch;

    if (!waitingFirst.isEmpty()) {
      podToLaunch = waitingFirst.remove(waitingFirst.size() - 1);
    } else {
      podToLaunch = waitingEconomy.remove(0);
    }
    this.launched.add(podToLaunch);
  }
  
  
  
  /**
   * Finds and removes any malfunctioning Pods from the launched track
   * 
   * @return  the total number of pods which were removed
   */
  public int clearMalfunctioning() {
    int count = 0;
    int index = this.launched.findFirstNonFunctional();
    
    while (index != -1) {
      this.launched.remove(index);
      count ++;
      index = this.launched.findFirstNonFunctional();
    }
    
    return count;
  }
  
  
  /**
   * Reports the total number of first and economy class Pods which are waiting to be launched
   * 
   * @return  the total number of unlaunched pods at this LoopStation
   */
  public int getNumWaiting() {
    return this.waitingEconomy.size() + this.waitingFirst.size();
  }
  
  
  
  /**
   * Reports the total number of Pods, functional or non-functional, 
   * which are currently running on the launched track
   * 
   * @return  the total number of Pods on the launched track
   */
  public int getNumLaunched() {
    return this.launched.size();
  }
  
  
  
  /**
   * Reports the total number of passengers in functional Pods across all tracks, 
   * waiting and launched
   * 
   * @return  the total number of passengers in functional Pods currently 
   * being served by this LoopStation
   */
  public int getNumPassengers() {
    int numPassengers = 0;
    
    LinkedNode firstCurrent = this.waitingFirst.head;
    while (firstCurrent != null) {
      try {
        if (firstCurrent.getPod().isFunctional()) {
          numPassengers += firstCurrent.getPod().getNumPassengers();
        }
      }
      catch (MalfunctioningPodException e) {
        //DO NOTHING HERE
      }
      firstCurrent = firstCurrent.getNext();
    }
    
    LinkedNode economyCurrent = this.waitingEconomy.head;
    while (economyCurrent != null) {
      try {
        if (economyCurrent.getPod().isFunctional()) {
          numPassengers += economyCurrent.getPod().getNumPassengers();
        }
      }
      catch (MalfunctioningPodException e) {
        //DO NOTHING HERE
      }
      economyCurrent = economyCurrent.getNext();
    }
    
    //Here I do not need the malFunctionNum, I just use the method
    //for clearing all NON-FUNCTIONAL pods in Launched Track
    int malFunctionNum = this.clearMalfunctioning();
    LinkedNode launchedCurrent = this.launched.head;
    
    while (launchedCurrent != null) {
      try {
        numPassengers += launchedCurrent.getPod().getNumPassengers();
      }
      catch (MalfunctioningPodException e) {
        //DO NOTHING HERE
      }
      launchedCurrent = launchedCurrent.getNext(); 
    }
    
    return numPassengers;
  }
  

  
}
