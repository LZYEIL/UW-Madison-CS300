//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hyperloop LoopStationTester
// Course:   CS 300 Spring 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    Reginald Yuan
// Partner Email:   yuan253@wisc.edu
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

import java.util.NoSuchElementException;

/**
 * This class tests the LoopStation class, and by extension, the Track class
 */
public class LoopStationTester {

  /**
   * Checks the correctness of the createPod() method. This method should: - create a Pod with the
   * given capacity and podClass - add it to the correct end of the correct Track in the LoopStation
   * - return a reference (shallow copy) to that Pod Note that the tracks in LoopStation are
   * protected, so you may access them directly for testing purposes
   *
   * @return true if createPod() is functioning correctly, false otherwise
   */
  public static boolean testCreatePod() {
    try {
      LoopStation station = new LoopStation();

      Pod firstClassPod = station.createPod(3, true);
      if (firstClassPod.getPodClass() != Pod.FIRST || station.waitingFirst.size() != 1) {
        return false;
      }

      Pod economyClassPod = station.createPod(5, false);
      if (economyClassPod.getPodClass() != Pod.ECONOMY || station.waitingEconomy.size() != 1) {
        return false;
      }

      return true;
    } catch (MalfunctioningPodException e) {
      System.out.println("Pod malfunctioned during testCreatePod.");
      return false;
    }
  }

  /**
   * Checks the correctness of the launchPod() method. This method should: - throw a
   * NoSuchElementException if no pods are waiting to launch - launch first class pods from the END
   * of the waitingFirst track - launch economy class pods from the BEGINNING of the waitingEconomy
   * track - launch ALL first class pods before launching ANY economy class pods Note that the
   * tracks in LoopStation are protected, so you may access them directly for testing purposes
   *
   * @return true if launchPod() is functioning correctly, false otherwise
   */
  public static boolean testLaunchPod() {
    LoopStation station = new LoopStation();

    Pod firstClassPod1 = station.createPod(3, true);
    Pod firstClassPod2 = station.createPod(4, true);
    Pod economyClassPod1 = station.createPod(5, false);
    Pod economyClassPod2 = station.createPod(6, false);

    station.launchPod();
    if (station.launched.size() != 1 || station.launched.get(0) != firstClassPod1) {
      System.out.println("Error: First Class Pod launch order incorrect.");
      return false;
    }

    station.launchPod();
    if (station.launched.size() != 2 || station.launched.get(1) != firstClassPod2) {
      System.out.println("Error: Second First Class Pod launch order incorrect.");
      return false;
    }

    // 发射第一个 Economy Class Pod
    station.launchPod();
    if (station.launched.size() != 3 || station.launched.get(2) != economyClassPod1) {
      System.out.println("Error: First Economy Class Pod launch order incorrect.");
      return false;
    }

    station.launchPod();
    if (station.launched.size() != 4 || station.launched.get(3) != economyClassPod2) {
      System.out.println("Error: Second Economy Class Pod launch order incorrect.");
      return false;
    }

    if (!station.waitingFirst.isEmpty() || !station.waitingEconomy.isEmpty()) {
      System.out.println("Error: Waiting queue is not empty after launching all Pods.");
      return false;
    }

    try {
      station.launchPod();
      System.out.println("Error: No exception thrown when no Pods are waiting.");
      return false;
    } catch (NoSuchElementException e) {
    }

    return true;
    }


  /**
   * Checks the correctness of the clearMalfunctioning() method. This method should: - repeatedly
   * check the launched track for malfunctioning pods - remove those pods correctly - report the
   * number of pods it removed once there are no longer any malfunctioning pods
   *
   * Things to consider when you are testing:
   *
   * - there is a protected setNonFunctional() method you may use for testing purposes to ensure
   * that at least one pod is non-functional
   *
   * - calling isFunctional() on a Pod may cause it to malfunction! You should come up with an
   * alternate way to check whether a Pod is functional, if you have not already.
   *
   * - verify that the difference in number of pods from before the method was called and after the
   * method was called is equal to the number that it reported
   *
   * @return true if clearMalfunctioning() is functioning correctly, false otherwise
   */
  public static boolean testClearMalfunctioning() {

    LoopStation station = new LoopStation();

    Pod pod1 = station.createPod(3, true);
    Pod pod2 = station.createPod(5, false);
    station.launched.add(pod1);
    station.launched.add(pod2);
    pod2.setNonFunctional();

    int removed = station.clearMalfunctioning();
    if (removed != 1 || station.launched.size() != 1 || station.launched.get(0) != pod1) {
      return false;
    }
    return true;

  }

  /**
   * Checks the correctness of the three getNumXXX() methods from LoopStation. This will require
   * adding Pods of various types, loading them with passengers, and launching them.
   *
   * @return true if the getNumXXX() methods are all functioning correctly, false otherwise
   */
  public static boolean testGetNums() {
    try {
      LoopStation station = new LoopStation();

      Pod firstClassPod = station.createPod(3, true);
      Pod economyClassPod = station.createPod(5, false);

      firstClassPod.addPassenger("Tony");
      firstClassPod.addPassenger("Jack");
      economyClassPod.addPassenger("Bob");

      if (station.getNumWaiting() != 2){
        return false;
      }
      station.launchPod();
      station.launchPod();

      if (station.getNumLaunched() != 2){
        return false;
      }
      return true;
    } catch (MalfunctioningPodException e) {
      System.out.println("Pod malfunctioned during testGetNums.");
      return false;
    }
  }

  public static void main(String[] args) {
    boolean test1 = testCreatePod();
    System.out.println("testCreatePod: " + (test1 ? "PASS" : "fail"));

    boolean test2 = testLaunchPod();
    System.out.println("testLaunchPod: " + (test2 ? "PASS" : "fail"));

    boolean test3 = testClearMalfunctioning();
    System.out.println("testClearMalfunctioning: " + (test3 ? "PASS" : "fail"));

    boolean test4 = testGetNums();
    System.out.println("testGetNums: " + (test4 ? "PASS" : "fail"));

    System.out.println("ALL TESTS: " + ((test1 && test2 && test3 && test4) ? "PASS" : "fail"));
  }

}
