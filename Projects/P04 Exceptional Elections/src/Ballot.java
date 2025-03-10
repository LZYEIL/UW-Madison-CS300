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


import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * An instantiable class representing a ballot. The class stores a list of Elections, 
 * and each Ballot instance is allowed one vote per Election. 
 */
public class Ballot {
  
  private static ArrayList<Election> elections = new ArrayList<Election>();
  private static boolean ballotsCreated = false;
  private boolean[] hasVoted;
  
  
  /**
   * Initializes a new ballot which corresponds to the current number of elections present in the 
   * elections ArrayList. This new Ballot has not yet voted in any of the elections.
   */
  public Ballot() {
    if (elections.isEmpty()) {
      throw new IllegalStateException("Invalid");
    }

    // check whether ballots are created
    if (!ballotsCreated) {
      ballotsCreated = true;
    }
    this.hasVoted = new boolean[elections.size()];
  }
  
  
  
  /**
   * Adds an election to the end of the elections ArrayList, as long as this election 
   * (or one equal to it) is not yet present.
   * 
   * @param election   the election to add to the list of elections
   */
  public static void addElection(Election election) {
    if (ballotsCreated) {
      throw new IllegalStateException("Cannot add elections after ballots have been created.");
    }

    if (elections.contains(election)) {
      throw new IllegalArgumentException("Election already present in the list.");
    }
    elections.add(election);
  }
  
  
  
  /**
   * Adds 1 vote to the provided Candidate running in the election for the given seatName, 
   * if this Ballot has not yet voted in that election. Marks the election as having been voted 
   * in if the vote is successful.
   * 
   * @param seatName   the name of the seat for the election to vote in
   * @param candidate  the Candidate to vote for
   */
//  public void vote(String seatName, Candidate candidate) {
//    int count = 0;
//    
//    for (int i = 0; i < Ballot.elections.size(); i++) {
//      if (seatName.equals(Ballot.elections.get(i).SEAT_NAME)) {
//        count++;
//        if (this.hasVoted[i]) {
//          throw new IllegalStateException("Has Voted");
//        }
//        else {
//          Ballot.elections.get(i).vote(candidate);
//          this.hasVoted[i] = true;
//          break;
//        }
//      }
//    }
//    
//    if (count == 0) {
//      throw new NoSuchElementException("No SEAT NAME");
//    }
//    
//  }
  
  public void vote(String seatName, Candidate candidate) {
    if (this.hasVoted(seatName)) {
        throw new IllegalStateException("Has Voted");
    }

    for (int i = 0; i < Ballot.elections.size(); i++) {
        if (seatName.equals(Ballot.elections.get(i).SEAT_NAME)) {
            Ballot.elections.get(i).vote(candidate);
            this.hasVoted[i] = true;
            return;
        }
    }

    throw new NoSuchElementException("No SEAT NAME");
  }



  
  /**
   * Checks whether this ballot has already voted in an election corresponding to the 
   * given seatName
   * 
   * @param seatName  the name of the seat for the election to vote in
   * @return  true if this ballot has already cast a vote for the specified election, 
   * false otherwise
   */
  public boolean hasVoted(String seatName) {

    for (int i = 0; i < Ballot.elections.size(); i++) {
      if (seatName.equals(Ballot.elections.get(i).SEAT_NAME)) {
        return this.hasVoted[i];
      }
    }

    throw new NoSuchElementException("No SEAT NAME");
  }
  
  
  
  /**
   * Creates and returns a String representation of this ballot's voter state as follows: 
   * in order, lists the seatName of the election from the elections ArrayList and whether 
   * this Ballot has yet cast a vote in that election
   * 
   * @return a string representation of this ballot as described in this comment, 
   * which does NOT end with a newline
   */
  @Override
  public String toString() {
    String result = "";
    
    for (int i = 0; i < Ballot.elections.size(); i++) {
      result += Ballot.elections.get(i).SEAT_NAME + ": " + this.hasVoted[i] + "\n";
    }
    
    result = result.substring(0, result.length() - 1);
    return result;
  }
  
  
  /**
   * empties the elections arraylist and resets ballotsCreated, for testing purposes only
   */
  public static void clearElections() {
     Ballot.elections.clear();
     Ballot.ballotsCreated = false;
  }
  
  
  
  
  
  
  
}