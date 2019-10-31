package liars.console;

import liars.Bid;
import liars.Game;
import liars.Player;

public interface Console {
    Bid getPlayerBid(Player player, Bid lastBid, int numberOfDice);

    String getPlayerChoice(Player player);


    void addDelay();


    void displayEnterPlayerNames();

    void displayStartGame();

    void displayOnesWild();

    void displaySettingUpLanguage();


    void displayGameState(Game game);

    void displayEndOfBidTurn(Game game, Bid bid);

    void displayRoundChoice();


    void displayInvalidChoiceError();

    void displayDice(String name, int[] dice2Ints);

    void displayRemovingLoser();


    void displayMakeInitialBid();


    void displayChallengeMade(Game game);

    void displayChallengeResults(boolean valid, Player player);

    void displayNextTurn(Game game);

    void displayDiceRerolled(int nextRound);


    void displayEndGame(Game game);


    void displayEnterAgentNames();

}
