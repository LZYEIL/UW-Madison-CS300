/**
 * PROVIDED, but will not compile until the Player class has been created
 * 
 * The AIPlayer class represents an AI-controlled player in the Cabo game.
 * This class extends the Player class and adds additional functionality
 * for AI logic, such as tracking card knowledge and calculating a "blind" hand value.
 * 
 * @author Yiheng Su and Yikai Zhang
 */
public class AIPlayer extends Player {

  private final boolean[][] cardKnowledge; // Tracks whether the player knows the rank of a card in the game

  /**
   * Constructs an AIPlayer with the specified name, label, and computer status.
   * The AI player tracks knowledge of its own cards, marking the first two cards in its hand as known.
   *
   * @param name       the name of the player.
   * @param label      the player's label, which uniquely identifies them in the game.
   * @param isComputer true if the player is a computer-controlled AI, false otherwise.
   */
  public AIPlayer(String name, int label, boolean isComputer) {
    super(name, label, isComputer);

    // Initialize the card knowledge for all players; the AI knows its first two cards by default
    this.cardKnowledge = new boolean[4][4];
    this.cardKnowledge[label][0] = true; // The AI knows the first card in its hand
    this.cardKnowledge[label][1] = true; // The AI knows the second card in its hand
  }

  /**
   * Calculates the "blind" total value of the AI player's hand.
   * If the AI doesn't know the value of a card in its hand, it assumes an average value of 8 for that card.
   * Otherwise, it uses the actual rank of the known cards.
   *
   * @return the total value of the AI's hand, with unknown cards estimated at a value of 8.
   */
  public int calcHandBlind() {
    int total = 0;

    // Iterate over the cards in the player's hand
    for (int i = 0; i < 4; ++i) {
      // If the AI doesn't know the card's value, assume it has a value of 8
      if (!this.cardKnowledge[getLabel()][i]) {
        total += 8;
      } else {
        // Use the actual card value if known
        total += getHand().getRankAtIndex(i);
      }
    }

    return total;
  }

  /**
   * Returns the index of the first card in the AI's hand that is unknown.
   * The method checks the card knowledge array and identifies the first card
   * whose rank is unknown to the AI.
   *
   * @return the index of the first unknown card in the AI's hand, or -1 if all cards are known.
   */
  public int getUnknownCardIndex() {
    for (int i = 0; i < 4; ++i) {
      if (!cardKnowledge[getLabel()][i]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the index of the first card in the human player's hand that is unknown to the AI.
   * The AI can use this index for spying on an unknown card in the human player's hand.
   *
   * @return the index of the first unknown card in the human player's hand, or -1 if all cards are known.
   */
  public int getSpyIndex() {
    for (int i = 0; i < 4; ++i) {
      if (!cardKnowledge[0][i]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the index of the lowest-ranked card in the specified player's hand that is known to the AI.
   * The method searches for the card with the lowest rank in the player's hand where the AI has card knowledge.
   *
   * @param player the player whose hand is being evaluated.
   * @return the index of the lowest-ranked known card, or -1 if no cards are known.
   */
  public int getLowestIndex(Player player) {
    int lowestValue = 8; // Assuming 8 is the average or maximum value for unknown cards
    int lowestIndex = -1;
    for (int i = 0; i < 4; ++i) {
      if (cardKnowledge[0][i] && player.getHand().getRankAtIndex(i) < lowestValue) {
        lowestValue = player.getHand().getRankAtIndex(i);
        lowestIndex = i;
      }
    }
    return lowestIndex;
  }

  /**
   * Sets the knowledge of a specific card for the AI or player.
   * This method updates whether the AI or player knows the value of a card at the given index in a player's hand.
   *
   * @param label     the label of the player whose card knowledge is being updated.
   * @param index     the index of the card in the player's hand.
   * @param knowledge {@code true} if the AI or player knows the card's value, {@code false} otherwise.
   */
  public void setCardKnowledge(int label, int index, boolean knowledge) {
    cardKnowledge[label][index] = knowledge;
  }

  /**
   * Returns whether the AI or player knows the value of a specific card in a player's hand.
   *
   * @param label the label of the player whose card knowledge is being checked.
   * @param index the index of the card in the player's hand.
   * @return {@code true} if the AI or player knows the card's value, {@code false} otherwise.
   */
  public boolean getCardKnowledge(int label, int index) {
    return cardKnowledge[label][index];
  }

  /**
   * Returns the index of the highest-ranked card in the AI's hand that is known.
   * The method searches for the card with the highest rank in the AI's hand, considering only the cards that the AI knows.
   * If no card has a rank greater than or equal to 8, the method returns -1.
   *
   * @return the index of the highest-ranked known card in the AI's hand, or -1 if no suitable card is found.
   */
  public int getHighestIndex() {
    int highestValue = -1;
    int highestIndex = -1;

    // Loop through the AI's hand and find the highest-ranked known card
    for (int i = 0; i < 4; ++i) {
      if (cardKnowledge[getLabel()][i] && getHand().getRankAtIndex(i) >= highestValue) {
        highestValue = getHand().getRankAtIndex(i);
        highestIndex = i;
      }
    }

    // Return -1 if no card with rank >= 8 is found
    if (highestValue < 8) {
      highestIndex = -1;
    }

    return highestIndex;
  }
}