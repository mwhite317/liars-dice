package liars;

import liars.agents.GamePlayer;
import liars.agents.HumanPlayer;
import liars.language.EnglishLanguage;

/**
 * A Player in a Liar's Dice Game Holds name and Dice
 * @author markwhite
 * @version 1.0
 */
public class Player {
    String name;
    Die[] dice;
    GamePlayer inputMethod;

    public Player(String name, GamePlayer inputMethod) {
        this.name = name;
        this.inputMethod = inputMethod;
    }

    /**
     * Instantiate a new Player with specified name
     *
     * @param name Player name
     */
    public Player(String name) {
        this.name = name;
        this.inputMethod = new HumanPlayer(new EnglishLanguage());
    }


    /**
     * Start playing a new game
     *
     * @param numberDice starting number of dice
     */
    public void startGame(int numberDice) {
        dice = new Die[numberDice];
        for (int i = 0; i < numberDice; i++) {
            dice[i] = new Die();
        }
    }

    /**
     * Roll the Player's dice
     */
    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    /**
     * take one die away from player
     *
     * @return remaining number of dice player now has
     */
    public int loseDie() {
        Die[] fewerDice = new Die[dice.length - 1];
        for (int i = 0; i < fewerDice.length; i++) {
            fewerDice[i] = dice[i];
        }
        dice = fewerDice;
        return fewerDice.length;
    }


    /**
     * Getter for Player's Dice
     *
     * @return array of dice
     */
    public Die[] getDice() {
        return dice;
    }

    /**
     * Get number of dice Player has
     *
     * @return dice count
     */
    public int getDieCount() {
        return dice.length;
    }

    /**
     * Get Player name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    public GamePlayer getInputMethod() {
        return inputMethod;
    }

    public void setInputMethod(GamePlayer inputMethod) {
        this.inputMethod = inputMethod;
    }


    public String toString() {
        return name + ": " + getDieCount();
    }
}

