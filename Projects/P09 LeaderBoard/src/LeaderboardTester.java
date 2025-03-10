//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 LeaderboardTester class
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



public class LeaderboardTester {
  
  /////////////////////////////////////////// COMPARE TO ///////////////////////////////////////////

  public static boolean testPlayerCompareTo() {
    boolean test1 = testCompareToDiffScore();
    boolean test2 = testCompareToSameScoreDiffName();
    boolean test3 = testCompareToEqual();
    if (!test1) System.out.print("diffScore FAIL ");
    if (!test2) System.out.print("diffName FAIL ");
    if (!test3) System.out.print("equals FAIL ");
    return test1 && test2 && test3;
  }
  
  /**
   * Compare players with different scores and verify the results
   * @return true is pass all, false otherwise
   */
  private static boolean testCompareToDiffScore() {
    
    Player p1 = new Player("Jack", 23);
    Player p2 = new Player("Luke", 27);
    
    if (p1.compareTo(p2) >= 0) {
      return false;
    }
    
    if (p2.compareTo(p1) <= 0) {
      return false;
    }
    
    return true;
  }
  
  
  /**
   * compare players with the same score but different names and verify the results
   * @return true if pass all, false otherwise
   */
  private static boolean testCompareToSameScoreDiffName() {
    
    Player p1 = new Player("Jack", 23);
    Player p2 = new Player("Luke", 23);
    
    if (p1.compareTo(p2) >= 0) {
      return false;
    }
    
    if (p2.compareTo(p1) <= 0) {
      return false;
    }
    
    return true;
  }
  
  
  /**
   * compare players with the same score and name and verify the results
   * @return  true if pass all, false otherwise
   */
  private static boolean testCompareToEqual() {

    Player p1 = new Player("Jack", 23);
    Player p2 = new Player("Jack", 23);
    
    if (p1.compareTo(p2) != 0) {
      return false;
    }
    
    if (p2.compareTo(p1) != 0) {
      return false;
    }
    
    return true;
  }
  
  
  
  ///////////////////////////////////////// LOOKUP: NAME /////////////////////////////////////////
  
  public static boolean testNameLookup() {
    boolean test1 = testLookupRoot();
    boolean test2 = testLookupLeft();
    boolean test3 = testLookupRight();
    boolean test4 = testLookupNotPresent();
    if (!test1) System.out.print("lookupRoot FAIL ");
    if (!test2) System.out.print("lookupLeft FAIL ");
    if (!test3) System.out.print("lookupRight FAIL ");
    if (!test4) System.out.print("lookupNotPresent FAIL");
    return test1 && test2 && test3 && test4;
  }
  
  // HINT: for these testers, add one Player at the root and then build the rest of the tree manually
  // using the reference returned by getRoot(), so that you are not relying on the correctness of
  // the addPlayer() method.
  
  /**
   * look up the player stored in the root note
   * 
   * @return true if pass all, false otherwise
   */
  private static boolean testLookupRoot() {
    Player p1 = new Player("Lok", 23);
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    
    if (!l1.lookup("Lok").equals(p1)) {
      return false;
    }
    
    return true;
  }
  
  
  /**
   * look up a player stored in the left subtree
   * 
   * @return  true if pass all, false otherwise
   */
  private static boolean testLookupLeft() {
    Player p1 = new Player("Lok", 23);
    Player p2 = new Player("Lack", 27);
    Player p3 = new Player("Julia", 15);
    Player p4 = new Player("Kamala", 12);
    Player p5 = new Player("Hava", 17);
    
    
    BSTNode<Player> rightN = new BSTNode<Player>(p2);
    BSTNode<Player> N2 = new BSTNode<Player>(p3);
    BSTNode<Player> N3 = new BSTNode<Player>(p4);
    BSTNode<Player> N4 = new BSTNode<Player>(p5);
    
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    
    BSTNode<Player> root1 = l1.getRoot();
    root1.setRight(rightN);
    root1.setLeft(N2);
    N2.setLeft(N3);
    N2.setRight(N4);
    
    if (!l1.lookup("Julia").equals(p3)) {
      return false;
    }
    
    if (!l1.lookup("Hava").equals(p5)) {
      return false;
    }
    
    if (!l1.lookup("Kamala").equals(p4)) {
      return false;
    }

    return true;
  }
  
  
  
