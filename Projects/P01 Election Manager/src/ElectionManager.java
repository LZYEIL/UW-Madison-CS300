//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P01-Election Manager
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


/**
 * A collection of utility methods for maintaining an 
 * oversize array of candidates running for a given office.
 */

public class ElectionManager {
  
  
  
  /**
   * Determines whether the given candidate, uniquely specified by their name and party affiliation, 
   * is present in the provided list of candidates.
   *
   * @param candidates  A two-dimensional oversize array containing the current list of candidates 
   *                    as [name, party, numVotes]. This input value is assumed to conform to the 
   *                    standards of oversize arrays, and is assumed to be in alphabetical order 
   *                    by candidate name.
   * @param numCandidates  The current size of the candidates oversize array at the time of input. 
   *                       This value is assumed to be accurate.   
   * @param name        The name of the candidate to search for.
   * @param party       The party affiliation of the candidate to search for.
   * @return  true if a candidate with the given name AND party affiliation is present in the list; 
   *          false otherwise
   */

  public static boolean containsCandidate(String[][] candidates, int numCandidates, 
      String name, String party) {
    
    boolean flag = false;
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
        flag = true;
      }
    }
    return flag;
    
  }
  
  
  /**
   * Adds a candidate with the given name, party affiliation, and vote count to the given list of 
   * candidates, maintaining the candidate list in alphabetical order by NAME, and returns the new 
   * total number of candidates in the array. Does NOT add the candidate if another candidate with 
   * the same name and party affiliation is already present in the list, or if the provided 
   * vote count is a negative value, or if the input array has no room to add another candidate. 
   * 
   * @param candidates      A two-dimensional oversize array containing the current list of 
   *                        candidates as [name, party, numVotes]. This input value is assumed to 
   *                        conform to the standards of oversize arrays, and is assumed to be in 
   *                        alphabetical order by candidate name.
   * @param numCandidates   The current size of the candidates oversize array at the time of input. 
   *                        This value is assumed to be accurate.
   * @param name            The name of the candidate to add
   * @param party           The party affiliation of the candidate to add
   * @param numVotes        The number of votes the candidate to add has received
   * @return            The size of the candidates oversize array after this method has completed. 
   *                    This value will be either the same as numCandidates or one larger.
   */
  
  public static int addCandidate(String[][] candidates, int numCandidates, 
      String name, String party, int numVotes) {
    
    
    boolean flag = ElectionManager.containsCandidate(candidates, numCandidates, name, party);
    boolean countFlag = (numVotes < 0);
    boolean roomFlag = (candidates.length - numCandidates) <= 0;
    
    String[] new_cand = {name, party, Integer.toString(numVotes)};
    
    if (flag || countFlag || roomFlag) {
      return numCandidates;
    }
    else {
      candidates[numCandidates] = new_cand;
      numCandidates += 1;
      
      //bubble sort
      //candidates[j][0] is the name, where we need to compareTo each other
      //and sort
      for (int i = 0; i < numCandidates; i++) {
        for (int j = 0; j < numCandidates - i - 1; j++) {
          if (candidates[j][0].compareTo(candidates[j + 1][0]) > 0) {
            String[] temp = candidates[j];
            candidates[j] = candidates[j + 1];
            candidates[j + 1] = temp;
          }
        }
      }
      
      return numCandidates;      
    }
       
  }
  
  
  /**
   * Removes the candidate specified uniquely by name and party from the given array of candidates, 
   * maintaining the candidates array in alphabetical order by name. Does not modify the array if 
   * the candidate specified is not present in the list.
   * 
   * @param candidates      A two-dimensional oversize array containing the current list of 
   *                        candidates as [name, party, numVotes]. This input value is assumed to 
   *                        conform to the standards of oversize arrays, and is assumed to be in 
   *                        alphabetical order by candidate name.
   * @param numCandidates   The current size of the candidates oversize array at the time of input. 
   *                        This value is assumed to be accurate.
   * @param name            The name of the candidate to drop from the list
   * @param party           The party affiliation of the candidate to drop from the list
   * @return    The size of the candidates oversize array after this method has completed. 
   *            This value will be either the same as numCandidates or one smaller.
   */
  
  public static int dropCandidate(String[][] candidates, int numCandidates, 
      String name, String party) {

    int index = -1;

    boolean flag = ElectionManager.containsCandidate(candidates, numCandidates, name, party);
    if (! flag) {
      return numCandidates;
    }
    else {
      for (int i = 0; i < numCandidates; i++) {
        if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
          index = i;
        }
      }
      
      numCandidates -= 1;

      for (int i = index; i < candidates.length - 1; i++) {
        candidates[i] = candidates[i + 1];
      }
      candidates[candidates.length - 1] = null;

      return numCandidates;
    }

  }
  
  
  
  /**
   * Helper method used to calculate the total votes of all candidates
   * 
   * @param candidates     A two-dimensional oversize array containing the current list of 
   *                       candidates as [name, party, numVotes]. This input value is assumed to 
   *                       conform to the standards of oversize arrays, and is assumed to be in 
   *                       alphabetical order by candidate name.
   * @param numCandidates  The current size of the candidates oversize array at the time of input. 
   *                       This value is assumed to be accurate.
   * @return  The total sum votes of all valid candidates
   */

  private static int calculateVotes(String[][] candidates, int numCandidates) {
    
    int sum = 0;
    for (int i = 0; i < numCandidates; i++) {
      sum += Integer.parseInt(candidates[i][2]);
    }
    
    return sum;
    
  }

  
  /**
   * Finds the candidate with the majority (that is, >50%) of total votes cast. If no candidate in 
   * the list has received more than 50% of the total votes, returns the string "CONTINGENT". 
   * This method will NOT be tested with a numCandidates of 0. 
   * 
   * @param candidates      A two-dimensional oversize array containing the current list of 
   *                        candidates as [name, party, numVotes]. This input value is assumed to 
   *                        conform to the standards of oversize arrays, and is assumed to be in 
   *                        alphabetical order by candidate name.
   * @param numCandidates   The current size of the candidates oversize array at the time of input. 
   *                        This value is assumed to be accurate.
   * @return    A string containing the "name (party) - votePct%" values of the candidate receiving 
   *            a majority of the votes, or the string "CONTINGENT" if no single candidate has 
   *            received more than half of the votes.
   */
  
  public static String findWinner(String[][] candidates, int numCandidates) {
    
    int voteSum = ElectionManager.calculateVotes(candidates, numCandidates);
    int index = -1;
    
    for (int i = 0; i < numCandidates; i++) {
      if (Integer.parseInt(candidates[i][2]) > (voteSum / 2)) {
        index = i;
      }
    }
    
    if (index == -1) {
      return "CONTINGENT";
    }
    else {
      double percent = (Double.valueOf(candidates[index][2]) / voteSum) * 100;
      
      String result = candidates[index][0] + " (" + candidates[index][1] + ") - " 
      + Double.toString(percent) + "%";
      
      return result;
    }

  }
  
  
  /**
   * Finds the candidate with the smallest number of votes cast. If there are multiple candidates 
   * with the same smallest number of votes, this method returns the one whose name is closest to 
   * the beginning of the alphabet (smallest index). If there are fewer than two candidates in the 
   * election, this method returns the string "UNCONTESTED".
   * 
   * @param candidates     A two-dimensional oversize array containing the current list of 
   *                       candidates as [name, party, numVotes]. This input value is assumed to 
   *                       conform to the standards of oversize arrays, and is assumed to be in 
   *                       alphabetical order by candidate name.
   * @param numCandidates  The current size of the candidates oversize array at the time of input. 
   *                       This value is assumed to be accurate.
   * @return    A string containing the "name (party) - numVotes" values of the lowest-index 
   *            candidate receiving the smallest number of votes, or "UNCONTESTED" if there are one 
   *            or zero candidates in the list.
   */
  
  public static String findLowestPollingCandidate(String[][] candidates,
      int numCandidates) {
    
    
    if (numCandidates < 2) {
      return "UNCONTESTED";
    }
    else {
      int[] minValArr = new int[numCandidates];
      int min = Integer.parseInt(candidates[0][2]);
      
      for (int i = 0; i < numCandidates; i++) {
        if (Integer.parseInt(candidates[i][2]) < min) {
          min = Integer.parseInt(candidates[i][2]);
        }
      }
      
      
      for (int i = 0, j = 0; i < numCandidates; i++) {
        if (Integer.parseInt(candidates[i][2]) == min) {
          minValArr[j] = i;
          j++;
        }
      }
      
      String result = candidates[minValArr[0]][0] + " (" + candidates[minValArr[0]][1] + ") - " 
          + candidates[minValArr[0]][2];
      
      return result;
      

    } 
  }
  
  

}



