package liars.console;

import liars.Bid;
import liars.Die;
import liars.Game;
import liars.Player;
import liars.agents.GamePlayer;
import liars.language.EnglishLanguage;
import liars.language.Language;
import net.WebPoster;

import java.util.Scanner;

public class PosterConsole implements Console {
    private static Language taal = new EnglishLanguage();
    private static Scanner inDevice = new Scanner(System.in);
    TerminalConsole terminalConsole;
    private GamePlayer player;

    public PosterConsole(Language language) {
        taal = language;
        terminalConsole = new TerminalConsole(language);
    }

    public static String readString() {
        String line = inDevice.nextLine();
        return line;
    }


    public Bid getPlayerBid(Player player, Bid lastBid, int numberOfDice) {
        return terminalConsole.getPlayerBid(player, lastBid, numberOfDice);
    }

    public String getPlayerChoice(Player player) {
        return terminalConsole.getPlayerChoice(player);
    }


    public void addDelay() {
        terminalConsole.addDelay();

    }

    public void displayEnterPlayerNames() {
        terminalConsole.displayEnterPlayerNames();

    }


    public void displayStartGame() {
        terminalConsole.displayStartGame();
        WebPoster.postMessage(taal.startGame());
    }

    public void displayOnesWild() {
        terminalConsole.displayOnesWild();
        WebPoster.postMessage(taal.onesWild());
    }

    public void displaySettingUpLanguage() {
        terminalConsole.displaySettingUpLanguage();
        WebPoster.postMessage(taal.settingUpLanguage());
    }


    public void displayGameState(Game game) {
        terminalConsole.displayGameState(game);
        WebPoster.postMessage(taal.gameState(game.getRound(), game.getPlayers()));
    }

    public void displayEndOfBidTurn(Game game, Bid bid) {
        terminalConsole.displayEndOfBidTurn(game, bid);
        WebPoster.postMessage(taal.bidSummary(bid));
        WebPoster.postMessage(taal.gameStateWithBid(game.getRound(), game.getPlayers(), bid));
    }

    public void displayRoundChoice() {
        terminalConsole.displayRoundChoice();
        WebPoster.postMessage(taal.gameMenu());
    }


    public void displayInvalidChoiceError() {
        terminalConsole.displayInvalidChoiceError();
        WebPoster.postMessage(taal.invalidChoiceError());
    }

    public void displayDice(String name, int[] dice2Ints) {
        WebPoster.postDice(name, dice2Ints);

    }

    public void displayRemovingLoser() {
        terminalConsole.displayRemovingLoser();
        WebPoster.postMessage(taal.removeLosers());

    }


    public void displayMakeInitialBid() {
        terminalConsole.displayMakeInitialBid();
        WebPoster.postMessage(taal.makeInitialBid());

    }


    public void displayChallengeMade(Game game) {
        terminalConsole.displayChallengeMade(game);
        int countValue = 0;
        Bid bid = game.getLastBid();
        WebPoster.postMessage(taal.challengeMade(bid));
        for (Player player : game.getPlayers()) {
            int playerCount = Die.count(player.getDice(), game.getLastBid().getFaceValue());
            countValue += playerCount;
            WebPoster.postMessage(taal.challengeCount(player.getName(),
                    player.getDice(),
                    playerCount));
        }

        WebPoster.postMessage(taal.totalCount(countValue));


    }

    public void displayChallengeResults(boolean valid, Player player) {
        terminalConsole.displayChallengeResults(valid, player);
        WebPoster.postMessage(taal.challengeResult(valid, player.getName()));
    }

    public void displayNextTurn(Game game) {
        terminalConsole.displayNextTurn(game);
        WebPoster.postMessage(taal.nextTurn(game.getCurrentPlayer()));
    }

    public void displayDiceRerolled(int nextRound) {
        terminalConsole.displayDiceRerolled(nextRound);
        WebPoster.postMessage(taal.diceRerolled(nextRound));
    }


    public void displayEndGame(Game game) {
        terminalConsole.displayEndGame(game);
        WebPoster.postMessage(taal.endGame(game.getWinner()));
    }


    public void displayEnterAgentNames() {
        terminalConsole.displayEnterAgentNames();

    }

}
