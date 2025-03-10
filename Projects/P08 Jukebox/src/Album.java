//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 Album class
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents an album consisting of a stack of songs. The Album class allows adding and removing 
 * songs in LIFO order.
 */
public class Album {
  
  private LinkedStack<Song> trackList;
  private String albumName;
  private int size;
  
  
  /**
   * Constructs an empty Album with a new LinkedStack to store song and size as zero.
   * 
   * @param albumName  the name of the album
   */
  public Album(String albumName) {
    if (albumName == null || albumName.isBlank()) {
      throw new IllegalArgumentException("The name is either null or empty");
    }
    this.trackList = new LinkedStack<Song>();
    this.albumName = albumName;
    this.size = 0;
  }
  
  
  
  /**
   * Adds a song to the top of the album's track list and adds the Album reference to the song.
   * 
   * @param s  the Song object to be added to the album
   */
  public void addSong(Song s) {
    if (this.trackList.contains(s)) {
      throw new IllegalArgumentException("The song exists");
    }
    
    this.trackList.push(s);
    this.size = this.size + 1;
    s.setAlbum(this);
  }
  
  
  
  /**
   * Removes the most recently added song from the album.
   * 
   * @return  the Song object removed from the top of the album
   */
  public Song removeSong() {
    if (this.trackList.isEmpty()) {
      throw new NoSuchElementException("Empty!");
    }
    
    Song removed = this.trackList.pop();
    removed.setAlbum(null);     //I set to null reference with the removed song
    this.size = this.size - 1;
    return removed;
  }
  
  
  
  /**
   * Retrieves the song that is currently at the top of the album's track list, 
   * without removing it from the stack.
   * 
   * @return  the Song object at the top of the album, or null if the album is empty
   */
  public Song firstSong() {
    return this.trackList.peek();
  }
  
  
  
  /**
   * Retrieves the name of the album.
   * 
   * @return  the the name of the album.
   */
  public String getAlbumName() {
    return this.albumName;
  }
  
  
  
  /**
   * Returns the number of songs currently in the album.
   * 
   * @return  the number of songs in the album
   */
  public int size() {
    return this.size;
  }
  
  
  
  /**
   * Returns a string representation of the album, with the name of the album as the first line 
   * and listing all songs from the top of the stack to the bottom. The output string should 
   * separate Songs using a new line (\n). Top of the stack should be the first line of the string.
   * 
   * @return  a string listing all songs in the album in LIFO order (top of stack comes FIRST)
   */
  @Override
  public String toString() {
    String albumName = "";
    ArrayList<Song> arrList = this.trackList.getList();
    
    if (arrList.size() == 0) {
      albumName = this.getAlbumName();
      return albumName;
    }

    albumName = this.getAlbumName() + "\n";
      
    for (int i = 0; i < arrList.size(); i++) {
      if (i == arrList.size() - 1) {
        albumName += arrList.get(i).toString();
      }
      else {
        albumName += arrList.get(i).toString() + "\n";
      }
    }
    return albumName;
  }

}
