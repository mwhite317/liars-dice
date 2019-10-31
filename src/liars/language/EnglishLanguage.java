package liars.language;

import liars.Bid;
import liars.Die;
import liars.Player;
import liars.Players;

import java.util.Arrays;

public class EnglishLanguage implements Language {

    public String settingUpLanguage() {
        return "Setting up language...";
    }

    public String enterPlayerName() {
        return "Enter the name of a Human Player " +
                "(or Q to stop generating human players): ";
    }

    public String enterAgentNames() {
        return "Enter the name of a AI Player (or R to randomise name or Q to stop generating Ai players): ";
    }


    public String invalidChoiceError() {
        return "Choice is invalid! Please choose again...";
    }

    public String startGame() {
        return "Starting a game of Liar's Dice!";
    }

    public String onesWild() {
        return "Remember 1s are wild!";
    }

    public String gameMenu() {
        return "Bid (Quantity & Face Value) or Challenge:" +
                "\n1. Bid" +
                "\n2. Challenge" +
                "\nPick one of the options above: ";
    }


    public String gameState(int round, Players players) {
        String playerList = "";

        for (Player player : players) {
            playerList += player.toString() + " dice, ";

        }
        return "Round #" + round + " [" +
                playerList.substring(0, playerList.length() - 2) + "]";
    }

    public String gameStateWithBid(int round, Players players, Bid lastBid) {
        return gameState(round, players) +
                " Last Bid: [" + lastBid.getQuantity() +
                " dice of Face Value " + lastBid.getFaceValue() +
                "]";
    }

    public String makeInitialBid() {
        return "Make a bid (Quantity & FaceValue)";
    }


    public String makeQuantityBid() {
        return "Quantity: ";
    }

    public String makeFaceValueBid() {
        return "Face Value: ";
    }

    public String bidSummary(Bid bid) {
        return "Bid made " + bid.getQuantity() + " of Face Value " + bid.getFaceValue();
    }


    public String nextTurn(Player player) {
        return "Turn for " + player.getName();
    }


    public String challengeMade(Bid bid) {
        return "Challenge made on [" + bid.getQuantity() +
                " dice of Face Value " + bid.getFaceValue() +
                "]...all dice shown:";
    }

    public String challengeCount(String name, Die[] dice, int count) {
        return name + Arrays.toString(dice)
                + " Count = " + count;
    }

    public String totalCount(int countValue) {
        return "Total Count = " + countValue;
    }

    public String challengeResult(boolean valid, String name) {
        return "Challenge " + (valid ? "valid" : "invalid") + "; Round Loser is " + name;
    }

    public String removeLosers() {
        return "Removing losers, who will go to the next round...\n";
    }

    public String diceRerolled(int round) {
        return "Dice re-rolled after challenge; starting Round #" + round;
    }


    public String endGame(Player winner) {
        return "Game over. Winner is  " + winner.getName() + "!" +
                " Bye";
    }


}
