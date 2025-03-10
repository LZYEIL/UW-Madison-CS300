//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2024
//
// Author:   Reginald Yuan
// Email:    yuan253@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Zhiyuan Li
// Partner Email:   zli2562@wisc.edu
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
 * The ActionCard class represents a card that has an associated action. 
 * It extends the BaseCard class and introduces action types that affect gameplay. 
 * The specific actions are as follows: - 7 and 8: Peek at a card in the player's own hand. 
 * - 9 and 10: Spy on a card in another player's hand. - Jack and Queen: Switch one card with 
 * another card from a different player.
 */
public class ActionCard extends BaseCard {
  
  private String actionType;
  
  
  /**
   * Constructs an ActionCard with the specified rank, suit, and action type. 
   * 
   * @param rank  the rank of the card (e.g., 1 for Ace, 13 for King).
   * @param suit  the suit of the card (e.g., "Hearts", "Diamonds").
   * @param actionType  the type of action associated with this card: "peek", "spy", or "switch"
   */
  public ActionCard(int rank, String suit, String actionType) {
    super(rank, suit);
    this.actionType = actionType;
  }
  
  
  
  /**
   * Gets the type of action associated with this card.
   * 
   * @return  the action type as a String: "peek", "spy", or "switch".
   */
  public String getActionType() {
    return this.actionType;
  }

}
