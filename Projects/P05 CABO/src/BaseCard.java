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

import java.io.File;

/**
 * The BaseCard class provides the foundation for a playing card in the game. 
 * Each card has a rank, suit, and face-up status. It also includes methods for rendering the card 
 * in a Processing environment.
 */
public class BaseCard {
  
  protected int rank;
  protected String suit;
  protected boolean faceUp;
  private int x;
  private int y;
  private final int WIDTH = 50;
  private final int HEIGHT = 70;
  
  protected static processing.core.PApplet processing;
  private processing.core.PImage cardImage;
  private static processing.core.PImage cardBack;
  
  
  /**
   * Constructs a new BaseCard with the specified rank and suit. The card is initialized to be 
   * face down by default. You may assume that the provided rank and suit are valid. This method 
   * should also initialize the cardImage, and initialize cardBack if that has not yet been done 
   * by any other constructor call.
   * 
   * @param rank   the rank of the card (e.g., 1 for Ace, 13 for King)
   * @param suit   the suit of the card (e.g., "Hearts", "Diamonds")
   */
  public BaseCard(int rank, String suit) {
    if (BaseCard.processing == null) {
      throw new IllegalStateException("Processing not set");
    }
    
    this.rank = rank;
    this.suit = suit;
    this.faceUp = false;
    
    this.cardImage = BaseCard.processing.loadImage("images"+ File.separator + this.rank + "_of_" +
    suit.toLowerCase() + ".png");
    
    if (BaseCard.cardBack == null) {
      BaseCard.cardBack = BaseCard.processing.loadImage("images"+ File.separator + "back.png");
    }
  }
  
  

  
  /**
   * Sets the Processing environment to be used for drawing and interacting with cards. 
   * This method must be called before creating any BaseCard objects.
   * 
   * @param processing  the Processing PApplet environment.
   */
  public static void setProcessing(processing.core.PApplet processing) {
    BaseCard.processing = processing;
  }
  
  
  /**
   * Returns the rank of the card directly, or -1 if the card is the King of Diamonds
   * 
   * @return the rank of the card, or -1 for the King of Diamonds
   */
  public int getRank() {
    if (this.suit.equals("Diamonds") && this.rank == 13) {
      return -1;
    }
    return this.rank;
  }
  
  
  /**
   * Sets the face-up status of the card.
   * 
   * @param faceUp  faceUp - if true, set the card face-up; if false, set it face-down.
   */
  public void setFaceUp(boolean faceUp) {
    this.faceUp = faceUp;
  }
  
  
  
  /**
   * Returns a string representation of the card, showing its suit and rank.
   * 
   * @return  a string in the format "Suit Rank" (e.g., "Hearts 10").
   */
  @Override
  public String toString() {
    String result = this.suit + " " + Integer.toString(this.rank);
    return result;
  }
  
  
  
  
  /**
   * Draws the card on the PApplet at the specified position.
   * 
   * @param xPosition  the x-coordinate to draw the card.
   * @param yPosition  the y-coordinate to draw the card.
   */
  public void draw(int xPosition, int yPosition) {
    this.x = xPosition;
    this.y = yPosition;
    
    //A rectangle to sit on
    processing.fill(255);
    processing.rect(xPosition, yPosition, WIDTH, HEIGHT);
    
    if (faceUp) {
      BaseCard.processing.image(this.cardImage, xPosition, yPosition, WIDTH, HEIGHT);
    }
    else {
      BaseCard.processing.image(BaseCard.cardBack, xPosition, yPosition, WIDTH, HEIGHT);
    }
  }
  
  
  
  /**
   * Checks if the mouse is currently over this card. Use PApplet's mouseX and mouseY fields to 
   * determine where the mouse is; the (x,y) coordinates of this card's upper left corner were 
   * set when it was last drawn.
   * 
   * @return   true if the card is under the mouse's current position, false otherwise.
   */
  public boolean isMouseOver() {
    boolean xBounds = BaseCard.processing.mouseX >= x && BaseCard.processing.mouseX <= x + WIDTH;
    boolean yBounds = BaseCard.processing.mouseY >= y && BaseCard.processing.mouseY <= y + HEIGHT;
    
    return xBounds && yBounds;
  }
  

}
