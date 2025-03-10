//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Election
// Course:   CS 300 Fall 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Reginald Yuan (a.k.a Yijing Yuan)
// Partner Email:   yuan253@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////


import java.util.NoSuchElementException;

/**
 * An instantiable class representing an election, which maintains a list of Candidates 
 * running in the election and some information about that election.
 */
public class Election {
  
  private Candidate[] candidates;
  private int numCandidates;
  public final String SEAT_NAME;
  
  
  /**
   * Initializes the oversize array for this election's candidates and sets the name of the 
   * position for which this election is being held.
   * 
   * @param seatName       the name of this election's position
   * @param maxCandidates  the maximum number of candidates permitted to run in this election
   */
  public Election(String seatName, int maxCandidates) {
    if (maxCandidates <= 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.candidates = new Candidate[maxCandidates];
    this.numCandidates = 0;
    this.SEAT_NAME = seatName;
  }
  
  
  
  /**
   * Accessor method for the current number of candidates in this Election
   * 
   * @return  the number of candidates currently running in this election
   */
  public int getNumCandidates() {
    return this.numCandidates;
  }
  
  
  /**
   * Accessor method for the maximum number of candidates in this Election. 
   * 
   * @return  the maximum number of candidates permitted to run in this election
   */
  public int capacity() {
    return this.candidates.length;
  }
  
  
  /**
   * Adds a candidate to the END of this election's list. 
   * 
   * @param candidate  the Candidate to add to this election
   */
  public void addCandidate(Candidate candidate) {
    boolean roomFlag = (this.capacity() - this.getNumCandidates()) <= 0;
   
    if (roomFlag) {
      throw new IllegalArgumentException("Candidate is already full");
    }
    
    for (int i = 0; i < this.capacity(); i++) {
      if (candidates[i] != null && candidates[i].equals(candidate)) {
        throw new IllegalArgumentException("Invalid method call");
      }
      if (candidates[i] == null) {
        candidates[i] = candidate;
        this.numCandidates += 1;
        break;
      }
    }
  }
  
  
  
  /**
   * Removes the candidate matching the provided candidate exactly from the election's list 
   * of candidates
   * 
   * @param candidate  the candidate to remove
   */
  public void removeCandidate(Candidate candidate) {
    if (this.getNumCandidates() == 0) {
        throw new IllegalStateException("Empty List!");
    }

    int index = -1;
    for (int i = 0; i < this.getNumCandidates(); i++) {
        if (candidates[i].equals(candidate)) {
            index = i;
            break;  
        }
    }

    if (index == -1) {
        throw new NoSuchElementException("Candidate not found");
    }

    for (int i = index; i < this.getNumCandidates() - 1; i++) {
        candidates[i] = candidates[i + 1];  
    }
    candidates[this.getNumCandidates() - 1] = null;
    this.numCandidates--;
  }
  
  
  
  
  /**
   * Returns a reference to the Candidate receiving more than 50% of the votes in this election
   * 
   * @return  the Candidate with >50% of the votes across this election's candidates
   */
  public Candidate findWinner() {
    double sum = 0.0;
    
    if (this.numCandidates == 0) {
      throw new IllegalStateException("Illegal action!");
    }

    for (int i = 0; i < this.getNumCandidates(); i++) {
      sum += candidates[i].getNumVotes();
    }
    sum = 0.5 * sum;
    
    for (int i = 0; i < this.getNumCandidates(); i++) {
      if (candidates[i].getNumVotes() > sum) {
        return candidates[i];
      }
    }
    throw new NoSuchElementException("No one satisfy");
  }

  
  
  
  /**
   * Increases the vote count of the given candidate by one
   * 
   * @param candidate   the candidate to vote for
   */
  public void vote(Candidate candidate) {
    int count = 0;
    
    for (int i = 0; i < this.getNumCandidates(); i++) {
      if (candidates[i].equals(candidate)) {
        candidates[i].addVote();
        count++;
        break;
      }
    }
    
    if (count == 0) {
      throw new NoSuchElementException("No element");
    }
  }
  
  
  /**
   * Creates and returns a String representation of this Election object, with each Candidate's 
   * string representation on a separate line. The first line of the output String contains the 
   * name of the seat this election is for. 
   * 
   * @return the String representation of the current state of this Election, which does 
   * NOT end with a newline
   */
  @Override
  public String toString() {
    String result = this.SEAT_NAME + "\n";
    
    for (int i = 0; i < this.getNumCandidates(); i++) {
      result += candidates[i].toString() + "\n";
    }
    
    return result.substring(0, result.length() - 1);
  }
  
  
  
  /**
   * Determines whether a provided object is equivalent to this Election. 
   * If anObject is not an Election at all, they are not equal. If it IS an Election, 
   * they are equivalent if and only if their seat names match, ignoring capitalization. 
   * 
   * @param anObject   the object to compare this Election against
   * @return true if the given object represents a Election equivalent to this election, 
   * false otherwise.
   */
  @Override
  public boolean equals(Object anObject) {
    if (anObject instanceof Election) {
      Election elec = (Election) anObject;
      return this.SEAT_NAME.equalsIgnoreCase(elec.SEAT_NAME);
    }
    else {
      return false;
    }
  }
  
  

}