public class Game {

  private String name;
  private int winningScore = 1;
  private int losingScore = 0;
  private Leaderboard scoreboard = new Leaderboard();

  // A parameter that determines how quickly a rating can change.
  private int k = 30;

  public Game(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.name = name;
  }

  public Game(String name, int winningScore, int losingScore, int k) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    this.name = name;
    this.winningScore = winningScore;
    this.losingScore = losingScore;
    this.k = k;
  }

  /*
   * Returns the expected ELO of p1 before the winner is determined.
   */
  private double expectedScore(Player p1, Player p2) {
    return 1.0 / (1 + Math.pow(10, (p2.getScore() - p1.getScore()) / 400.0));
  }

  /**
   * Updates the ELO of both players assuming p1 is the winner.
   * 
   * @param p1
   * @param p2
   */
  public void updateScores(Player p1, Player p2) {

    // Calculate expected scores
    double expectedA = expectedScore(p1, p2);
    double expectedB = expectedScore(p2, p1);

    // Update ratings based on actual scores and expected scores
    p1.setScore((int) (p1.getScore() + k * (winningScore - expectedA)));
    p2.setScore((int) (p2.getScore() + k * (losingScore - expectedB)));
  }

  public void addPlayer(Player player) {
    scoreboard.addPlayer(player);
  }

  public Leaderboard getScoreboard() {
    return this.scoreboard;
  }


  public void challenge(String name1, String name2, boolean win) {
    Player p1 = scoreboard.lookup(name1);
    Player p2 = scoreboard.lookup(name2);
    
    if (p1 == null || p2 == null) {
      System.out.println("ERROR: "+name1+" unable to challenge "+name2);
      return;
    }
    System.out.println(name1+" is challenging "+name2+"!");

    challengeHelper(p1, p2, win);
  }

  public void challengeNext(String name, boolean win) {
    Player p1 = scoreboard.lookup(name);
    Player p2 = scoreboard.next(p1);
    
    if (p1 == null || p2 == null) {
      System.out.println("ERROR: "+name+" unable to challenge next player");
      return;
    }
    System.out.println(name+" is challenging "+p2.getName()+"!");

    challengeHelper(p1, p2, win);
  }

  private void challengeHelper(Player p1, Player p2, boolean win) {
    scoreboard.removePlayer(p1);
    scoreboard.removePlayer(p2);

    if (win) {
      updateScores(p1, p2);
    } else {
      updateScores(p2, p1);
    }

    scoreboard.addPlayer(p1);
    scoreboard.addPlayer(p2);
  }
  
  public String toString() {
    String toReturn = "\n=== "+this.name+" ===\n\n";
    toReturn += scoreboard.prettyPrint()+"\n";
    toReturn += scoreboard.toString()+"\n";
    return toReturn;
  }

  public static void main(String[] args) {
    Game game = new Game("Chess");
    game.addPlayer(new Player("William", 1400));
    game.addPlayer(new Player("Jing", 1300));
    game.addPlayer(new Player("Hobbes", 1500));
    game.addPlayer(new Player("Caleb", 1400));
    game.addPlayer(new Player("Ina", 1600));
    game.addPlayer(new Player("Michelle", 1500));
    System.out.println(game);

    game.challengeNext("William", true);
    game.challenge("Jing", "Hobbes", false);
    System.out.println(game);

    game.challenge("Ina", "Caleb", false);
    game.challengeNext("Michelle", true);
    System.out.println(game);
  }
}
