package liars.language;

import liars.Bid;
import liars.Die;
import liars.Player;
import liars.Players;

public interface Language {
    String settingUpLanguage();

    String onesWild();


    String invalidChoiceError();

    String startGame();

    String gameMenu();


    String gameState(int round, Players players);

    String gameStateWithBid(int round, Players players, Bid lastBid);

    String makeInitialBid();


    String makeQuantityBid();

    String makeFaceValueBid();

    String bidSummary(Bid bid);


    String nextTurn(Player player);


    String challengeMade(Bid bid);

    String challengeCount(String name, Die[] dice, int count);

    String totalCount(int countValue);

    String challengeResult(boolean valid, String name);

    String removeLosers();

    String diceRerolled(int round);


    String endGame(Player winner);

    String enterPlayerName();

    String enterAgentNames();
}
