//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 LeaderboardIterator class
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


import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The Iterator class for our BST (Leaderboard)
 */
public class LeaderboardIterator implements Iterator<Player> {

  private Leaderboard lb;
  private int nodeRemain;
  private Player currentPlayer;
  
  
  /**
   * Constructor of LeaderboardIterator
   * 
   * @param lb The Leaderboard to iterate through
   */
  public LeaderboardIterator(Leaderboard lb) {
    this.lb = lb;
    this.currentPlayer = null;
    this.nodeRemain = lb.count();
  }
  
  
  
  /**
   * Checks if there are more players to iterate over.
   *
   * @return true if there are more players, false otherwise
   */
  @Override
  public boolean hasNext() {
    return this.nodeRemain > 0;
  }
  

  /**
   * Returns the player(one by one) in the leaderboard.
   *
   * @return the next player
   * @throws NoSuchElementException if there are no more players
   */
  @Override
  public Player next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException("Wrong!");
    }
    
    //First call should enter this
    if (currentPlayer == null) {
      currentPlayer = lb.getMinScore();
    } 
    else {
      currentPlayer = lb.next(currentPlayer);
    }

    nodeRemain--; 
    return currentPlayer;
  }
  

}
