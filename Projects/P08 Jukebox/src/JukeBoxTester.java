//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 JukeBoxTester class
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
 * Tester class for testing the functionality of the LinkedQueue, LinkedStack, Album, Song, and
 * Jukebox classes.
 */
public class JukeBoxTester {

  /**
   * Test the behavior of adding an element to the stack.
   * 
   * @return true if element is correctly added to the stack, false otherwise
   */
  public static boolean testStackAdd() {
    //Test adding 1 element
    LinkedStack<Integer> stack1 = new LinkedStack<Integer>();
    ArrayList<Integer> arr1 = stack1.getList();
    
    if (!arr1.isEmpty()) {
      return false;
    }
    
    stack1.push(10);
    arr1 = stack1.getList();
    
    if (stack1.peek() != 10 || !arr1.contains(10) || arr1.size() != 1 || arr1.indexOf(10) != 0) {
      return false;
    }
    
    //Test adding more than 1 elements
    stack1.push(20);
    arr1 = stack1.getList();
    
    if (stack1.peek() != 20 || !arr1.contains(20) || arr1.size() != 2 || arr1.indexOf(20) != 0
        || arr1.indexOf(10) != 1) {
      return false;
    }
    
    stack1.push(3);
    arr1 = stack1.getList();
    
    if (stack1.peek() != 3 || !arr1.contains(3) || arr1.size() != 3 || arr1.indexOf(3) != 0
        || arr1.indexOf(20) != 1 || arr1.indexOf(10) != 2) {
      return false;
    }
    return true;
  }

  
  /**
   * Test the behavior of removing an element from the stack.
   * 
   * @return true if element is correctly removed from the stack, false otherwise
   */
  public static boolean testStackRemove() {
    LinkedStack<Integer> stack2 = new LinkedStack<Integer>();
    
    //1. Test remove empty stack
    Integer removed = stack2.pop();
    if (removed != null || !stack2.isEmpty()) {
      return false;
    }
    
    //2. Test remove 1 element
    stack2.push(20);
    Integer removed2 = stack2.pop();
    if (removed2 != 20 || !stack2.isEmpty()) {
      return false;
    }
    
    //3. Test removing multiple elements
    stack2.push(1);
    stack2.push(5);
    stack2.push(9);
    
    ArrayList<Integer> arrList = new ArrayList<Integer>();
    Integer removed3 = stack2.pop();
    arrList = stack2.getList();
    
    if (removed3 != 9 || arrList.size() != 2 || !arrList.contains(1) || !arrList.contains(5)
        || arrList.indexOf(1) != 1 || arrList.indexOf(5) != 0) {
      return false;
    }
    
    Integer removed4 = stack2.pop();
    arrList = stack2.getList();
    if (removed4 != 5 || arrList.size() != 1 || !arrList.contains(1) || arrList.indexOf(1) != 0) {
      return false;
    }
    
    Integer removed5 = stack2.pop();
    arrList = stack2.getList();
    if (removed5 != 1 || arrList.size() != 0) {
      return false;
    }
    return true;
  }
  
  
  

  /**
   * Test the behavior of adding an element to the queue.
   * 
   * @return true if element is correctly added to the queue, false otherwise
   */
  public static boolean testQueueAdd() {
    //Testing add 1 element
    LinkedQueue<Integer> queue1 = new LinkedQueue<Integer>();
    ArrayList<Integer> arr1 = queue1.getList();
    
    if (!arr1.isEmpty()) {
      return false;
    }
    
    queue1.enqueue(10);
    arr1 = queue1.getList();
    
    if (queue1.peek() != 10 || !arr1.contains(10) || arr1.size() != 1 || arr1.indexOf(10) != 0) {
      return false;
    }
    
    //Test adding more than 1 elements
    queue1.enqueue(20);
    arr1 = queue1.getList();
    
    if (queue1.peek() != 10 || !arr1.contains(20) || arr1.size() != 2 || arr1.indexOf(20) != 1
        || arr1.indexOf(10) != 0) {
      return false;
    }
    
    queue1.enqueue(3);
    arr1 = queue1.getList();
    
    if (queue1.peek() != 10 || !arr1.contains(3) || arr1.size() != 3 || arr1.indexOf(3) != 2
        || arr1.indexOf(20) != 1 || arr1.indexOf(10) != 0) {
      return false;
    }
    return true;
  }

  

