import liars.Bid;
import liars.Die;
import liars.Game;
import liars.Player;
import liars.agents.*;
import liars.console.Console;
import liars.console.PosterConsole;
import liars.console.TerminalConsole;
import liars.language.DutchLanguage;
import liars.language.EnglishLanguage;
import liars.language.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Each Player starts the game with 5 dice
 * <p>
 * The game is played in Rounds. In each round, the dice are rolled and each Player looks at their Dice without showing the other players
 * <p>
 * The first player begins bidding, announcing any Face Value and the minimum number of dice that the player believes are showing that value, under all of the cups in the game.
 * <p>
 * Note ones are wild, they always count as having the Face Value of the current bid.
 * <p>
 * Turns rotate among the players in a clockwise order.
 * <p>
 * Each player has two choices during their turn: to make a higher bid, or challenge the previous bid—typically with a call of "liar". A higher bid is either the same Face Value and a larger quantity OR a higher Face Value with the same or a larger quantity.
 * <p>
 * If the current player challenges the previous bid, all dice are revealed. If the bid is valid (at least as many of the Face Value), the bidder wins the Round. Otherwise, the challenger wins. The player who loses a round loses one of their dice.
 * <p>
 * A player who loses all their dice is eliminated and sits out the remainder of the game.
 * <p>
 * The loser of the last round starts the bidding on the next round. If the loser of the last round was eliminated, the next player (after that player) starts the new round.
 * <p>
 * The last player to still retain a die (or dice) is the winner.
 *
 * @author markwhite
 * @version 1.0
 */
public class LiarsDiceMain {
    private static Game game;
    private static Console console;
    private static Language language;

    private static void playGame() {
        System.out.print("Select a language | Kies een taal :\n" +
                "1. English\n" +
                "2. Nederlands" +
                "\n:");
        int answer = TerminalConsole.readNumber();
        if (answer == 1) {
            setLanguage(new EnglishLanguage());

        } else if (answer == 2) {
            setLanguage(new DutchLanguage());
        }


        List<Player> playerList = addHumanPlayers(new ArrayList<>());
        playerList.addAll(addAgentPlayers(new ArrayList<>()));
        Player[] players = new Player[playerList.size()];
        for (int i = 0; i < playerList.size(); i++) {
            players[i] = playerList.get(i);
        }
        game = new Game(players);

        game.start(5);
        console.displayStartGame();
        console.displayOnesWild();

        while (game.isPlaying()) {
            playRound();
        }
        console.displayEndGame(game);
    }

    private static List<Player> addHumanPlayers(List<Player> playerList) {
        while (true) {
            console.displayEnterPlayerNames();
            String playerName = TerminalConsole.readString();
            if (playerName.equals("Q")) {
                break;
            }
            playerList.add(new Player(playerName, new HumanPlayer(language)));

        }
        return playerList;
    }

    private static List<Player> addAgentPlayers(List<Player> playerList) {
        while (true) {
            console.displayEnterAgentNames();
            String playerName = TerminalConsole.readString();
            if (playerName.equals("Q")) {
                break;
            } else if (playerName.equals("R")) {
                playerName = AgentsNameGenerator.getName();
            }

            Random generator = new Random();
            int percentage = generator.nextInt(100) + 1;
            if (percentage < 65) {
                playerList.add(new Player(playerName, new BlackMirror2_0()));
            } else {
                playerList.add(new Player(playerName, new BlackMirror()));
            }
        }
        return playerList;
    }

    private static void setLanguage(Language languageChoice) {
        console = new TerminalConsole(languageChoice);
        language = languageChoice;
        console.displaySettingUpLanguage();
        console.addDelay();
    }


    private static void playRound() {
        console.displayGameState(game);
        console.displayNextTurn(game);
        rollPlayerDice();
        playFirstBid();

        while (true) {
            console.displayNextTurn(game);
            console.displayRoundChoice();
            if (maxBidPlayed() && game.getCurrentPlayer().getInputMethod() instanceof AiPlayer) {
                playChallenge();
                finishRound();
                break;
            }
            String givenType = console.getPlayerChoice(game.getCurrentPlayer());

            if (givenType.equals("1")) {
                playBid();
            } else if (givenType.equals("2")) {
                playChallenge();
                finishRound();
                break;
            } else {
                console.displayInvalidChoiceError();
            }
        }
    }

    private static boolean maxBidPlayed() {
        return game.getLastBid().getQuantity() == game.totalNumberOfDice() &&
                game.getLastBid().getFaceValue() == 6;
    }

    private static void finishRound() {
        game.removeLosers();
        console.displayRemovingLoser();
        console.addDelay();
        if (game.isPlaying()) {
            console.displayDiceRerolled(game.getRound());
        }
    }


    private static void playFirstBid() {
        console.displayMakeInitialBid();
        Bid bid = console.getPlayerBid(game.getCurrentPlayer(), null, game.totalNumberOfDice());
        game.makeBid(bid);
        console.displayEndOfBidTurn(game, bid);

    }

    private static void playBid() {
        Bid bid = console.getPlayerBid(game.getCurrentPlayer(), game.getLastBid(), game.totalNumberOfDice());
        while (!(game.makeBid(bid))) {
            if (game.getCurrentPlayer().getInputMethod() instanceof HumanPlayer) {
                console.displayInvalidChoiceError();
            }
            bid = console.getPlayerBid(game.getCurrentPlayer(), game.getLastBid(), game.totalNumberOfDice());
        }
        console.displayEndOfBidTurn(game, bid);
    }


    private static void playChallenge() {
        console.displayChallengeMade(game);
        boolean successfulChallenge = game.makeChallenge();
        console.displayChallengeResults(successfulChallenge, game.getLastRoundLoser());

    }

    private static void rollPlayerDice() {
        for (Player player : game.getPlayers()) {
            player.roll();
            console.displayDice(player.getName(), Die.dice2Ints(player.getDice()));
        }
    }

    /**
     * Instantiates the Players that will play and then plays a game of Liar's Dice:
     *
     * @param args
     */
    public static void main(String[] args) {
        playGame();

    }

}
