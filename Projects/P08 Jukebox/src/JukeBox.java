//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 JukeBox class
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
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Represents a jukebox that holds a queue of songs for playback. 
 * The jukebox has a maximum capacity to limit the number of songs that can be queued at once.
 */
public class JukeBox {
  
  private LinkedQueue<Song> songQueue;
  private int capacity;
  
  
  
  /**
   * Constructs a new JukeBox with a specified capacity.
   * 
   * @param capacity  the maximum number of songs that can be held in the jukebox queue
   */
  public JukeBox(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity is negative!");
    }
    this.songQueue = new LinkedQueue<Song>();
    this.capacity = capacity;
  }
  
  
  
  /**
   * Adds a single song to the end of the queue if space allows.
   * 
   * @param song  the Song object to be added to the queue
   */
  public void addSongToQueue(Song song) {
    if (this.isFull()) {
      throw new IllegalStateException("Exceed maximum capacity!");
    }
    
    if (this.songQueue.contains(song)) {
      throw new IllegalArgumentException("The song already exists!");
    }
    
    this.songQueue.enqueue(song);
  }
  
  
  
  /**
   * Adds an entire album to the queue if space allows. Each song in the album is added 
   * individually in order. Add as many songs as possible if album has size greater than space, 
   * skip songs already added to queue.
   * 
   * @param album  a list of Song objects representing an album
   */
  public void addAlbumToQueue(Album album) {
    while (!this.isFull() && album.size() != 0) {
      if (!this.songQueue.contains(album.firstSong())) {
        this.songQueue.enqueue(album.removeSong());
      }
      else {
        album.removeSong();
      }
    }
  }
  
  
  
  
  /**
   * Plays and removes the song at the front of the queue.
   * 
   * @return  the Song object that was played from the front.
   */
  public Song playSong() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("The queue is empty!");
    }
    return this.songQueue.dequeue();
  }
  
  
  
  
  /**
   * Shuffles the songs present in the queue. 
   * Size and capacity after this operation should remain the same.
   */
  public void shuffleSongQueue() {
    ArrayList<Song> arrList = this.songQueue.getList();
    Collections.shuffle(arrList);
    
    this.songQueue.clear();
    for (int i = 0; i < arrList.size(); i++) {
      this.addSongToQueue(arrList.get(i));
    }
  }
  
  

  
  /**
   * Returns the current number of songs in the queue.
   * 
   * @return  the number of songs in the queue
   */
  public int size() {
    return this.songQueue.size();
  }
  
  
  
  /**
   * Returns the capacity of the queue.
   * 
   * @return  the maximum number of songs that can be in the queue
   */
  public int capacity() {
    return this.capacity;
  }
  
  
  /**
   * Checks if the queue is full.
   * 
   * @return  true if the queue has reached its capacity, false otherwise
   */
  public boolean isFull() {
    return this.size() >= this.capacity();
  }
  
  
  
  /**
   * Checks if the queue is empty.
   * 
   * @return  true if the queue has no songs, false otherwise
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  
  
  /**
   * Provides a string representation of the jukebox queue for debugging and display. 
   * Song representation followed by -> and finishes with "END". Song1: Artist1 (Album1) -> 
   * Song2: Artist2 (Album2) -> END
   * 
   * @return  a string representing the queue, including song details in order
   */
  @Override
  public String toString() {
    ArrayList<Song> arrList = this.songQueue.getList();
    String result = "";
    
    for (int i = 0; i < arrList.size(); i++) {
      if (i == arrList.size() - 1) {
        result += arrList.get(i).toString() + " -> END";
      }
      else {
        result += arrList.get(i).toString() + " -> ";
      }
    }
    return result;
  }

}
