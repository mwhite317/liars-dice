package liars.agents;

import liars.Bid;

public interface GamePlayer {
    String getPlayerChoice();

    Bid getPlayerBid(Bid lastBid, int numberOfDice);

}
