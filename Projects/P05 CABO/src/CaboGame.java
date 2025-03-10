//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
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

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * The CaboGame class implements the main game logic for the card game CABO. It manages the deck,
 * discard pile, players, game state, and user interactions.
 */
public class CaboGame extends PApplet {

  private Deck deck;
  private Deck discard;
  private BaseCard drawnCard;

  private Player[] players;
  private int currentPlayer;
  private int caboPlayer;
  private boolean gameOver;
  private int selectedCardFromCurrentPlayer;

  private Button[] buttons;


  /**
   * Enum representing the different action states in the game (e.g., swapping cards, peeking,
   * spying, switching).
   *
   * This allows us to easily restrict the possible values of a variable.
   */
  private enum ActionState {
    NONE, SWAPPING, PEEKING, SPYING, SWITCHING
  }


  private ActionState actionState = ActionState.NONE;

  // provided data fields for tracking the players' moves through the game
  private ArrayList<String> gameMessages = new ArrayList<>();

  /**
   * Launch the game window; PROVIDED. Note: the argument to PApplet.main() must match the name of
   * this class, or it won't run!
   *
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("CaboGame");
  }

  /**
   * Sets up the initial window size for the game; PROVIDED.
   */
  @Override
  public void settings() {
    size(1000, 800);
  }

