//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P01-Election Manager Tester
// Course:   CS 300 Fall 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         A TA in Office Hour on 9/9, I had the problem for dropCandidate() method
//                  implementation and he gave the idea that I could ignore the index
//                  of the dropped candidate and start traversing the array from index + 1
//
// Online Sources:  I asked ChatGPT on compareTo() method/ definition of helper method
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * A collection of tester methods for testing
 * the five methods in ElectionManager.java
 */

public class ElectionManagerTester {

  /**
   * Test whether an empty candidateList – with length > 0 but size == 0 not contains any given
   * candidate
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   *         true ONLY if all of our expectations are met by the method we are testing
   */

  public static boolean testContainsEmpty() {
    // for your use: an "empty" candidate list
    String[][] candidateList = {null, null, null, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 0;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = false;
    
    boolean actual = 
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;  

  }

  /**
   * Verifying whether the candidate has not been added to the race
   * 
   * @return  false if any of the scenarios we test have results other than what we expect;
   *          true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testDoesNotContain() {
    
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Khoury";
    String targetParty = "Ocean";
    boolean expected = false;
    
    boolean actual = 
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    return true;  
  }
  
  
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a candidate has
   * already been added to the race.
   * 
   * NOTE: This method ONLY tests scenarios where the candidate IS PRESENT in the list;
   * situations where the candidate is not present or the list is empty should be
   * tested in the other contains tester methods.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testDoesContain() { 
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = true;
    
    // (1b) call the method we are testing
    boolean actual = 
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }
  

  /**
   * test method for verifying whether a new candidate has been added correctly to the 
   * empty race (length > 0 but size == 0 list of candidates)
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   *         true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testAddToEmpty() {
    String[][] candidateList = {null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    
    String[][] expectedList = {
        {"Goldeen", "Water", "5"}, null, null};  
    int size = 0;
    int expected = 1;
    
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;
    
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a new candidate has
   * been added correctly to the race.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testAddToNonEmpty() {
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    
    String[][] expectedList = {
        {"Goldeen", "Water", "5"}, // alphabetically first, new candidate will be added here
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null};  // now only TWO null values in this length-6 array!
    int size = 3;
    int expected = 4;
    
    // (1b) call the method we are testing
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  
  /**
   * Verify whether adding a duplicate candidate or a candidate with negative votes is impossible
   * 
   * @return   false if any of the scenarios we test have results other than what we expect;
   *           true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testAddCandidateErrors() {
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null};
    
    String newName1 = "Squirtle";
    String newParty1 = "Water";
    int newVotes1 = 5;
    
    String newName2 = "Khoury";
    String newParty2 = "Ocean";
    int newVotes2 = -5;
    
    
    String[][] expectedList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null};  
    int size = 3;
    int expected = 3;
    
    int actual1 = 
        ElectionManager.addCandidate(candidateList, size, newName1, newParty1, newVotes1);
    int actual2 = 
        ElectionManager.addCandidate(candidateList, size, newName2, newParty2, newVotes2);
    

    if (expected != actual1) return false;
    if (expected != actual2) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;
    
  }


  /**
   * Verifying whether adding to a list of candidates with length == size, which contains no null 
   * values is not available
   * 
   * @return    false if any of the scenarios we test have results other than what we expect;
   *            true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testAddToFull() {
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}};
    
    String newName = "Khoury";
    String newParty = "Ocean";
    int newVotes = 29;
    
    String[][] expectedList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}};
    int size = 3;
    int expected = 3;
    
    int actual = 
        ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    if (expected != actual) return false;
    
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    
    return true;

  }


  /**
   * Verifying whether removing a candidate from a list with size == 1 is possible
   * 
   * @return    false if any of the scenarios we test have results other than what we expect;
   *            true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testDropOnlyCandidate() {
    String[][] candidateList1 = {
        {"Slowpoke", "Water", "3"}, 
        null, null, null};
    
    String[][] expectedList1 = {null, null, null, null};

    String name1 = "Slowpoke";
    String party1 = "Water";
    int size1 = 1;
    int expected1 = 0;

    String[][] candidateList2 = {
        {"Wooper", "Water", "6"}, 
        null, null};
    
    String[][] expectedList2 = {{"Wooper", "Water", "6"}, null, null};

    String name2 = "Wo";
    String party2 = "Water";
    int size2 = 1;
    int expected2 = 1;
    
    int actual1 =
        ElectionManager.dropCandidate(candidateList1, size1, name1, party1);
    int actual2 =
        ElectionManager.dropCandidate(candidateList2, size2, name2, party2);

    if (expected1 != actual1) return false;
    if (expected2 != actual2) return false;
    
    if (!Arrays.deepEquals(candidateList1, expectedList1)) return false;
    if (!Arrays.deepEquals(candidateList2, expectedList2)) return false;

    
    return true;
  }

  /**
   * Verifying whether asfter removing the first candidate in a list with size > 1, the
   * resulting array still be a compact, oversize array.
   * 
   * @return   false if any of the scenarios we test have results other than what we expect;
   *           true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testDropFirstCandidate() {
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String name = "Slowpoke";
    String party = "Water";
    int size = 3;
    int expected = 2;
    
    String[][] expectedList = {{"Squirtle", "Water", "127"}, 
        {"Wooper", "Water", "300"}, null, null, null, null};
    
    int actual =
        ElectionManager.dropCandidate(candidateList, size, name, party);
    
    if (expected != actual) return false;
    if (!Arrays.deepEquals(candidateList, expectedList)) return false;
    

    
    return true;
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether trying to drop a
   * candidate who is not running in the race correctly has NO effect on the candidate list.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testDropCandidateNotRunning() {
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String name = "Goldeen";
    String party = "Water";
    int size = 3;
    int expected = 3;
    
    // (1b) call the method we are testing
    int actual =
        ElectionManager.dropCandidate(candidateList, size, name, party);
    
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual) return false;
    
    // (2a) sometimes you may want to REPEAT the process with slightly different variables:
    name = "Slowpoke";
    party = "Fire"; // try with a name that's present but a different PARTY; should still not drop
    actual = ElectionManager.dropCandidate(candidateList, size, name, party);
    if (expected != actual) return false;
    
    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;
    
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
    
  }



  /**
   * Verifying whether the winner on a list with size == 1, returning the
   * single candidate’s name and party with 100.0% of the votes
   * 
   * @return  false if any of the scenarios we test have results other than what we expect;
   *          true ONLY if all of our expectations are met by the method we are testing
   */
  
  public static boolean testUncontestedWinner() {
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Slowpoke";
    String expectedParty = "(Water)";
    double expectedVotePct = 100.0;
    int size = 1;
    
    String result = ElectionManager.findWinner(candidateList, size);
    String[] resultPieces = result.split(" "); 

    if (resultPieces.length != 4) return false; 
    if (!resultPieces[3].endsWith("%")) return false; 

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; 

    if (!resultPieces[2].equals("-")) return false; 

    double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
    if (actualVotePct != expectedVotePct) return false;

    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;

    return true;
    
  }
  
  /**
   * PROVIDED TESTER METHOD: example test method for verifying the results of an election
   * where one candidate has received a clear majority of the votes cast.
   * 
   * @return false if any of the scenarios we test have results other than what we expect;
   * true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testClearWinner() {
    
    // (1a) set up the test variables
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "97"},
        {"Wooper", "Water", "300"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Wooper";
    String expectedParty = "(Water)";
    double expectedVotePct = 300.0/(300+97+3)*100;
    int size = 3;
    
    // (1b) call the method we are testing
    String result = ElectionManager.findWinner(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    // NOTE: for a String, this takes a little more processing to do sensitively.
    // We expect this result to be "Wooper (Water) - 75.0%" but there may be some weirdness
    // especially with that percentage. See how we do it here:
    
    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string
    
    if (resultPieces.length != 4) return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%")) return false; // no % at the end

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-")) return false; // forgot the "-" between party and %

    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct = Double.valueOf(resultPieces[3].substring(0,resultPieces[3].length()-1));
    if (Math.abs(actualVotePct-expectedVotePct) > 0.01) return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  
  /**
   * Verifying whether when no candidate has > 50% of the votes, the method returns “CONTINGENT”
   * 
   * @return    false if any of the scenarios we test have results other than what we expect;
   *            true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testContingentElection() {
    String[][] candidateList = {
        {"Slowpoke", "Water", "3"},
        {"Squirtle", "Water", "3"},
        {"Wooper", "Water", "3"},
        null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedResult = "CONTINGENT";
    int size = 3;

    String result = ElectionManager.findWinner(candidateList, size);

    if (result != expectedResult) return false;
    if (!Arrays.deepEquals(candidateList, candidateCopy)) return false;

    return true;
  }

  
  /**
   * Verifying if the list has 0 or 1 candidates, this method returns “UNCONTESTED”
   * 
   * @return   false if any of the scenarios we test have results other than what we expect;
   *           true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testUncontestedLowestPolling() {
    String[][] candidateList1 = {
        {"Slowpoke", "Water", "3"}, 
        null, null, null};
    String[][] candidateCopy1 = Arrays.copyOf(candidateList1, candidateList1.length);
    int size1 = 1;
    String expected = "UNCONTESTED";
    
    String[][] candidateList2 = {null, null, null};
    String[][] candidateCopy2 = Arrays.copyOf(candidateList2, candidateList2.length);
    int size2 = 0;

    
    String actual1 =
        ElectionManager.findLowestPollingCandidate(candidateList1, size1);
    String actual2 =
        ElectionManager.findLowestPollingCandidate(candidateList2, size2);
    
    if (expected != actual1) return false;
    if (expected != actual2) return false;

    if (!Arrays.deepEquals(candidateList1, candidateCopy1)) return false;
    if (!Arrays.deepEquals(candidateList2, candidateCopy2)) return false;

    return true;

  }

  /**
   * Verifying if list's size >= 2 and all candidates have unique vote counts, the method returns 
   * the correct result for the lowest votes' candidate information
   * 
   * @return  false if any of the scenarios we test have results other than what we expect;
   *          true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testLowestUniqueVoteCount() {
    String[][] candidateList1 = {
        {"Slowpoke", "Water", "3"}, 
        {"Squirtle", "Water", "97"},
        null, null, null};
    String[][] candidateCopy1 = Arrays.copyOf(candidateList1, candidateList1.length);
    int size1 = 2;
    String expected1 = "Slowpoke (Water) - 3";
    
    String[][] candidateList2 = {
        {"Slowpoke", "Water", "3"}, 
        {"Squirtle", "Water", "97"},
        {"Tom", "Lay", "1"},
        null, null, null};
    String[][] candidateCopy2 = Arrays.copyOf(candidateList2, candidateList2.length);
    int size2 = 3;
    String expected2 = "Tom (Lay) - 1";
    
    
    String actual1 =
        ElectionManager.findLowestPollingCandidate(candidateList1, size1);
    String actual2 =
        ElectionManager.findLowestPollingCandidate(candidateList2, size2);
    
    if (!expected1.equals(actual1)) return false;
    if (!expected2.equals(actual2)) return false;

    if (!Arrays.deepEquals(candidateList1, candidateCopy1)) return false;
    if (!Arrays.deepEquals(candidateList2, candidateCopy2)) return false;

    return true;

  }

  /**
   * Verifying when list's size >= 2 and at least two candidates are tied for lowest number of 
   * votes, the method returns the candidate's information that is less alphabetical
   * 
   * @return  false if any of the scenarios we test have results other than what we expect;
   *          true ONLY if all of our expectations are met by the method we are testing
   */
  public static boolean testLowestVoteCountTied() {
    String[][] candidateList1 = {
        {"Slowpoke", "Water", "3"}, 
        {"Squirtle", "Water", "3"},
        null, null, null};
    String[][] candidateCopy1 = Arrays.copyOf(candidateList1, candidateList1.length);
    int size1 = 2;
    String expected1 = "Slowpoke (Water) - 3";
    
    String[][] candidateList2 = {
        {"Slowpoke", "Water", "30"}, 
        {"Squirtle", "Water", "30"},
        {"Tom", "Lay", "30"},
        null, null, null};
    String[][] candidateCopy2 = Arrays.copyOf(candidateList2, candidateList2.length);
    int size2 = 3;
    String expected2 = "Slowpoke (Water) - 30";
    
    
    String actual1 =
        ElectionManager.findLowestPollingCandidate(candidateList1, size1);
    String actual2 =
        ElectionManager.findLowestPollingCandidate(candidateList2, size2);
    
    if (!expected1.equals(actual1)) return false;
    if (!expected2.equals(actual2)) return false;

    if (!Arrays.deepEquals(candidateList1, candidateCopy1)) return false;
    if (!Arrays.deepEquals(candidateList2, candidateCopy2)) return false;

    return true;
    
  }

  /**
   * PROVIDED MAIN METHOD to manage the tester methods above.
   * 
   * We're getting a little esoteric here to take advantage of loops to keep the code short;
   * each pass through the loop could also be written as follows:
   * 
   *   boolean singleTest = testMethodCall();
   *   allPass &= singleTest;
   *   System.out.println("testMethodCall : " + singleTest);
   * 
   * @throws NoSuchMethodException if you spell a method name incorrectly
   * 
   * And a couple of other "checked" exceptions that should never happen with our usage here:
   * @throws IllegalAccessException
   * @throws java.lang.reflect.InvocationTargetException
   */
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, 
    java.lang.reflect.InvocationTargetException {
    boolean allPass = true, singlePass = true;
    String printFormat = "%-29s: %s\n";
    
    // NOTE TO STUDENTS: If you create any additional tests for any of the methods in
    // ElectionManager, add their names to the appropriate array below!
    String[] containsTests = {"testContainsEmpty", "testDoesNotContain", "testDoesContain"};
    String[] addTests = {"testAddToEmpty", "testAddToNonEmpty", "testAddCandidateErrors",
        "testAddToFull"};
    String[] dropTests = {"testDropOnlyCandidate", "testDropFirstCandidate", 
        "testDropCandidateNotRunning"};
    String[] winTests = {"testUncontestedWinner", "testClearWinner", "testContingentElection"};
    String[] lowTests = {"testUncontestedLowestPolling", "testLowestUniqueVoteCount", 
        "testLowestVoteCountTied"};
    
    String[][] testNames = {containsTests, addTests, dropTests, winTests, lowTests};
    
    // NOTE TO STUDENTS: this for-loop is moving through the method names we've added to the 2D 
    // array testNames and attempting to call methods with those names from this tester
    // (specifically line 286 here). See Java's reflection framework for more details!
    for (String[] testSet : testNames) {
      for (String name : testSet) {
        singlePass = (boolean) ElectionManagerTester.class.getDeclaredMethod(name).invoke(null);
        allPass &= singlePass;
        System.out.printf(printFormat, name, singlePass);
      }
      System.out.println();
    }
 
    System.out.println("ALL TESTS: "+allPass);


  }

}

