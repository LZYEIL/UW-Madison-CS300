import java.util.Arrays;
import java.util.Random;

/**
 * This class models Pod objects for the CS300 Hyperloop project.
 * 
 * Note: ALL METHODS except the constructor will throw a MalfunctioningPodException
 * if this pod is not functioning!
 * 
 * @author hobbes >:3
 */
public class Pod {
  
  /**
   * Constant indicating that this is a first-class Pod, set to 0
   */
  public static final int FIRST = 0;
  
  /**
   * Constant indicating that this is an economy-class Pod, set to 1
   */
  public static final int ECONOMY = 1;
  
  /**
   * Random number generator to determine whether the Pod will malfunction
   */
  private static Random randGen = new Random();
  
  /**
   * Indicator of whether this Pod is first class or economy class
   */
  private int podClass;
  
  /**
   * A perfect-size array list of passenger names for this Pod; unoccupied
   * seats are indicated as null values
   */
  private String[] passengerList;
  
  /**
   * Indicator of whether this pod is currently functional; a false value
   * will cause all methods except equals/compareTo to throw a MalfunctioningPodException
   */
  private boolean isFunctional;
  
  /**
   * Constructs a new, functional Pod for the given number of passengers.
   * 
   * @param capacity the maximum number of passengers for this Pod
   * @param podClass the class of this Pod, either Pod.FIRST or Pod.ECONOMY
   * @throws IllegalArgumentException if capacity <= 0 or the provided podClass does not correspond
   * to either Pod.FIRST or Pod.ECONOMY
   */
  public Pod(int capacity, int podClass) {
    if (capacity <= 0 || (podClass != Pod.FIRST && podClass != Pod.ECONOMY)) 
      throw new IllegalArgumentException("Invalid argument "+capacity);
    passengerList = new String[capacity];
    this.podClass = podClass;
    isFunctional = true;
  }
  
  /**
   * Verifies whether this Pod is currently functional. Has a 1/20 chance of
   * causing this pod to malfunction.
   * @return {@code true} if this Pod is currently functional, {@code false} otherwise
   */
  public boolean isFunctional() {
    // if you roll a d20 and get a 1, that's a critical failure:
    if (randGen.nextInt(20) == 0) isFunctional = false;
    return isFunctional;
  }
  
  /**
   * For testing purposes, this method intentionally sets a Pod to be non-functional.
   */
  protected void setNonFunctional() {
    this.isFunctional = false;
  }
  
  /**
   * Returns the class of this Pod (Pod.FIRST or Pod.ECONOMY)
   * @return the class of this Pod (Pod.FIRST or Pod.ECONOMY)
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public int getPodClass() throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    return this.podClass;
  }
  
  /**
   * Returns the total number of passengers currently present in this Pod,
   * defined as the number of non-null elements in the passengerList
   * @return the total number of passengers currently present in the Pod
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public int getNumPassengers() throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    int total = 0;
    for (String s: passengerList) {
      if (s != null) total++;
    }
    return total;
  }
  
  /**
   * The total number of passengers who could be present in this Pod
   * @return the capacity of this Pod
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public int getCapacity() throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    return passengerList.length;
  }
  
  /**
   * Whether this Pod has reached its passenger capacity
   * @return {@code true} if this Pod is carrying all of the passengers that could
   *   fit into it, {@code false} if there is still more room
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public boolean isFull() throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    return passengerList.length == getNumPassengers();
  }
  
  /**
   * Adds a new passenger to this pod if there is room
   * @param name the name of the passenger to add to this Pod
   * @throws IllegalStateException if the Pod is full
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public void addPassenger(String name) throws MalfunctioningPodException {
    if (isFull()) throw new IllegalStateException("Pod is full.");
    for (int i=0; i<passengerList.length; i++) {
      if (passengerList[i] == null) {
        passengerList[i] = name;
        return;
      }
    }
  }
  
  /**
   * Removes a passenger from the Pod by name
   * @param name the name of the passenger to remove
   * @return {@code true} if the passenger was found and removed, {@code false} otherwise
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public boolean removePassenger(String name) throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    for (int i=0; i<passengerList.length; i++) {
      if (passengerList[i].equals(name)) {
        passengerList[i] = null;
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks whether a passenger by the given name is present on this Pod
   * @param name the passenger to find
   * @return {@code true} if the passenger was found, {@code false} otherwise
   * @throws MalfunctioningPodException if this pod is not functional
   */
  public boolean containsPassenger(String name) throws MalfunctioningPodException {
    if (!isFunctional) throw new MalfunctioningPodException("Oh no!");
    for (String s: passengerList) {
      if (s != null && s.equals(name)) return true;
    }
    return false;
  }
  
  /**
   * Compares all instance fields of this object to the provided object
   * @return {@code true} if o is a Pod with all instance fields equal to
   * this Pod's instance field values; {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pod)) return false;
    Pod p = (Pod) o;
    return Arrays.deepEquals(this.passengerList, p.passengerList) && 
        this.podClass == p.podClass && this.isFunctional == p.isFunctional;
  }
  
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(Arrays.toString(passengerList));

    if (!this.isFunctional()) {
        sb.append(" * ");
    }
    return sb.toString();
  }

}
