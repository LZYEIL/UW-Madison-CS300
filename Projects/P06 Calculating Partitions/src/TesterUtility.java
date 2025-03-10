import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

/**
 * Set of static utility methods that can be use for testing purposes.
 * @author Michelle Jensen
 */
public class TesterUtility {
  
  //stores all counts for numbers of partitions
  private static ArrayList<Integer> counts;
  
  /**
   * Retrieves the number of partitions for a given N from the solutions file
   * @param N the N value of the partitions
   * @return the number of partitions for N
   */
  public static int getPartitionCount(int N) {
    //if we haven't loaded in the counts before
    if(counts == null) {
      counts = new ArrayList<Integer>();
      
      //this is a try-catch w/ resources! (it auto closes the Scanner when done!)
      try(Scanner fileIn = new Scanner(new File("partitions.csv"))) {
        fileIn.nextLine(); //skip the column headers
        
        //read and process each line, add count to list
        while(fileIn.hasNextLine()) {
          String str = fileIn.nextLine();
          counts.add(Integer.parseInt(str.split(",")[1]));
        }  
      } catch (Exception e) { //print if there was an issue processing file
        e.printStackTrace();
      }
    }
    
    return counts.get(N-1);
  }
  
  /**
   * Retrieves the list of partitions for the given N. The list of what is returned will vary
   * based on the permutations argument.
   * @param N the N value for the partitions
   * @param permutations true if the list should contain all permutations of the partitions,
   *    false otherwise
   * @return list of partitions for the value N, based on whether it should include all permutations
   *    of them
   */
  public static ArrayList<Partition> getPartitions(int N, boolean permutations){
    ArrayList<Partition> partitions = new ArrayList<Partition>();

    //select the correct file based on which problem we are testing
    String fileName = permutations ? "permutedPartitions.csv" : "partitions.csv";
    
    try(Scanner fileIn = new Scanner(new File(fileName))) {
      
      //skip down to the required line
      for(int i= 0; i < N; i++)
        fileIn.nextLine();
      
      
      String data = fileIn.nextLine();
      String[] parts = data.split(",");

      //process the data
      int start = permutations ? 1 : 2;
      for(int i=start; i < parts.length; i++) {
        
        //get the set numbers
        Partition p = new Partition(N);
        String[] nums = parts[i].strip().split(" ");
        
        //add numbers to the partition
        for(int j = 0; j < nums.length; j++) {
          p.addNumber(Integer.parseInt(nums[j]));
        }
        
        //add partition to list
        partitions.add(p);
      }
      
    }catch(Exception e) { //handle exception if anything goes wrong
      e.printStackTrace();
    }
    
    return partitions;
  }
  
  /**
   * Compares the two lists of partitions based with respect to if the order of numbers matters
   * or not.
   * @param result the actual return value of the method being tested 
   * @param expected the expected return value of the method being testes
   * @param orderMatters true if testing for permutations, false otherwise
   * @return true if the list of partitions are exactly the same with respect to the value 
   *    of orderMatters, false otherwise
   */
  public static boolean comparePartitionLists(ArrayList<Partition> result
      ,ArrayList<Partition> expected,boolean orderMatters){
    
      //set partition data field to be the proper one
      Partition.orderMatters = orderMatters;
      
      Collections.sort(result);
      Collections.sort(expected);
      
      //result has all partitions and same number
      if(result.equals(expected))
        return true;
      else {
        //figure out the ones missing from result
        expected.removeAll(result);
        System.out.println("Result is missing the following " + expected.size() + " partition(s):");
        
        //print the partitions missing from the result
        for(Partition p : expected) {
          System.out.println("\t" + p.toString());
        }
        
        return false;
      }
  }
}
