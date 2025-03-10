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

/**
 * An instantiable class representing a candidate in an election. 
 */
public class Candidate {
  
  private String name;
  private int numVotes;
  private String party;

  
  /**
   * Creates a new Candidate object with the given name and party. This candidate has 
   * received 0 votes.
   * 
   * @param name   the candidate's name, cannot be null or blank
   * @param party  the candidate's party, cannot be null or blank
   */
  public Candidate(String name, String party) {
    if (name == null || name.trim().isEmpty() || party == null || party.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid inputs");
    }
    this.numVotes = 0;
    this.name = name;
    this.party = party;
  }


  /**
   * Accessor for the candidate's current number of votes
   * 
   * @return the number of votes this candidate has received
   */
  public int getNumVotes() {
    return this.numVotes;
  }
  
  
  /**
   * Adds one (1) vote to this candidate's total
   */
  public void addVote() {
    this.numVotes += 1;
  }
  
  /**
   * Creates and returns a String representation of this candidate in the form "name (party): 
   * numVotes
   * 
   * @return  a String representation of the candidate as described in this comment, 
   * which does NOT end with a newline
   */
  @Override
  public String toString() {
    return this.name + " (" + this.party + "): " + this.getNumVotes();
  }
  
  
  
  /**
   * Determines whether this candidate and anObject are copies (deep or shallow) of each other. 
   * If anObject is not a Candidate object at all, they are not equal. If it IS a Candidate, 
   * then they are equal if and only if this candidate and anObject have exactly the same name, 
   * party, and number of votes.
   * 
   * @param anObject  the object to compare this Candidate against
   * @return  true if the given object represents a Candidate equivalent to this candidate, 
   * false otherwise.
   */
  @Override
  public boolean equals(Object anObject) {
    if (anObject instanceof Candidate) {
      Candidate c = (Candidate) anObject;
      
      return this.name.equals(c.name) && this.party.equals(c.party) && 
          this.getNumVotes() == c.getNumVotes();
 
    }
    return false;
  }
  

}