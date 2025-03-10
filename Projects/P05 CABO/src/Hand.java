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

import java.util.ArrayList;

/**
 * This class models a mini-Deck that each player holds - their "hand" of cards.
 */
public class Hand extends Deck {
  
  private final int HAND_SIZE = 4;
  
  
  /**
   * Creates a new empty deck for this hand
   */
  public Hand() {
    super(new ArrayList<BaseCard>());
    if (BaseCard.processing == null) {
      throw new IllegalStateException("Processing not set");
    }
  }
  
  
  /**
   * Overrides Deck's addCard() method to prevent this player being dealt more than 
   * HAND_SIZE cards
   * 
   * @param card  the card to add to this hand
   */
  @Override
  public void addCard(BaseCard card) {
    if (this.cardList.size() >= HAND_SIZE) {
      throw new IllegalStateException("Full.");
    }
    
    super.addCard(card);  
  }
  
  
  
  /**
   * Replaces the card at the given index (assumed to be between 0 and (HAND_SIZE-1)) with the 
   * provided card, and returns the card that was previously at that index.
   * 
   * @param newCard  the card to swap into this hand
   * @param index   the index to place the new card at
   * @return   the card that was previously at that index
   */
  public BaseCard swap(BaseCard newCard, int index) {
    BaseCard res = this.cardList.get(index);
    this.cardList.set(index, newCard);
    return res;
  }
  
  
  
  /**
   * Switches a card in this hand with a card in the other hand.
   * 
   * @param myIndex   the index of the card in this hand to switch
   * @param otherHand  the other hand to switch cards with
   * @param otherIndex  the index of the card in the other hand to switch
   */
  public void switchCards(int myIndex, Hand otherHand, int otherIndex) {
    BaseCard myCard = this.cardList.get(myIndex);
    BaseCard thatCard = otherHand.cardList.get(otherIndex);
    
    otherHand.cardList.set(otherIndex, myCard);
    this.cardList.set(myIndex, thatCard);
  }
  
  
  
  /**
   * Changes the face-up value of the card at the given index to the provided value
   * 
   * @param index  the index of the card to change
   * @param faceUp  true if this card should be face-up, false if it should be face-down
   */
  public void setFaceUp(int index, boolean faceUp) {
    this.cardList.get(index).setFaceUp(faceUp);
  }
  
  
  
  /**
   * Draws the entire hand at the given y-coordinate. 
   * To calculate the x-coordinate of each card, use (50 + 60*index).
   * 
   * @param y the y-coordinate of the upper-left corner of all cards in this hand
   */
  public void draw(int y) {
    for (int i = 0; i < this.HAND_SIZE; i++) {
      this.cardList.get(i).draw(50 + 60 * i, y);
    }
  }
  
  
  
  /**
   * Checks if the mouse is currently over any of the cards in this hand, and returns the index 
   * of any card which the mouse is over, or -1 if the mouse is not currently 
   * over any card in this hand.
   * 
   * @return  the index of a card in this hand which the mouse is over, 
   * or -1 if the mouse is not over any cards in this hand
   */
  public int indexOfMouseOver() {
    for (int i = 0; i < this.HAND_SIZE; i++) {
      if (this.cardList.get(i).isMouseOver()) {
        return i;
      }
    }
    return -1;
  }
  
  
  
  /**
   * Accesses the rank of a card at a given index
   * 
   * @param index  the index of the card to access
   * @return  the rank of the card at that index
   */
  public int getRankAtIndex(int index) {
    return this.cardList.get(index).getRank();
  }
  
  
  /**
   * Determines the total value of the cards in this hand, as a sum of 
   * the ranks of each of the cards
   * 
   * @return  the total value of this Player's hand
   */
  public int calcHand() {
    int totalValue = 0;

    for (int i = 0; i < this.HAND_SIZE; i++) {
      totalValue += this.cardList.get(i).getRank();
    }
    return totalValue;
  }
  
  
}