  /**
   * Test the behavior of removing an element from the queue.
   * 
   * @return true if element is correctly removed from the queue, false otherwise
   */
  public static boolean testQueueRemove() {
    LinkedQueue<Integer> queue2 = new LinkedQueue<Integer>();
    
    //1. Test remove empty queue
    Integer removed = queue2.dequeue();
    if (removed != null || !queue2.isEmpty()) {
      return false;
    }
    
    //2. Test remove 1 element
    queue2.enqueue(20);
    Integer removed2 = queue2.dequeue();
    if (removed2 != 20 || !queue2.isEmpty()) {
      return false;
    }
    
    //3. Test removing multiple elements
    queue2.enqueue(1);
    queue2.enqueue(5);
    queue2.enqueue(9);
    
    ArrayList<Integer> arrList = new ArrayList<Integer>();
    Integer removed3 = queue2.dequeue();
    arrList = queue2.getList();
    
    if (removed3 != 1 || arrList.size() != 2 || !arrList.contains(5) || !arrList.contains(9)
        || arrList.indexOf(5) != 0 || arrList.indexOf(9) != 1) {
      return false;
    }
    
    Integer removed4 = queue2.dequeue();
    arrList = queue2.getList();
    if (removed4 != 5 || arrList.size() != 1 || !arrList.contains(9) || arrList.indexOf(9) != 0) {
      return false;
    }
    
    Integer removed5 = queue2.dequeue();
    arrList = queue2.getList();
    if (removed5 != 9 || arrList.size() != 0) {
      return false;
    }
    return true;
  }

  
  /**
   * Test the behavior of peeking at the top element (for stack) and the front element (for queue).
   * 
   * @return true if the correct element returned for both data structures, false otherwise
   */
  public static boolean testPeek() {
    //STACK:
    LinkedStack<Integer> stack3 = new LinkedStack<Integer>();
    
    stack3.push(3);
    if (stack3.peek() != 3) {
      return false;
    }
    
    stack3.push(7);
    stack3.push(9);
    if (stack3.peek() != 9) {
      return false;
    }
    
    //QUEUE:
    LinkedQueue<Integer> queue3 = new LinkedQueue<Integer>();
    
    queue3.enqueue(7);
    if (queue3.peek() != 7) {
      return false;
    }
    
    queue3.enqueue(11);
    queue3.enqueue(17);
    if (queue3.peek() != 7) {
      return false;
    }
    return true;
  }

  /**
   * This method tests whether the contains method correctly identifies whether a specific element
   * exists in a stack and a queue.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testContains() {
    //STACK:
    LinkedStack<Integer> stack4 = new LinkedStack<Integer>();
    
    if (stack4.contains(10)) {
      return false;
    }
    
    stack4.push(20);
    if (!stack4.contains(20)) {
      return false;
    }
    
    stack4.push(36);
    stack4.push(54);
    if (!stack4.contains(20) || !stack4.contains(36) || !stack4.contains(54)) {
      return false;
    }
    
    //QUEUE:
    LinkedQueue<Integer> queue4 = new LinkedQueue<Integer>();
    
    if (queue4.contains(10)) {
      return false;
    }
    
    queue4.enqueue(34);
    if (!queue4.contains(34)) {
      return false;
    }
    
    queue4.enqueue(76);
    queue4.enqueue(23283);
    if (!queue4.contains(34) || !queue4.contains(76) || !queue4.contains(23283)) {
      return false;
    }
    return true;
  }
  
  

  /**
   * Test the behavior of getting the list of elements in the stack and queue.
   * 
   * @return true if method returns a correctly ordered list for both data structures, false
   *         otherwise
   */
  public static boolean testGetList() {
    //STACK:
    LinkedStack<Integer> stack5 = new LinkedStack<Integer>();
    ArrayList<Integer> arrL = stack5.getList();
    
    if (!arrL.isEmpty()) {
      return false;
    }
    
    stack5.push(25);
    arrL = stack5.getList();
    if (arrL.size() != 1 || !arrL.contains(25) || arrL.indexOf(25) != 0) {
      return false;
    }
    
    stack5.push(78);
    stack5.push(97);
    arrL = stack5.getList();
    if (arrL.size() != 3 || !arrL.contains(25) || arrL.indexOf(25) != 2 || 
        !arrL.contains(78) || arrL.indexOf(78) != 1 || !arrL.contains(97) ||
        arrL.indexOf(97) != 0) {
      return false;
    }
    
    //QUEUE:
    LinkedQueue<Integer> queue5 = new LinkedQueue<Integer>();
    ArrayList<Integer> arrLQ = queue5.getList();
    
    if (!arrLQ.isEmpty()) {
      return false;
    }
    
    queue5.enqueue(79);
    arrLQ = queue5.getList();
    if (arrLQ.size() != 1 || !arrLQ.contains(79) || arrLQ.indexOf(79) != 0) {
      return false;
    }
    
    queue5.enqueue(28);
    queue5.enqueue(67);
    arrLQ = queue5.getList();

    if (arrLQ.size() != 3 || !arrLQ.contains(79) || arrLQ.indexOf(79) != 0 || 
        !arrLQ.contains(28) || arrLQ.indexOf(28) != 1 || !arrLQ.contains(67) ||
        arrLQ.indexOf(67) != 2) {
      return false;
    }
    return true;
  }
  
  
  

