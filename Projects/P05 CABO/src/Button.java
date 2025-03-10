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
// Online Sources:  pushStyle:https://processing.org/reference/pushStyle_.html
//
///////////////////////////////////////////////////////////////////////////////


import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

  private boolean active;
  private int height;
  private String label;
  protected static processing.core.PApplet processing;
  private int width;
  private int x;
  private int y;

  /**
   * Constructs a Button with the specified label and position, which is inactive by default. Throws
   * an IllegalStateException if the Processing environment has not been initialized.
   *
   * @param label  the text label displayed on the button.
   * @param x      the x-coordinate of the top-left corner of the button.
   * @param y      the y-coordinate of the top-left corner of the button.
   * @param width  the width of the button.
   * @param height the height of the button.
   */
  public Button(String label, int x, int y, int width, int height) {
    if (Button.processing == null) {
      throw new IllegalStateException("Not set the environment!");
    }

    this.label = label;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.active = false;
  }

  /**
   * Sets the Processing environment to be used by the Button class. This must be called before
   * creating any buttons.
   *
   * @param processing the Processing environment to be used for drawing and interaction.
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Button.processing = processing;
  }

  /**
   * Returns the label of this button
   *
   * @return this button's current label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Changes the label of this button
   *
   * @param label the new label for this button
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Returns whether the button is currently active.
   *
   * @return true if the button is active, false otherwise.
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the active state of the button. If true, the button will be rendered as active. If false,
   * it will be rendered as inactive.
   *
   * @param active the new active state of the button.
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Renders the button on the Processing canvas. The button changes color based on its isActive
   * parameter and whether the mouse is currently over it.
   */
  public void draw() {

    // Set fill color based on active state and mouse hover
    if (active) {
      if (isMouseOver()) {
        processing.fill(150);
      } else {
        processing.fill(200);
      }
    } else {
      processing.fill(255,51,51);
    }

    processing.rect(x, y, width, height, 5);

    // Set text properties and draw the button label
    processing.fill(0);
    processing.textSize(14);
    processing.textAlign(PApplet.CENTER, PApplet.CENTER);
    processing.text(label, x + width / 2, y + height / 2);

  }

  public boolean isMouseOver() {
    return processing.mouseX >= x && processing.mouseX <= x + width && processing.mouseY >= y && processing.mouseY <= y + height;
  }
}
