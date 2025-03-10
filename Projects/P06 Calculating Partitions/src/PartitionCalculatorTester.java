//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Calculating Partitions
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



//import java.util.ArrayList;
import java.util.Random;

/**
 * The tester class of PartitionCalculator.java
 */
public class PartitionCalculatorTester {

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsBase() {
    try {
      if (PartitionCalculator.numOfPartitions(1) != TesterUtility.getPartitionCount(1)) {
        return false;
      }

      if (PartitionCalculator.numOfPartitions(2) != TesterUtility.getPartitionCount(2)) {
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for recursive cases with
   * n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsRecursive() {
    try {
      if (PartitionCalculator.numOfPartitions(3) != TesterUtility.getPartitionCount(3)) {
        return false;
      }

      if (PartitionCalculator.numOfPartitions(4) != TesterUtility.getPartitionCount(4)) {
        return false;
      }

      if (PartitionCalculator.numOfPartitions(5) != TesterUtility.getPartitionCount(5)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.numOfPartitions method for multiple random cases
   * of N. (See write-up for more details). This should also account for unexpected 
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testNumOfPartitionsFuzz() {
    Random rand = new Random();
    boolean flag = false;
    
    //number of tests from 100-199
    int numTests = rand.nextInt(100) + 100;
    
    try {
      for (int i = 0; i < numTests; i++) {
        int N = rand.nextInt(50) + 1; 
        flag = PartitionCalculator.numOfPartitions(N) == TesterUtility.getPartitionCount(N);
      }
      
      if (!flag) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for base cases n = 1
   * and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsBase() {
    try {
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(1), 
          TesterUtility.getPartitions(1, false), false)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(2), 
          TesterUtility.getPartitions(2, false), false)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
  
    }
    return true;
  }


  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for recursive cases 
   * with n >= 3. There must be a total of 3 test cases. This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsRecursive() {
    try {
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(3), 
          TesterUtility.getPartitions(3, false), false)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(4), 
          TesterUtility.getPartitions(4, false), false)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(5), 
          TesterUtility.getPartitions(5, false), false)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
  
    }
    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculatePartitions method for multiple random 
   * cases of N. (See write-up for more details). This should also account for unexpected
   * exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalcPartitionsFuzz() {
    Random rand = new Random();
    boolean flag = false;
    
    int numTests = rand.nextInt(20) + 1;
    
    try {
      for (int i = 0; i < numTests; i++) {
        int N = rand.nextInt(35) + 1;
        flag = TesterUtility.comparePartitionLists(PartitionCalculator.calculatePartitions(N), 
            TesterUtility.getPartitions(N, false), false);
      }
      
      if (!flag) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermuations method for base cases 
   * n = 1 and n = 2. This should also account for unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsBase() {
    try {
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.
          calculateAllPermutations(TesterUtility.getPartitions(1, false)), 
          TesterUtility.getPartitions(1, true), true)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.
          calculateAllPermutations(TesterUtility.getPartitions(2, false)), 
          TesterUtility.getPartitions(2, true), true)) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  

  /**
   * Tests the correctness of PartitionCalculator.calculateAllPermutations method for recursive 
   * cases with n >= 3. There must be a total of 3 test cases. This should also account for 
   * unexpected exceptions that MAY occur.
   * @return true if it passes all test cases, false otherwise
   */
  public static boolean testCalculateAllPermutationsRecursive() {
    try {
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.
          calculateAllPermutations(TesterUtility.getPartitions(3, false)), 
          TesterUtility.getPartitions(3, true), true)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.
          calculateAllPermutations(TesterUtility.getPartitions(4, false)), 
          TesterUtility.getPartitions(4, true), true)) {
        return false;
      }
      
      if (!TesterUtility.comparePartitionLists(PartitionCalculator.
          calculateAllPermutations(TesterUtility.getPartitions(5, false)), 
          TesterUtility.getPartitions(5, true), true)) {
        return false;
      }
      
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Runs and outputs the results of all tester methods.
   * @return true if all tests return true, false otherwise
   * @author Michelle Jensen
   */
  public static boolean runAllTests() {
    boolean test1 = testNumOfPartitionsBase();
    System.out.println("testNumOfPartitionsBase(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testNumOfPartitionsRecursive();
    System.out.println("testNumOfPartitionsRecursive(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testCalcPartitionsBase();
    System.out.println("testUniquePartitionsBase(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testCalcPartitionsRecursive();
    System.out.println("testUniquePartitionsRecursive(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testCalculateAllPermutationsBase();
    System.out.println("testCalculateAllPermutationsBase(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testCalculateAllPermutationsRecursive();
    System.out.println("testCalculateAllPermutationsRecursive(): " + (test6 ? "PASS" : "FAIL"));
    
    boolean test7 = testNumOfPartitionsFuzz();
    System.out.println("testNumOfPartitionsFuzz(): " + (test7 ? "PASS" : "FAIL"));
    
    boolean test8 = testCalcPartitionsFuzz();
    System.out.println("testUniquePartitionsFuzz(): " + (test8 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8;
  }

  public static void main(String[] args) {
    System.out.println("runAllTest(): " + (runAllTests()? "PASS" : "FAIL"));
  }

}