  /**
   * Sets up the game environment, including the font, game state, and game elements.
   */
  @Override
  public void setup() {

    textFont(createFont("Arial", 16));
    // setProcessing for the classes which require it
    BaseCard.setProcessing(this);
    Deck.setProcessing(this);
    Button.setProcessing(this);

    deckCheck();

    // set up deck and discard pile
    this.deck = new Deck(Deck.createDeck());
    this.discard = new Deck(new ArrayList<BaseCard>());
    this.drawnCard = null;

    // set up players array and deal their cards
    this.players = new Player[4];
    players[0] = new Player("Cyntra", 0, false);
    players[1] = new AIPlayer("Avalon", 1, true);
    players[2] = new AIPlayer("Balthor", 2, true);
    players[3] = new AIPlayer("Ophira", 3, true);

    this.currentPlayer = 0;
    this.caboPlayer = -1;

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < this.players.length; j++) {
        this.players[j].addCardToHand(this.deck.drawCard());
      }
    }

    this.players[0].getHand().setFaceUp(0, true);
    this.players[0].getHand().setFaceUp(1, true);


    // set up buttons and update their states for the beginning of the game
    buttons = new Button[5];
    buttons[0] = new Button("Draw from Deck", 50, 700, 150, 40);
    buttons[1] = new Button("Swap a card", 220, 700, 150, 40);
    buttons[2] = new Button("Declare Cabo", 390, 700, 150, 40);
    buttons[3] = new Button("Use Action", 390 + 170, 700, 150, 40);
    buttons[4] = new Button("End Turn", 390 + 170 + 170, 700, 150, 40);

    // update the gameMessages log: "Turn for "+currentPlayer.name
    this.setGameStatus("Turn for " + this.players[currentPlayer].getName());

    updateButtonStates();
  }

  /**
   * Console-only output for verifying the setup of the card objects and the deck containing them
   */
  public void deckCheck() {

    ArrayList<BaseCard> deckCheck = Deck.createDeck();

    // verify that there are 52 cards in the deck
    if (deckCheck.size() == 52) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }
    // verify that there are 8 of each type of ActionCard
    int peekCount = 0, spyCount = 0, switchCount = 0;

    for (BaseCard card : deckCheck) {
      if (card instanceof ActionCard) {
        ActionCard actionCard = (ActionCard) card;
        switch (actionCard.getActionType()) {
          case "peek":
            peekCount++;
            break;
          case "spy":
            spyCount++;
            break;
          case "switch":
            switchCount++;
            break;
        }
      }
    }

    if (peekCount == 8 && spyCount == 8 && switchCount == 8) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }

    // verify that there are 13 of each suit
    int heartsCount = 0, diamondsCount = 0, clubsCount = 0, spadesCount = 0;

    for (BaseCard card : deckCheck) {
      switch (card.suit) {
        case "Hearts":
          heartsCount++;
          break;
        case "Diamonds":
          diamondsCount++;
          break;
        case "Clubs":
          clubsCount++;
          break;
        case "Spades":
          spadesCount++;
          break;
      }
    }

    if (heartsCount == 13 && diamondsCount == 13 && clubsCount == 13 && spadesCount == 13) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }

    // verify that the king of diamonds' getRank() returns -1
    int res = 0;
    for (BaseCard card : deckCheck) {
      if (card.suit.equals("Diamonds") && card.rank == 13) {
        res = card.getRank();
        break;
      }
    }

    if (res == -1) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }
  }



  /**
   * Updates the state of the action buttons based on the current game state. Activates or
   * deactivates buttons depending on whether it's the start of a player's turn, a card has been
   * drawn, or the player is an AI.
   */
  public void updateButtonStates() {
    // if the current player is a computer, deactivate all buttons
    if (players[currentPlayer].isComputer()) {
      for (Button button : buttons) {
        button.setActive(false);
      }
    } else {
      if (drawnCard == null) {

        // If no card has been drawn, activate Draw from Deck and Declare Cabo
        buttons[0].setActive(true);

        // Declare Cabo (only if no one has declared)
        buttons[2].setActive(caboPlayer == -1);
        buttons[1].setActive(false);
        buttons[3].setActive(false);
        buttons[4].setActive(false);
      } else {
        // if a card has been drawn
        buttons[0].setActive(false);
        buttons[2].setActive(false);
        buttons[1].setActive(true);
        buttons[4].setActive(true);

        if (drawnCard instanceof ActionCard) {
          buttons[3].setActive(true);
          buttons[3].setLabel(((ActionCard) drawnCard).getActionType().toUpperCase());
        } else {
          buttons[3].setActive(false);
        }
      }
    }
  }

  /**
   * Renders the graphical user interface; also handles some game logic for the computer players.
   */
  @Override
  public void draw() {
    background(0, 128, 0);

    // draw the deck and discard pile
    this.deck.draw(500, 80, false);
    this.discard.draw(600, 80, true);

    textSize(16);
    fill(255);
    text("Deck:", 520, 60);
    text("Discard Pile:", 644, 60);

    // draw the players' hands
    for (int i = 0; i < this.players.length; i++) {
      text(this.players[i].getName(), 50, 45 + 150 * i);
      this.players[i].getHand().draw(60 + 150 * i);
    }
    // draw the buttons
    for (Button button : buttons) {
      button.draw();
    }
    // show the drawn card, if there is one
    if (drawnCard != null) {
      drawnCard.setFaceUp(true);
      drawnCard.draw(500, 500);
    }

    // Display game messages with different colors based on the content
    int y = 200;
    for (String message : gameMessages) {
      textSize(16);
      if (message.contains("CABO")) {
        fill(255, 128, 0);
      } else if (message.contains("switched")) {
        fill(255, 204, 153);
      } else if (message.contains("spied")) {
        fill(255, 229, 204);
      } else {
        fill(255);
      }
      text(message, width - 300, y); // Adjust x-position as needed
      y += 20; // Spacing between messages
    }
    // if the game is over, display the game over status
    if (gameOver) {
      displayGameOver();
    }

  }

  /**
   * Handles mouse press events during the game. It manages user interactions with buttons (that is,
   * drawing a card, declaring CABO, swapping cards, using action cards) and updates the game state
   * accordingly.
   */
  @Override
  public void mousePressed() {
    // if game is over or it's the computer's turn, do nothing
    if (gameOver || players[currentPlayer].isComputer()) {
      return;
    }
    // handle button clicks
    for (Button button : buttons) {
      if (button.isActive() && button.isMouseOver()) {
        if (button == buttons[0]) {
          // Draw from deck
          drawFromDeck();
        } else if (button == buttons[1]) {
          // Swap a card
          actionState = ActionState.SWAPPING;
          setGameStatus("Click a card in your hand to swap it with the drawn card.");

        } else if (button == buttons[2]) {
          // declare cabo
          declareCabo();
        } else if (button == buttons[3]) {

          String actionType = ((ActionCard) drawnCard).getActionType().toUpperCase();
          switch (actionType) {
            case "PEEK":
              actionState = ActionState.PEEKING;
              break;
            case "SPY":
              actionState = ActionState.SPYING;
              break;
            case "SWITCH":
              actionState = ActionState.SWITCHING;
              break;
            default:
              throw new IllegalArgumentException("Unknown action type: " + actionType);
          }
          switch (actionState) {
            case PEEKING:
              setGameStatus("Click a card in your hand to peek at it");
              break;
            case SPYING:
              setGameStatus("Click a card in another player's hand to spy on it");
              break;
            case SWITCHING:
              setGameStatus(
                  "Click a card from your hand, then a card from another player's hand to switch.");
              break;
          }

          buttons[3].setLabel("Use Action");
          selectedCardFromCurrentPlayer = -1;
        } else if (button == buttons[4]) {
          // End turn
          nextTurn();
        }
        updateButtonStates();
        return;
      }
    }

    // handle additional action states
    switch (actionState) {
      case SWAPPING -> handleCardSwap();
      case PEEKING -> handlePeek();
      case SPYING -> handleSpy();
      case SWITCHING -> handleSwitch();
      default -> { /* No action to be taken */ }
    }
  }

  ///////////////////////////////////// BUTTON CLICK HANDLERS
  // ///////////////////////////////////

  /**
   * Handles the action of drawing a card from the deck. If the deck is empty, the game ends.
   * Otherwise, the drawn card is added to the current player's hand. The game status and button
   * states are updated accordingly.
   */
  public void drawFromDeck() {
    if (deck.isEmpty()) {
      gameOver = true;
      setGameStatus("The deck is empty. Game over.");
      return;
    }
    drawnCard = deck.drawCard();
    String message = players[currentPlayer].getName() + " drew a card.";
    setGameStatus(message);
    gameMessages.add(message);
    updateButtonStates();
  }

  /**
   * Handles the action of declaring CABO. Updates the game status to show that the player has
   * declared CABO.
   */
  public void declareCabo() {
    if (caboPlayer == -1) {
      caboPlayer = currentPlayer;
      gameMessages.add(players[currentPlayer].getName() + " declared CABO!");
      nextTurn();
    }
  }

  ///////////////////////////////////// ACTION STATE HANDLERS
  // ///////////////////////////////////

  /**
   * This method runs when the human player has chosen to SWAP the drawn card with one from their
   * hand. Detect if the mouse is over a card from the currentPlayer's hand and, if it is, swap the
   * drawn card with that card.
   *
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does
   * nothing.
   */
  public void handleCardSwap() {
    Player player = players[currentPlayer];
    int index = player.getHand().indexOfMouseOver();
    if (index != -1) {
      BaseCard swappedCard = player.getHand().swap(drawnCard, index);
      discard.addCard(swappedCard);
      setGameStatus("Swapped the drawn card with card " + (index + 1) + " in the hand.");
      drawnCard = null;
      actionState = ActionState.NONE;
      updateButtonStates();
    }
  }

  /**
   * Handles the action of peeking at one of your cards. The player selects a card from their own
   * hand, which is then revealed (set face-up).
   *
   * If the mouse is not currently over a card from the currentPlayer's hand, this method does
   * nothing.
   */
  public void handlePeek() {
    Player player = players[currentPlayer];
    int index = player.getHand().indexOfMouseOver();
    if (index != -1) {
      player.getHand().setFaceUp(index, true);
      setGameStatus("Revealed card " + (index + 1) + " in the hand.");
      discard.addCard(drawnCard);
      drawnCard = null;
      actionState = ActionState.NONE;
      updateButtonStates();
    }
  }

  /**
   * Handles the spy action, allowing the current player to reveal one of another player's cards.
   * The current player selects a card from another player's hand, which is temporarily revealed.
   *
   * If the mouse is not currently over a card from another player's hand, this method does
   * nothing.
   */
  public void handleSpy() {
    for (Player player : players) {
      if (player == players[currentPlayer])
        continue;
      int index = player.getHand().indexOfMouseOver();
      if (index != -1) {
        player.getHand().setFaceUp(index, true);
        setGameStatus("Spied on " + player.getName() + "'s card.");
        discard.addCard(drawnCard);
        drawnCard = null;
        actionState = ActionState.NONE;
        updateButtonStates();
        return;
      }
    }
  }


  /**
   * Handles the switch action, allowing the current player to switch one of their cards with a card
   * from another player's hand.
   *
   * This action is performed in 2 steps, in this order: (1) select a card from the current player's
   * hand (2) select a card from another player's hand
   *
   * If the mouse is not currently over a card, this method does nothing.
   */
  public void handleSwitch() {
    Player currentPlayer = players[this.currentPlayer];
    if (selectedCardFromCurrentPlayer == -1) {
      int index = currentPlayer.getHand().indexOfMouseOver();
      if (index != -1) {
        selectedCardFromCurrentPlayer = index;
        setGameStatus(
            "Selected card " + (index + 1) + " from your hand. Now select a card from another " + "player's hand.");
      }
    } else {
      for (Player player : players) {
        if (player == currentPlayer)
          continue;
        int index = player.getHand().indexOfMouseOver();
        if (index != -1) {
          currentPlayer.getHand()
              .switchCards(selectedCardFromCurrentPlayer, player.getHand(), index);
          setGameStatus("Switched a card with " + player.getName());
          discard.addCard(drawnCard);
          drawnCard = null;
          actionState = ActionState.NONE;
          selectedCardFromCurrentPlayer = -1;
          updateButtonStates();
          return;
        }
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Advances the game to the next player's turn. Hides all players' cards, updates the current
   * player, checks for game-over conditions, resets action states, and updates the UI button states
   * for the new player's turn.
   */
  public void nextTurn() {
    for (Player player : players) {
      for (int i = 0; i < player.getHand().size(); i++) {
        player.getHand().setFaceUp(i, false);
      }
    }
    if (drawnCard != null) {
      discard.addCard(drawnCard);
      drawnCard = null;
    }
    currentPlayer = (currentPlayer + 1) % players.length;
    if (currentPlayer == caboPlayer) {
      gameOver = true;
      displayGameOver();
    } else {
      setGameStatus("Turn for " + players[currentPlayer].getName());
      actionState = ActionState.NONE;
      updateButtonStates();
      System.out.println("Updated button states.");

      if (players[currentPlayer].isComputer() && players[currentPlayer] instanceof AIPlayer) {
        System.out.println("Current player is an AI player: " + players[currentPlayer].getName());
        performAITurn();
      } else {
        System.out.println(
            "Current player is not an AI player: " + players[currentPlayer].getName());
      }
    }
  }

  /**
   * Displays the game-over screen and reveals all players' cards. The method calculates each
   * player's score, identifies the winner, and displays a message about the game's result,
   * including cases where there is no winner.
   *
   * We've provided the code for the GUI parts, but the logic behind this method is still TODO
   */
  public void displayGameOver() {
    // Create a dimmed background overlay
    fill(0, 0, 0, 200);
    rect(0, 0, width, height);
    fill(255);
    textSize(32);
    textAlign(CENTER, CENTER);
    text("Game Over!", (float) width / 2, (float) height / 2 - 150);

    // reveal all players' cards
    for (Player player : players) {
      Hand hand = player.getHand();
      for (int i = 0; i < hand.size(); i++) {
        hand.setFaceUp(i, true);
      }
    }
    // calculate and display each player's score
    int yPosition = height / 2 - 100;
    textSize(24);
    int[] scores = new int[players.length];
    for (int i = 0; i < players.length; i++) {
      scores[i] = players[i].getHand().calcHand();
      text(players[i].getName() + "'s score: " + scores[i], (float) width / 2, yPosition);
      yPosition += 30;
    }

    // Determine the winner
    int minScore = Integer.MAX_VALUE;
    String winner = null;
    boolean tie = false;
    for (int i = 0; i < players.length; i++) {
      if (scores[i] < minScore) {
        minScore = scores[i];
        winner = players[i].getName();
        tie = false;
      } else if (scores[i] == minScore) {
        tie = true;
      }
    }

    // display this message if there is no winner
    yPosition += 30;
    if (tie) {
      text("No Winner. The war starts.", (float) width / 2, yPosition);
    } else {
      // display this message if there is a winner
      text("Winner: " + winner, (float) width / 2, yPosition);
    }
  }

  /**
   * PROVIDED: Sets the current game status message and updates the message log. If the message log
   * exceeds a maximum number of messages, the oldest message is removed.
   *
   * @param message the message to set as the current game status.
   */
  private void setGameStatus(String message) {
    gameMessages.add(message);
    int MAX_MESSAGES = 15;
    if (gameMessages.size() > MAX_MESSAGES) {
      gameMessages.remove(0); // Remove the oldest message
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // The 2 methods below this line are PROVIDED in their entirety to run the AIPlayer
  // interactions
  // with the CABO game. Uncomment them once you are ready to add AIPlayer actions to your game!
  /////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Performs the AI player's turn by drawing a card and deciding whether to swap, discard, or use
   * an action card. If the AI player draws a card that is better than their highest card, they swap
   * it; otherwise, they discard it. If the drawn card is an action card, the AI player performs the
   * corresponding action. If the AI player's hand value is low enough, they may declare CABO.
   */
  private void performAITurn() {
    AIPlayer aiPlayer = (AIPlayer) players[currentPlayer];
    String gameStatus = aiPlayer.getName() + " is taking their turn.";
    setGameStatus(gameStatus);

    // Draw a card from the deck
    drawnCard = deck.drawCard();
    if (drawnCard == null) {
      gameOver = true;
      return;
    }

    gameStatus = aiPlayer.getName() + " drew a card.";
    setGameStatus(gameStatus);

    // Determine if AI should swap or discard
    int drawnCardValue = drawnCard.getRank();
    int highestCardIndex = aiPlayer.getHighestIndex();
    if (highestCardIndex == -1) {
      highestCardIndex = 0;
    }
    int highestCardValue = aiPlayer.getHand().getRankAtIndex(highestCardIndex);

    // Swap if the drawn card has a lower value than the highest card in hand
    if (drawnCardValue < highestCardValue) {
      BaseCard cardInHand = aiPlayer.getHand().swap(drawnCard, highestCardIndex);
      aiPlayer.setCardKnowledge(aiPlayer.getLabel(), highestCardIndex, true);
      discard.addCard(cardInHand);
      gameStatus =
          aiPlayer.getName() + " swapped the drawn card with card " + (highestCardIndex + 1) + " "
              + "in their hand.";

      setGameStatus(gameStatus);
    } else if (drawnCard instanceof ActionCard) {
      // Use the action card
      String actionType = ((ActionCard) drawnCard).getActionType();
      gameStatus = aiPlayer.getName() + " uses an action card: " + actionType;
      setGameStatus(gameStatus);
      performAIAction(aiPlayer, actionType);
      discard.addCard(drawnCard);
    } else {
      // Discard the drawn card
      discard.addCard(drawnCard);
      gameStatus = aiPlayer.getName() + " discarded the drawn card: " + drawnCard;
      setGameStatus(gameStatus);
    }

    // AI may declare Cabo if hand value is low enough
    int handValue = aiPlayer.calcHandBlind();
    if (handValue <=

        random(13, 21) && caboPlayer == -1) {
      declareCabo();
    }

    // Prepare for the next turn
    drawnCard = null;
    System.out.println(aiPlayer.getName() + " completed their turn.");

    nextTurn();
  }

  /**
   * Performs the specified action for the AI player based on the drawn action card. Actions include
   * peeking at their own cards, spying on another player's card, or switching cards with another
   * player.
   *
   * @param aiPlayer   the AI player performing the action.
   * @param actionType the type of action to perform ("peek", "spy", or "switch").
   */
  private void performAIAction(AIPlayer aiPlayer, String actionType) {
    Player otherPlayer = players[0]; // Assuming Player 1 is the human player
    String gameStatus = "";
    switch (actionType) {
      case "peek" -> {
        // AI peeks at one of its own cards
        int unknownCardIndex = aiPlayer.getUnknownCardIndex();
        if (unknownCardIndex != -1) {
          aiPlayer.setCardKnowledge(aiPlayer.getLabel(), unknownCardIndex, true);
          gameStatus = aiPlayer.getName() + " peeked at their card " + (unknownCardIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "spy" -> {
        // AI spies on one of the human player's cards
        int spyIndex = aiPlayer.getSpyIndex();
        if (spyIndex != -1) {
          aiPlayer.setCardKnowledge(0, spyIndex, true);
          gameStatus = aiPlayer.getName() + " spied on Player 1's card " + (spyIndex + 1);
          setGameStatus(gameStatus);
        }
      }
      case "switch" -> {
        // AI switches one of its cards with one of the human player's cards
        int aiCardIndex = aiPlayer.getHighestIndex();
        if (aiCardIndex == -1) {
          aiCardIndex = (int) random(aiPlayer.getHand().size());
        }
        int otherCardIndex = aiPlayer.getLowestIndex(otherPlayer);
        if (otherCardIndex == -1)
          otherCardIndex = (int) random(otherPlayer.getHand().size());

        // Swap the cards between AI and the human player
        aiPlayer.getHand().switchCards(aiCardIndex, otherPlayer.getHand(), otherCardIndex);
        boolean preCardKnowledge = aiPlayer.getCardKnowledge(aiPlayer.getLabel(), aiCardIndex);
        aiPlayer.setCardKnowledge(aiPlayer.getLabel(), aiCardIndex,
            aiPlayer.getCardKnowledge(0, otherCardIndex));

        aiPlayer.setCardKnowledge(0, otherCardIndex, preCardKnowledge);

        gameStatus = aiPlayer.

            getName() + " switched card " + (aiCardIndex + 1) + " with " + otherPlayer.

            getName() + "'s " + (otherCardIndex + 1) + ".";

        setGameStatus(gameStatus);
      }
    }
  }

}
