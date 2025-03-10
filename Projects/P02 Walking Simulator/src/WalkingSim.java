//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 Walking Simulator
// Course:   CS 300 Fall 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  ChatGPT for class and method JavaDocs Help
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PImage;


/**
 * A collection of methods for animating some 
 * walking-person characters
 */
public class WalkingSim {
  
  private static Random randGen;
  private static int bgColor;
  private static PImage[] frames;
  private static Walker[] walkers;



  /**
   * Initializes the background color and loads the frames for the walkers.
   * Sets up the walker objects with random positions on the screen.
   */
  public static void setup() {
    randGen = new Random();
    bgColor = randGen.nextInt();
    
    frames = new PImage[Walker.NUM_FRAMES];
    
    for (int i = 0; i < frames.length; i++) {
      frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png");
    }
    
    walkers = new Walker[8];
    int randomNum = randGen.nextInt(walkers.length) + 1;

    for (int i = 0; i < randomNum; i++) {
      int wx = randGen.nextInt(Utility.width());
      int wy = randGen.nextInt(Utility.height());
      walkers[i] = new Walker(wx, wy);
  }

  }

  
  /**
   * Draws the background and all active walkers on the screen.
   * Walkers move if their 'walking' state is true and their position
   * is updated accordingly.
   */
  public static void draw() {
    Utility.background(bgColor);
    
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] != null) {
        Walker currentWalker = walkers[i];
        
        boolean isWalk = currentWalker.isWalking();
        if (isWalk) {
          currentWalker.setPositionX(currentWalker.getPositionX() + 3);
          
          if (currentWalker.getPositionX() > Utility.width()) {
            currentWalker.setPositionX(0);
          }
        }
        
        Utility.image(frames[currentWalker.getCurrentFrame()], 
            currentWalker.getPositionX(), currentWalker.getPositionY());
        
        if (isWalk) {
          currentWalker.update();
        }
      }
    }
    

  }


  /**
   * Checks whether the mouse is currently over the specified walker.
   * 
   * @param w   the Walker object to check
   * @return true if the mouse is over the walker, false otherwise
   */
  public static boolean isMouseOver(Walker w) {
    boolean flag = false;
    
    float imageHeight = frames[0].height;
    float imageWidth = frames[0].width;
    
    float min_x = w.getPositionX() - (imageWidth / 2);
    float max_x = w.getPositionX() + (imageWidth / 2);
    
    float min_y = w.getPositionY() - (imageHeight / 2);
    float max_y = w.getPositionY() + (imageHeight / 2);
    
    if ((Utility.mouseX() > min_x && Utility.mouseX() < max_x) && 
    (Utility.mouseY() > min_y && Utility.mouseY() < max_y)) {
      flag = true;
    }
    
    return flag;
        
    
  }


  /**
   * Called when the mouse is pressed. If the mouse is over a walker,
   * it sets that walker to start walking.
   */
  public static void mousePressed() {
    int lowIndex = -1;
    
    for (int i = 0; i < walkers.length; i++) {
      if (walkers[i] != null) {
        boolean overCheck = WalkingSim.isMouseOver(walkers[i]);
        
        if (overCheck) {
          lowIndex = i;
          break;
        }
      }
    }
    
    if (lowIndex != -1) {
      walkers[lowIndex].setWalking(true);
    }
  }


  /**
   * Handles key press events. Pressing 'a' adds a new walker if there is
   * space available in the array. Pressing 's' stops all walkers from moving.
   * 
   * @param c the character corresponding to the key pressed
   */
  public static void keyPressed(char c) {
    if (c == 'a') {
      for (int i = 0; i < walkers.length; i++) {
        if (walkers[i] == null) {
          int wx = randGen.nextInt(Utility.width());
          int wy = randGen.nextInt(Utility.height());
          
          walkers[i] = new Walker(wx, wy);
          break;
        }
      }
    }
    else if (c == 's') {
      for (int i = 0; i < walkers.length; i++) {
        if (walkers[i] != null) {
          walkers[i].setWalking(false);
        }
      }
    }
    
  }

  
  


  /**
   * The main method that runs the walking simulator application.
   * This initializes the environment and begins the animation.
   */
  public static void main(String[] args) {
    Utility.runApplication();


    

  }

}




