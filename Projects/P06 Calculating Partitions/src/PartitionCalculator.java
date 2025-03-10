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
// Online Sources:  ChatGPT
//
///////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;

/**
 * Set of partition calculator methods.
 */
public class PartitionCalculator {

  /**
   * Given a value N, return the total number of partitions for that value N.
   *
   * @param N is the value need to be partitioned
   * @return the return from the helper method.
   */
  public static int numOfPartitions(int N) {
    if (N == 0) {
      return 0;
    }
    return numOfPartitionsHelper(N, N);
  }

  
  /**
   * Helper method of numOfPartitions
   * 
   * @param n value need to be partitioned
   * @param m  the largest number allowed of the current n
   * @return  the total number of partitions of n
   */
  private static int numOfPartitionsHelper(int n, int m) {
    if (n == 0) {
      return 1;
    }

    if (n < 0 || m <= 0) {
      return 0;
    }
    return numOfPartitionsHelper(n - m, m) + numOfPartitionsHelper(n, m - 1);
  
  }





  /**
   * Public method to calculate all unique partitions of N.
   *
   * @param N The number to be partitioned
   * @return A list of all unique partitions of N.
   */
  public static ArrayList<Partition> calculatePartitions(int N) {
    ArrayList<Partition> result = new ArrayList<>();
    calculatePartitionsHelper(N, N, new Partition(N), result);
    return result;
  }

  /**
   * Helper method to recursively calculate all unique partitions of a number.
   *
   * @param n                The number to be partitioned.
   * @param m        The maximum number that can be used in the partition.
   * @param current The current state of the partition being constructed.
   * @param result           The list to store all the unique partitions.
   */ 
  private static void calculatePartitionsHelper(int n, int m, Partition current, 
      ArrayList<Partition> result) {
    if (n == 0) {
      result.add(current.copyOf()); 
      return;
    }

    if (n < 0 || m <= 0) {
      return; 
    }

    current.addNumber(m);
    calculatePartitionsHelper(n - m, m, current, result);
    current.removeLast(); 

    calculatePartitionsHelper(n, m - 1, current, result);
  }


  /**
   * Public method to calculate all permutations of each partition given.
   *
   * @param partitions A list of partitions.
   * @return A list of all permutations of each partition.
   */
  public static ArrayList<Partition> calculateAllPermutations(ArrayList<Partition> partitions) {
    ArrayList<Partition> result = new ArrayList<>();
    for (Partition partition : partitions) {
      calculateAllPermutationsHelper(partition, 0, result);
    }
    return result;
  }

  /**
   * Helper method to recursively calculate all permutations of a partition.
   *
   * @param partition The partition to be permuted.
   * @param index     The current index for permutation.
   * @param result    The list to store all the unique permutations.
   */
  private static void calculateAllPermutationsHelper(Partition partition, int index,
      ArrayList<Partition> result) {
    if (index == partition.length() - 1) {
      result.add(partition.copyOf());
      return;
    }
    ArrayList<Integer> usedNumbers = new ArrayList<>();

    for (int i = index; i < partition.length(); i++) {
      int currentNum = partition.getNumAt(i);
      if (!usedNumbers.contains(currentNum)) {
        usedNumbers.add(currentNum);
        partition.swapNumbers(index, i);
        calculateAllPermutationsHelper(partition, index + 1, result);
        partition.swapNumbers(index, i);
      }
    }
  }
  
  
}

