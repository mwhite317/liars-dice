package liars;

import java.util.ArrayList;
import java.util.List;

/**
 * Track players and bids for a game of Liars Dice
 * @author markwhite
 * @version 1.0
 */
public class Game {
    private int round = 0;
    private int turnNumber = 0;
    private Player lastRoundLoser;
    private Players players;
    private List<Bid> bidsForRound = new ArrayList<>();

    /**
     * Create a game
     *
     * @param players players in the game
     */
    public Game(Player[] players) {
        this.players = new Players(players);
    }

    /**
     * Start the game
     *
     * @param numberDice number of dice each player begins with
     */
    public void start(int numberDice) {
        for (Player player : players) {
            player.startGame(numberDice);
        }
    }

    /**
     * Handle making of a new bid
     *
     * @param bid the bid to make
     * @return true if bid is legal to make; false otherwie
     */
    public boolean makeBid(Bid bid) {
        if (bidsForRound.size() == 0 || getLastBid().isOtherBigger(bid)) {
            turnNumber++;
            bidsForRound.add(bid);
            return true;
        }
        return false;
    }

    /**
     * Handle challenge made
     *
     * @return true if challenge valid; false otherwise
     */
    public boolean makeChallenge() {
        int countValue = 0;
        int challengeFaceValue = getLastBid().getFaceValue();
        for (Player player : players) {
            countValue += Die.count(player.getDice(), challengeFaceValue);

        }
        boolean successChallenge = (countValue == getLastBid().getQuantity());
        updateGameStateForEndOfRound(successChallenge);

        return successChallenge;
    }


    private void updateGameStateForEndOfRound(boolean successChallenge) {
        updateLastRoundLoser(successChallenge);
        lastRoundLoser.loseDie();

        bidsForRound = new ArrayList<>();

        round++;
    }

    private void updateLastRoundLoser(boolean challengeIsValid) {
        if (!challengeIsValid) {
            lastRoundLoser = getCurrentPlayer();
            turnNumber = players.getTurnNumberForPlayer(lastRoundLoser);
        } else {
            lastRoundLoser = players.getPreviousPlayer(getCurrentPlayer());
            turnNumber = players.getTurnNumberForPlayer((lastRoundLoser));
        }
    }

    /**
     * Indicates whether game underway
     *
     * @return true if game started and not over
     */
    public boolean isPlaying() {
        return players.numberOfPlayers() != 1;

    }

    public void removeLosers() {
        players.removeDicelessPlayers();
    }

    public int totalNumberOfDice() {
        int numberDice = 0;
        for (Player player : players) {
            numberDice += player.getDice().length;
        }
        return numberDice;
    }


    public Players getPlayers() {
        return players;
    }

    /**
     * Get the player who is to make the next play
     *
     * @return player
     */
    public Player getCurrentPlayer() {
        return players.getPlayerForTurn(turnNumber);

    }

    /**
     * Returns
     *
     * @return Player who won game
     */
    public Player getWinner() {
        for (Player player : players) {
            if (!(player.getDice().length == 0)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Getter of loser of previous round
     *
     * @return Player that lost round
     */
    public Player getLastRoundLoser() {
        return lastRoundLoser;

    }

    /**
     * Getter of current game Round
     *
     * @return Round number
     */
    public int getRound() {
        return round;
    }

    /**
     * Fast bid that was made
     *
     * @return last bid that was made
     */
    public Bid getLastBid() {
        if (bidsForRound.isEmpty()) {
            return null;
        }
        return bidsForRound.get(bidsForRound.size() - 1);
    }

}