  /**
   * Tests adding songs to an Album and verifies the size and content. Checks if songs are correctly
   * added in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToAlbum() {
    Album a1 = new Album("Good");
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    a1.addSong(s1);
    if (!a1.firstSong().equals(s1) || a1.size() != 1) {
      return false;
    }
    
    a1.addSong(s3);
    a1.addSong(s2);
    a1.addSong(s4);
    if (!a1.firstSong().equals(s4) || a1.size() != 4) {
      return false;
    }
    
    try {
      a1.addSong(s4);
      return false;
    }
    catch (IllegalArgumentException e) {
      //DO Nothing Here (Checking if it is throwing exception under certain scenarios
    }
       
    return true;
  }
  
  

  /**
   * Tests removing a song from an Album and verifies the size and content after removal. Checks if
   * songs are correctly removed in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testRemoveSongFromAlbum() {
    Album a1 = new Album("Good");
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    try {
      a1.removeSong();
      return false;
    }
    catch (NoSuchElementException e) {
      //Do Nothing Here
    }
    
    a1.addSong(s1);
    Song removed = a1.removeSong();
    if (!removed.equals(s1) || a1.size() != 0 || a1.firstSong() != null) {
      return false;
    }
    
    a1.addSong(s3);
    a1.addSong(s2);
    a1.addSong(s4);
    Song removed2 = a1.removeSong();
    if (!removed2.equals(s4) || a1.size() != 2 || !a1.firstSong().equals(s2)) {
      return false;
    }
    
    return true;
  }
  
  

  /**
   * Tests the toString method of the Album class. Verifies that the returned string correctly
   * represents all songs in LIFO order.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAlbumToString() {
    Album a2 = new Album("Good");
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    //Test the empty album's toString()
    if (!a2.toString().equals(a2.getAlbumName())) {
      return false;
    }
    
    //Test the album with 1 song
    a2.addSong(s2);
    if (!a2.toString().equals(a2.getAlbumName() + "\n" + s2.toString())) {
      return false;
    }
    
    //Test the album with multiple songs
    a2.addSong(s1);
    a2.addSong(s4);
    a2.addSong(s3);
    if (!a2.toString().equals(a2.getAlbumName() + "\n" + s3.toString() + "\n" + s4.toString() + 
        "\n" + s1.toString() + "\n" + s2.toString())) {
      return false;
    }
    
    return true;
  }
  
  

  /**
   * Tests adding a song to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddSongToJukebox() {
    JukeBox j1 = new JukeBox(3);
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    //Test adding one
    j1.addSongToQueue(s3);
    if (j1.capacity() != 3 || j1.size() != 1 || !j1.playSong().equals(s3)) {
      return false;
    }
    
    //Test adding two
    j1.addSongToQueue(s2);
    j1.addSongToQueue(s1);
    if (j1.capacity() != 3 || j1.size() != 2 || !j1.playSong().equals(s2)) {
      return false;
    }
    
    //Test adding three(should correct)
    j1.addSongToQueue(s4);
    j1.addSongToQueue(s3);
    if (j1.capacity() != 3 || j1.size() != 3 || !j1.playSong().equals(s1)) {
      return false;
    }
    
    //Test adding four(exceeds the capacity)
    j1.addSongToQueue(s1); //I check the order through removing, so now
                           //I need to re-add s1 that was removed in the  last step
    try {
      j1.addSongToQueue(s2);
      return false;
    }
    catch (IllegalStateException e) {
      //Do nothing
    }
    
    //Test adding duplicates
    Song removed = j1.playSong();  //I remove one to ensure not exceed the limit (s4 removed here)
    if (!removed.equals(s4)) {
      return false;
    }
    try {
      j1.addSongToQueue(s3);
      return false;
    }
    catch (IllegalArgumentException e) {
      //Do nothing
    }
    
    return true;
  }

  
  
  
  /**
   * Tests adding an album to the Jukebox and verifies the queue contents and size.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testAddAlbumToJukebox() {
    JukeBox j3 = new JukeBox(5);
    JukeBox j4 = new JukeBox(3);
    
    Album a9 = new Album("Bad");
    Album a10 = new Album("heuofh");
    Album a11 = new Album("heuofhr");
    Album a12 = new Album("fheuif");
    
    
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    Song s5 = new Song("5", "artist5");
    Song s6 = new Song("6", "artist6");
    Song s7 = new Song("7", "artist7");
    Song s8 = new Song("8", "artist8");
    
    
    
    j3.addAlbumToQueue(a9);
    if (!j3.isEmpty() || j3.capacity() != 5 || j3.size() != 0) {
      return false;
    }
    
    a9.addSong(s4);
    j3.addAlbumToQueue(a9);
    if (j3.capacity() != 5 || j3.size() != 1 || !j3.playSong().equals(s4)) {
      return false;
    }
    

    a10.addSong(s2);
    a10.addSong(s3);
    a10.addSong(s1);
    j3.addAlbumToQueue(a10);
    if (j3.capacity() != 5 || j3.size() != 3 || !j3.playSong().equals(s1)) {
      return false;
    }
    
    
    a11.addSong(s6);
    a11.addSong(s5);
    a11.addSong(s7);
    a11.addSong(s8);
    j3.addAlbumToQueue(a11);
    if (j3.capacity() != 5 || j3.size() != 5 || !j3.playSong().equals(s3)) {
      return false;
    }
    

    j4.addSongToQueue(s8);
    a12.addSong(s8);
    a12.addSong(s7);
    j4.addAlbumToQueue(a12);
    if (j4.capacity() != 3 || j4.size() != 2 || !j4.playSong().equals(s8)) {
      return false;
    }

    return true;
  }

  /**
   * Tests playing a song from the JukeboxQueue. Verifies that the song is removed from the queue
   * after playback.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testPlaySongFromJukebox() {
    JukeBox j7 = new JukeBox(4);
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    //Test the empty case
    try {
      j7.playSong();
      return false;
    }
    catch (NoSuchElementException e) {
      //Do nothing
    }
    
    //Remove one
    j7.addSongToQueue(s4);
    Song removed = j7.playSong();
    if (!removed.equals(s4) || j7.capacity() != 4 || j7.size() != 0) {
      return false;
    }
    
    j7.addSongToQueue(s3);
    j7.addSongToQueue(s1);
    j7.addSongToQueue(s2);
    Song removed2 = j7.playSong();
    if (!removed2.equals(s3) || j7.capacity() != 4 || j7.size() != 2) {
      return false;
    }

    return true;
  }

  
  
  /**
   * Tests shuffling the JukeBox queue. Verifies that the songs are reordered randomly after the
   * operation.
   * 
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testJukeboxShuffle() {
    JukeBox j8 = new JukeBox(5);
    Song s1 = new Song("1", "artist1");
    Song s2 = new Song("2", "artist2");
    Song s3 = new Song("3", "artist3");
    Song s4 = new Song("4", "artist4");
    
    j8.shuffleSongQueue();
    if (!j8.isEmpty() || j8.capacity() != 5 || j8.size() != 0) {
      return false;
    }
    
    j8.addSongToQueue(s3);
    j8.shuffleSongQueue();
    if (j8.capacity() != 5 || j8.size() != 1 || !j8.playSong().equals(s3)) {
      return false;
    }
    
    j8.addSongToQueue(s3);
    j8.addSongToQueue(s1);
    j8.addSongToQueue(s2);
    j8.addSongToQueue(s4);
    
    String beforeShuffle = j8.toString();
    j8.shuffleSongQueue();
    String afterShuffle = j8.toString();
    if (j8.capacity() != 5 || j8.size() != 4 || beforeShuffle.equals(afterShuffle)) {
      return false;
    }

    return true;
  }
  
  

  public static void main(String[] args) {
    // Running and printing results for all the tests

    boolean test1 = testStackAdd();
    System.out.println("testStackAdd: " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testStackRemove();
    System.out.println("testStackRemove: " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testQueueAdd();
    System.out.println("testQueueAdd: " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testQueueRemove();
    System.out.println("testQueueRemove: " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testPeek();
    System.out.println("testPeek: " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testContains();
    System.out.println("testContains: " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testGetList();
    System.out.println("testGetList: " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testAddSongToAlbum();
    System.out.println("testAddSongToAlbum: " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveSongFromAlbum();
    System.out.println("testRemoveSongFromAlbum: " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testAlbumToString();
    System.out.println("testAlbumToString: " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testAddSongToJukebox();
    System.out.println("testAddSongToJukebox: " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testAddAlbumToJukebox();
    System.out.println("testAddAlbumToJukebox: " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testPlaySongFromJukebox();
    System.out.println("testPlaySongFromJukebox: " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testJukeboxShuffle();
    System.out.println("testJukeboxShuffle: " + (test14 ? "PASS" : "FAIL"));

    System.out.println("ALL TESTS: " + (test1 && test2 && test3 && test4 && test5 && test6 && test7
        && test8 && test9 && test10 && test11 && test12 && test13 && test14 ? "PASS" : "FAIL"));
  }
}
