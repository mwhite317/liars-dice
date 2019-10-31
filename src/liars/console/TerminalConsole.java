package liars.console;

import liars.Bid;
import liars.Die;
import liars.Game;
import liars.Player;
import liars.agents.BlackMirror;
import liars.agents.GamePlayer;
import liars.language.EnglishLanguage;
import liars.language.Language;

import java.util.Scanner;

public class TerminalConsole implements Console {
    private static Language taal = new EnglishLanguage();
    private static Scanner inDevice = new Scanner(System.in);
    private GamePlayer inputMethod;

    public TerminalConsole(Language language) {
        taal = language;
    }

    public static String readString() {
        String line = inDevice.nextLine();
        return line;
    }

    public static int readNumber() {
        while (true) {
            try {
                int number = inDevice.nextInt();
                return number;
            } catch (Exception e) {
                System.out.println(taal.invalidChoiceError());
            } finally {
                inDevice.nextLine();
            }
        }
    }


    public Bid getPlayerBid(Player player, Bid lastBid, int numberOfDice) {
        return player.getInputMethod().getPlayerBid(lastBid, numberOfDice);
    }

    public String getPlayerChoice(Player player) {
        return player.getInputMethod().getPlayerChoice();
    }


    public void addDelay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
    }

    public void displayEnterPlayerNames() {
        System.out.println(taal.enterPlayerName());

    }


    public void displayStartGame() {
        System.out.println(taal.startGame());
    }

    public void displayOnesWild() {
        System.out.println(taal.onesWild());
    }

    public void displaySettingUpLanguage() {
        System.out.println(taal.settingUpLanguage());
    }


    public void displayGameState(Game game) {
        System.out.println(taal.gameState(game.getRound(), game.getPlayers()));
    }

    public void displayEndOfBidTurn(Game game, Bid bid) {
        System.out.println(taal.bidSummary(bid));
        System.out.println(taal.gameStateWithBid(game.getRound(), game.getPlayers(), bid));
    }

    public void displayRoundChoice() {
        System.out.println(taal.gameMenu());
    }


    public void displayInvalidChoiceError() {
        if (!(inputMethod instanceof BlackMirror)) {
            System.out.println(taal.invalidChoiceError());
        }
    }

    public void displayDice(String name, int[] dice2Ints) {
        // Do nothing so players cannot cheat on others


    }

    public void displayRemovingLoser() {
        System.out.println(taal.removeLosers());

    }


    public void displayMakeInitialBid() {
        System.out.println(taal.makeInitialBid());

    }


    public void displayChallengeMade(Game game) {
        int countValue = 0;
        Bid bid = game.getLastBid();
        System.out.println(taal.challengeMade(bid));
        for (Player player : game.getPlayers()) {
            int playerCount = Die.count(player.getDice(), game.getLastBid().getFaceValue());
            countValue += playerCount;
            System.out.println(taal.challengeCount(player.getName(),
                    player.getDice(),
                    playerCount));
        }

        System.out.println(taal.totalCount(countValue));


    }

    public void displayChallengeResults(boolean valid, Player player) {
        System.out.println(taal.challengeResult(valid, player.getName()));
    }

    public void displayNextTurn(Game game) {
        System.out.println(taal.nextTurn(game.getCurrentPlayer()));
    }

    public void displayDiceRerolled(int nextRound) {
        System.out.println(taal.diceRerolled(nextRound));
    }


    public void displayEndGame(Game game) {
        System.out.println(taal.endGame(game.getWinner()));
    }


    public void displayEnterAgentNames() {
        System.out.println(taal.enterAgentNames());

    }

}
