//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Election
// Course:   CS 300 Fall 2024
//
// Author:   Reginald Yuan
// Email:    yuan253@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Zhiyuan Li
// Partner Email:   zli2562@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests to check the
 * correctness of the Candidate, Election, and Ballot classes.
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness. This test
   * accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      //   1) the constructor didn't intialize a data field correctly OR
      //   2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0"))
        return false;

      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) where an
   * exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;
    boolean flag4 = false;

    // Case 1: name = null
    try {
      Candidate c = new Candidate(null, "basketball");
    } catch (IllegalArgumentException e) {
      flag1 = true;
    } catch (Exception e) {
      // If any other exception is caught, this is incorrect
      return false;
    }

    // Case 2: name.trim().isEmpty()
    try {
      Candidate c = new Candidate("  ", "soccer");
    } catch (IllegalArgumentException e) {
      flag2 = true;
    } catch (Exception e) {
      // If any other exception is caught, this is incorrect
      return false;
    }

    // Case 3: party = null
    try {
      Candidate c = new Candidate("messi", null);
    } catch (IllegalArgumentException e) {
      flag3 = true;
    } catch (Exception e) {
      // If any other exception is caught, this is incorrect
      return false;
    }

    // Case 4: party.trim().isEmpty()
    try {
      Candidate c = new Candidate("messi", "  ");
    } catch (IllegalArgumentException e) {
      flag4 = true;
    } catch (Exception e) {
      // If any other exception is caught, this is incorrect
      return false;
    }

    return flag1 && flag2 && flag3 && flag4;
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.) This test accounts
   * for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {
    try {
      // test the 2-argument constructor
      Election elec = new Election("Ballon d'Or", 5);

      // check that the instance data fields have been initialized correctly
      if (!elec.toString().equals("Ballon d'Or"))
        return false;

      if (elec.getNumCandidates() != 0)
        return false;

      if (elec.capacity() != 5)
        return false;

      // check the toString method for an empty election
      if (!elec.toString().equals("Ballon d'Or"))
        return false;
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) in situations
   * where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {

    // Case 1: maxCandidates = 0
    try {
      Election elec = new Election("Ballon d'Or", 0);
      return false;
    } catch (IllegalArgumentException e) {
      // expected
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Case 2: maxCandidates < 0
    try {
      Election elec = new Election("Ballon d'Or", -5);
      return false;
    } catch (IllegalArgumentException e) {
      // expected
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations. This
   * test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    try {
      Election elec = new Election("Best Athlete", 2);
      Candidate c1 = new Candidate("lebron", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");

      elec.addCandidate(c1);
      elec.addCandidate(c2);

      if (elec.getNumCandidates() != 2) {
        return false;
      }

      if (!elec.toString()
          .equals("Best Athlete" + "\n" + "lebron (basketball): 0" + "\n" + "messi (soccer): 0")) {
        return false;
      }

    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.
    Election elec = null;
    try {
      elec = new Election("Best Athlete", 1);
      Candidate c1 = new Candidate("lebron", "basketball");

      elec.addCandidate(c1);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    // Case 1: add a same candidate
    try {
      elec.addCandidate(new Candidate("lebron", "basketball"));
      return false; // this line should not be reached
    } catch (IllegalArgumentException e) {
      // expected
    } catch (Exception e) {
      System.out.println("Unexpected exception type!");
      e.printStackTrace();
      return false;
    }

    // Case 2: add more candidates than capacity
    try {
      elec.addCandidate(new Candidate("messi", "soccer"));
      if (elec.getNumCandidates() != 1) {
        return false; // this line should not be reached if capacity is respected
      }
    } catch (IllegalArgumentException e) {
      // expected
    } catch (Exception e) {
      System.out.println("Unexpected exception type!");
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations. This test
   * accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    try {
      Election elec = new Election("Best Athlete", 2);
      Candidate c1 = new Candidate("lebron", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");

      elec.addCandidate(c1);
      elec.addCandidate(c2);

      elec.vote(c1);

      // check the number of votes after voting
      if (c1.getNumVotes() != 1){
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.

    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1);
      election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Election's removeCandidate() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    try {
      Election elec = new Election("Best Athlete", 2);
      Candidate c1 = new Candidate("lebron", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");

      elec.addCandidate(c1);
      elec.addCandidate(c2);

      elec.removeCandidate(c1);
      // check the number of the candidates after removing
      if (elec.getNumCandidates() != 1) {
        return false;
      }

      // check toString output after removing
      if (!elec.toString().equals("Best Athlete" + "\n" + "messi (soccer): 0")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.

    Election elec = null; // declare outside of the try block for scope reasons
    Candidate c1 = new Candidate("lebron", "basketball");
    try {
      elec = new Election("Best Athlete", 2);
    } catch (Exception e) {
    e.printStackTrace();
    return false;
    }

    try {
      elec.removeCandidate(c1);
      return false;
    } catch (IllegalStateException e) {
      // expected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      elec.addCandidate(c1);
      elec.removeCandidate(new Candidate("messi", "soccer"));
      return false;
    } catch (NoSuchElementException e) {
      //expected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations. This test accounts for
   * the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    try {
      // Phase 1: add elections to the Ballot class
      Ballot.clearElections();
      Ballot.addElection(new Election("Best Athlete", 2));
      Ballot.addElection(new Election("Best Team", 3));

      // Phase 2: create a Ballot and verify that it has the correct number of elections
      Ballot ballot = new Ballot();

      // check toString method
      if (!ballot.toString().equals("Best Athlete: false\nBest Team: false")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // all tests pass
    return true;
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s) in
   * situations where an exception is expected. See the Ballot documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    try {
      // ensure the elections list is empty
      Ballot.clearElections();
      try {
        Ballot ballot = new Ballot();
        return false; // this line should not be reached
      } catch (IllegalStateException e) {
        // expected
      } catch (Exception e) {
        return false;
      }

      // Add elections after Ballot creation
      Ballot.addElection(new Election("Best Athlete", 2));
      Ballot ballot = new Ballot();
      try {
        Ballot.addElection(new Election("Best Team", 3));
        return false; // this line should not be reached
      } catch (IllegalStateException e) {
        // expected
      } catch (Exception e) {
        return false;
      }

      // add a duplicate election
      Ballot.clearElections();
      Ballot.addElection(new Election("Best Athlete", 2));
      try {
        Ballot.addElection(new Election("Best Athlete", 2));
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // all tests pass:
    return true;
  }
  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations. This test accounts
   * for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    try {
      Ballot.clearElections();
      Election elec1 = new Election("Best Athlete", 2);
      Election elec2 = new Election("Best Team", 3);

      Candidate c1 = new Candidate("lebron", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      Candidate c3 = new Candidate("Barcelona", "soccer");

      elec1.addCandidate(c1);
      elec1.addCandidate(c2);
      elec2.addCandidate(c3);

      Ballot.addElection(elec1);
      Ballot.addElection(elec2);

      Ballot ballot = new Ballot();

      ballot.vote("Best Athlete", c2);

      if (!ballot.hasVoted("Best Athlete")) {
        return false;
      }

      if (ballot.hasVoted("Best Team")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVoteExceptions() {
    try {
      Ballot.clearElections();
      Election elec = new Election("Best Athlete", 2);

      Candidate c1 = new Candidate("lebron", "basketball");
      elec.addCandidate(c1);

      Ballot.addElection(elec);
      Ballot ballot = new Ballot();

      // try to vote twice in the same election
      ballot.vote("Best Athlete", c1);
      try {
        ballot.vote("Best Athlete", c1);
        return false;
      } catch (IllegalStateException e) {
        // expected
      }

      // try to vote in an election that doesn't exist
      try {
        ballot.vote("Best Team", c1);
        return false;
      } catch (NoSuchElementException e) {
        // expected
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   *
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {

    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotSetup(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10 && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call
   * them directly in the main method rather than adding them to the previous method.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }


}