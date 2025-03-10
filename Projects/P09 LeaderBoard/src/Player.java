//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Player class
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


/**
 * This class models a Player for the CS300 Leaderboard project.
 * 
 * You must complete the compareTo() method in this file (and modify the class header as necessary)
 * and submit the completed file to Gradescope for credit.
 */
public class Player implements Comparable<Player> {

  
  /**
   * Compares this player to another player:
   * - if the players' SCORES differ, the player with the greater score is "larger"
   * - otherwise, if they players' NAMES differ, the player with the alphabetically greater name is 
   *   "larger"
   * - if both their names and scores are identical, the players are considered equal
   * @param other the player to compare with this player
   * @return a negative number if this < other, a positive number if this > other, and 0 if they are
   * equal
   */
  public int compareTo(Player other) {
    //Equal
    if (this.compareToName(other) == 0 && this.getScore() == other.getScore()) {
      return 0;
    }
    
    // Score
    if (this.getScore() > other.getScore()) {
      return 1;
    } else if (this.getScore() < other.getScore()) {
      return -1;
    }
    
    // Name
    return this.compareToName(other);
  }
  
  ////////////////////////////////// The remainder of this class is provided.
  
  /** The player's name */
  private String name;
  
  /** The player's score, set to 1500 by default */
  private int score = 1500;

  /**
   * Creates a new Player with the given name and the default score
   * @param name the player's name
   * @throws IllegalArgumentException if the player's name is null or blank
   */
  public Player(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.name = name;
  }

  /**
   * Creates a new player with the given name and score
   * @param name the player's name
   * @param score the player's score
   */
  public Player(String name, Integer score) {
    this(name);
    this.score = score;
  }

  /**
   * Accesses this player's name
   * @return the player's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Accesses this player's score
   * @return the player's score
   */
  public double getScore() {
    return this.score;
  }

  /**
   * Updates the player's score
   * @param score the new score
   */
  public void setScore(Integer score) {
    this.score = score;
  }

  /**
   * Provides a String representation of this player as [name]: [score]
   * @return a String representation of this player
   */
  @Override
  public String toString() {
    return this.name + ": " + this.score;
  }

  /**
   * A custom comparison method that compares players by name only
   * @param other the player to compare with this player
   * @return a negative number if this player's name is alphabetically before the other player's, a
   * positive number if it is after, and 0 if they are the same
   */
  public int compareToName(Player other) {
    return this.name.compareTo(other.name);
  }

  /**
   * Determines whether this object is equal to the provided object using the results of the
   * compareTo() method defined above.
   * @param o the object to compare with this player
   * @return true if the argument was a Player with the same score and name; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Player record) {
      return 0 == this.compareTo(record);
    } else {
      return false;
    }
  }
}
