package liars.language;

import liars.Bid;
import liars.Die;
import liars.Player;
import liars.Players;

import java.util.Arrays;

public class DutchLanguage implements Language {

    public String settingUpLanguage() {
        return "Instellen taal... ";
    }

    public String enterPlayerName() {
        return "Voer de naam in van een menselijke speler " +
                "(of Q om te stoppen met het genereren van menselijke spelers):";
    }

    public String enterAgentNames() {
        return "Voer de naam in van een AI Player " +
                "(of R om speler te randomiseren " +
                "of Q om te stoppen met het genereren van Ai-spelers):";
    }


    public String invalidChoiceError() {
        return "Keuze is ongeldig! Kies opnieuw...";
    }

    public String startGame() {
        return "Het starten van een spelletje blufpoker is!";
    }

    public String onesWild() {
        return "Remember 1s zijn wild!";
    }

    public String gameMenu() {
        return "Bid (hoeveelheid & nominale waarde) of Uitdaging:" +
                "\n1. Bod" +
                "\n2. Uitdaging" +
                "\nKies één van de bovenstaande opties: ";
    }


    public String gameState(int round, Players players) {
        String playerList = "";

        for (Player player : players) {
            playerList += player.toString() + " dobbelstenen, ";

        }
        return "Ronde #" + round + " [" +
                playerList.substring(0, playerList.length() - 2) + "]";
    }

    public String gameStateWithBid(int round, Players players, Bid lastBid) {
        return gameState(round, players) +
                " Laatste bod: [" + lastBid.getQuantity() +
                " dobbelstenen van nominale waarde " + lastBid.getFaceValue() +
                "]";
    }

    public String makeInitialBid() {
        return "Het doen van een bod (hoeveelheid & nominale waarde)";
    }


    public String makeQuantityBid() {
        return "Hoeveelheid: ";
    }

    public String makeFaceValueBid() {
        return "Nominale Waarde: ";
    }

    public String bidSummary(Bid bid) {
        return "Bod " + bid.getQuantity() + " met een nominale waarde " + bid.getFaceValue();
    }


    public String nextTurn(Player player) {
        return "Beurt voor " + player.getName();
    }


    public String challengeMade(Bid bid) {
        return "Hoeveelheid op [" + bid.getQuantity() +
                " dobbelstenen van nominale waarde " + bid.getFaceValue() +
                "]...alle dobbelstenen afgebeeld:";
    }

    public String challengeCount(String name, Die[] dice, int count) {
        return name + Arrays.toString(dice)
                + " tellen = " + count;
    }

    public String totalCount(int countValue) {
        return "Totaaltelling = " + countValue;
    }

    public String challengeResult(boolean valid, String name) {
        return "Hoeveelheid " + (valid ? "geldig" : "ongeldig") + "; Ronde verliezer is " + name;
    }

    public String removeLosers() {
        return "Uitbouwen van verliezers, die gaat naar de volgende ronde...\n";
    }

    public String diceRerolled(int round) {
        return "Dice hergewalste na uitdaging; starten ronde #" + round;
    }


    public String endGame(Player winner) {
        return "Einde oefening. Winnaar is  " + winner.getName() + "!" +
                " Tot ziens";
    }


}