  /**
   * look up a player stored in the right subtree
   * 
   * @return true is all pass, false otherwise
   */
  private static boolean testLookupRight() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    
    BSTNode<Player> N1 = new BSTNode<Player>(p2);
    BSTNode<Player> N2 = new BSTNode<Player>(p3);
    BSTNode<Player> N3 = new BSTNode<Player>(p4);
    BSTNode<Player> N4 = new BSTNode<Player>(p5);
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    BSTNode<Player> root1 = l1.getRoot();
    root1.setLeft(N1);
    
    root1.setRight(N2);
    N2.setLeft(N3);
    N2.setRight(N4);
    
    if (!l1.lookup("Julia").equals(p3)) {
      return false;
    }
    
    if (!l1.lookup("Hava").equals(p5)) {
      return false;
    }
    
    if (!l1.lookup("Kamala").equals(p4)) {
      return false;
    }

    return true;
  }
  
  
  
  /**
   * look up the name of a player who is not present in the tree
   * 
   * @return true if all pass, false otherwise
   */
  private static boolean testLookupNotPresent() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    
    BSTNode<Player> N1 = new BSTNode<Player>(p2);
    BSTNode<Player> N2 = new BSTNode<Player>(p3);
    BSTNode<Player> N3 = new BSTNode<Player>(p4);
    BSTNode<Player> N4 = new BSTNode<Player>(p5);
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    BSTNode<Player> root1 = l1.getRoot();
    root1.setLeft(N1);
    
    root1.setRight(N2);
    N2.setLeft(N3);
    N2.setRight(N4);
    
    if (l1.lookup("Jiuop") != null) {
      return false;
    }
    
    if (l1.lookup("Jasper") != null) {
      return false;
    }
    
    return true;
  }
  
  
  
  //////////////////////////////////////////// ADD ////////////////////////////////////////////
  
  public static boolean testAdd() {
    boolean test1 = testAddPlayerEmpty();
    boolean test2 = testAddPlayer();
    boolean test3 = testAddPlayerDuplicate();
    if (!test1) System.out.print("addEmpty FAIL ");
    if (!test2) System.out.print("addPlayer FAIL ");
    if (!test3) System.out.print("addDuplicate FAIL ");
    return test1 && test2 && test3;
  }
  
  
  /**
   * verify that addPlayer() correctly adds a player to an empty BST
   * 
   * @return true if all pass, false otherwise
   */
  private static boolean testAddPlayerEmpty() {
    Player p1 = new Player("Lok", 15);
    Leaderboard l1 = new Leaderboard();
    
    if (l1.addPlayer(p1) == false) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    if (l1.size() != 1) {
      return false;
    }
    
    if (l1.count() != 1) {
      return false;
    }

    return true;
  }
  
  
  /**
   * verify that addPlayer() correctly adds a player to a non-empty BST
   * 
   * @return true if all pass, false otherwise
   */
  private static boolean testAddPlayer() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 17);
    Player p7 = new Player("Mark", 31);
    
    Leaderboard l1 = new Leaderboard();
    
    //1
    if (l1.addPlayer(p4) == false) {
      return false;
    }
    if (!l1.getRoot().getData().equals(p4)) {
      return false;
    }
    if (l1.size() != 1) {
      return false;
    }
    if (l1.count() != 1) {
      return false;
    }
    
    //2
    if (l1.addPlayer(p1) == false) {
      return false;
    }
    if (!l1.getRoot().getLeft().getData().equals(p1)) {
      return false;
    }
    
    if (l1.size() != 2) {
      return false;
    }
    if (l1.count() != 2) {
      return false;
    }
    
    //3
    if (l1.addPlayer(p2) == false) {
      return false;
    }
    if (!l1.getRoot().getLeft().getLeft().getData().equals(p2)) {
      return false;
    }
    
    if (l1.size() != 3) {
      return false;
    }
    if (l1.count() != 3) {
      return false;
    }
    
    //4
    if (l1.addPlayer(p3) == false) {
      return false;
    }
    
    if (!l1.getRoot().getRight().getData().equals(p3)) {
      return false;
    }
    if (l1.size() != 4) {
      return false;
    }
    if (l1.count() != 4) {
      return false;
    }
    
    //5
    if (l1.addPlayer(p5) == false) {
      return false;
    }
    
    if (!l1.getRoot().getRight().getRight().getData().equals(p5)) {
      return false;
    }
    if (l1.size() != 5) {
      return false;
    }
    if (l1.count() != 5) {
      return false;
    }
    
    //6
    if (l1.addPlayer(p6) == false) {
      return false;
    }
    
    if (!l1.getRoot().getLeft().getRight().getData().equals(p6)) {
      return false;
    }
    if (l1.size() != 6) {
      return false;
    }
    if (l1.count() != 6) {
      return false;
    }
    
    //7
    if (l1.addPlayer(p7) == false) {
      return false;
    }
    
    if (!l1.getRoot().getRight().getRight().getRight().getData().equals(p7)) {
      return false;
    }
    if (l1.size() != 7) {
      return false;
    }
    if (l1.count() != 7) {
      return false;
    }
    
    System.out.println();
    System.out.println(l1.prettyPrint());
    return true;
  }
  
  
  
  /**
   * verify that adding a duplicate player returns false, does not modify the BST, and
   * does not cause an exception
   * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testAddPlayerDuplicate() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p6 = new Player("Kamala", 17);
    
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    
    try {
      if (l1.addPlayer(p6) == true) {
        return false;
      }
    }
    catch (Exception e) {
      return false;
    }
    

    if (l1.count() != 4 || l1.size() != 4) {
      return false;
    }
    System.out.println();
    System.out.println(l1.prettyPrint());

    return true;
  }
  
  
  //////////////////////////////////////////// REMOVE ////////////////////////////////////////////
  
  public static boolean testRemove() {
    boolean test1 = testRemoveLeaf();
    boolean test2 = testRemoveOneChild();
    boolean test3 = testRemoveTwoChildren();
    boolean test4 = testRemoveNotInTree();
    if (!test1) System.out.print("remove FAIL ");
    if (!test2) System.out.print("removeOneChild FAIL ");
    if (!test3) System.out.print("removeTwoChildren FAIL ");
    if (!test4) System.out.print("removeNotInTree FAIL ");
    return test1 && test2 && test3 && test4;
  }
  
  
  /**
   * verify that removePlayer() correctly removes a leaf node from the tree
   * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testRemoveLeaf() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 19);
    Player p7 = new Player("Mark", 31);
    
    Leaderboard l1 = new Leaderboard();
    
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (l1.removePlayer(p2) != true || l1.count() != 6 || l1.size() != 6) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    if (l1.removePlayer(p6) != true || l1.count() != 5 || l1.size() != 5) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    if (l1.removePlayer(p7) != true || l1.count() != 4 || l1.size() != 4) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    return true;
  }
  
  
  
  /**
   * verify that removePlayer() correctly removes a player with ONE child
   * 
   * @return true if all pass, false otherwise
   */
  private static boolean testRemoveOneChild() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 19);
    Player p7 = new Player("Mark", 31);
    
    Leaderboard l1 = new Leaderboard();
    
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (l1.removePlayer(p4) != true || l1.count() != 6 || l1.size() != 6) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    if (l1.removePlayer(p5) != true || l1.count() != 5 || l1.size() != 5) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p1)) {
      return false;
    }
    
    System.out.println();
    System.out.println(l1.prettyPrint());

    return true;
  }
  
  
  
  /**
   * verify that removePlayer() correctly removes a player with TWO children
   * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testRemoveTwoChildren() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 19);
    Player p7 = new Player("Mark", 31);
    
    Leaderboard l1 = new Leaderboard();
    
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (l1.removePlayer(p1) != true || l1.count() != 6 || l1.size() != 6) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p4)) {
      return false;
    }
    
    if (l1.removePlayer(p3) != true || l1.count() != 5 || l1.size() != 5) {
      return false;
    }
    
    if (!l1.getRoot().getData().equals(p4)) {
      return false;
    }
    
    System.out.println();
    System.out.println(l1.prettyPrint());
    return true;
  }
  
  
  
  /**
   * verify that removing a player not in the tree returns false, does not modify the BST, 
   * and does not cause an exception
   * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testRemoveNotInTree() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 19);
    Player p7 = new Player("Mark", 31);
    
    Player p8 = new Player("Harry", 9000);
    
    Leaderboard l1 = new Leaderboard();
    
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    try {
      if (l1.removePlayer(p8) != false || l1.count() != 7 || l1.size() != 7) {
        return false;
      }
    }
    catch (Exception e) {
      return false;
    }
    
    return true;
  }
 
  //////////////////////////////////////////// GET NEXT ////////////////////////////////////////////
  
  public static boolean testGetNext() {
    boolean test1 = testGetNextAfterRoot();
    boolean test2 = testGetNextAfterLeftSubtree();
    boolean test3 = testGetNextAfterRightSubtree();
    if (!test1) System.out.print("afterRoot FAIL ");
    if (!test2) System.out.print("afterLeft FAIL ");
    if (!test3) System.out.print("afterRight FAIL ");
    return test1 && test2 && test3;
  }
  
  
  /**
   * // verify that next() returns the correct Player when passed the Player in the root node
   * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testGetNextAfterRoot() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Jei", 31);
    Player p6 = new Player("Jeremy", 9); 
    Player p7 = new Player("John", 14);
    
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (!l1.next(p1).equals(p4)) {
      return false;
    }

    return true;
  }
  
  
  
  /**
   * // verify that next() returns the correct Player when the correct value is in the left
    // subtree of the leaderboard
     * 
   * @return  true if all pass, false otherwise
   */
  private static boolean testGetNextAfterLeftSubtree() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Jei", 31);
    Player p6 = new Player("Jeremy", 9); 
    Player p7 = new Player("John", 14);
    
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (!l1.next(p6).equals(p2)) {
      return false;
    }
    
    if (!l1.next(p2).equals(p7)) {
      return false;
    }
    
    if (!l1.next(p7).equals(p1)) {
      return false;
    }

    return true;
  }
  
  
  
  /**
   * verify that next() returns the correct Player when the correct value is in the right
   * subtree of the leaderboard
   * 
   * @return true if all pass, false otherwise
   */
  private static boolean testGetNextAfterRightSubtree() {
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Jei", 31);
    Player p6 = new Player("Jeremy", 9); 
    Player p7 = new Player("John", 14);
    
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p4);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    if (!l1.next(p1).equals(p4)) {
      return false;
    }
    
    if (!l1.next(p4).equals(p3)) {
      return false;
    }
    
    if (!l1.next(p3).equals(p5)) {
      return false;
    }
    
    if (l1.next(p5) != null) {
      return false;
    }

    return true;
  }
  
  
  
  //////////////////////////////////////////// MAIN ////////////////////////////////////////////
  
  public static void main(String[] args) {
    System.out.print("Player compareTo(): ");
    System.out.println(testPlayerCompareTo()?"PASS":"");
    
    System.out.print("Leaderboard lookup(): ");
    System.out.println(testNameLookup()?"PASS":"");
    
    System.out.print("Leaderboard add(): ");
    System.out.println(testAdd()?"PASS":"");

    System.out.print("Leaderboard remove(): ");
    System.out.println(testRemove()?"PASS":"");

    System.out.print("Leaderboard next(): ");
    System.out.println(testGetNext()?"PASS":"");
    
    
    ///////////////////////This section is used to test Iterator///////////////////////
    
    Player p1 = new Player("Lok", 15);
    Player p2 = new Player("Lack", 13);
    Player p3 = new Player("Julia", 29);
    Player p4 = new Player("Kamala", 17);
    Player p5 = new Player("Hava", 31);
    Player p6 = new Player("Andrew", 17);
    Player p7 = new Player("Mark", 31);
    
    Leaderboard l1 = new Leaderboard();
    l1.addPlayer(p4);
    l1.addPlayer(p1);
    l1.addPlayer(p2);
    l1.addPlayer(p3);
    l1.addPlayer(p5);
    l1.addPlayer(p6);
    l1.addPlayer(p7);
    
    for (Player p : l1) {
      System.out.println(p);
    }
    
  }
  
}
