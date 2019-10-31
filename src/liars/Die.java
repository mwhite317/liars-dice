package liars;

import java.util.Random;

/**
 * A Die used in the game of Lairs Dice
 * @author markwhite
 * @version 1.0
 */
public class Die {
    public static final int MAX_VALUE = 6;
    public static final int WILD_VALUE = 1;
    private int faceValue;

    /**
     * Creates Dice
     */
    public Die() {
    }

    // only used in testing
    public Die(int faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * Checks a Face Value to make sure it is valid Greater than equal to 1
     * and less than or equal to MAX VALUE of a die
     *
     * @param faceValue value to test
     * @return true if Face Value is valid, else false
     */
    public static boolean isValidFaceValue(int faceValue) {
        return faceValue >= 1 && faceValue <= MAX_VALUE;
    }

    /**
     * Converts dice to an int array o
     *
     * @param dice set of dice
     * @return array of ints of dice Face Values
     */
    public static int[] dice2Ints(Die[] dice) {
        int[] ints = new int[dice.length];
        for (int i = 0; i < dice.length; i++) {
            ints[i] = dice[i].getFaceValue();

        }
        return ints;
    }

    /**
     * Count occurrences of Face Value or wild Face Value in a set of dice
     *
     * @param dice      set of dice
     * @param faceValue Face Value to match
     * @return count of Face Value (or wild Face Value)
     */
    public static int count(Die[] dice, int faceValue) {
        int count = 0;
        for (Die die : dice) {
            if (die.getFaceValue() == (faceValue) || die.getFaceValue() == WILD_VALUE) {
                count++;
            }
        }
        return count;
    }

    /**
     * Rolls a die
     *
     * @return value rolled
     */
    public int roll() {
        Random randomNumber = new Random();
        faceValue = randomNumber.nextInt(6) + 1;
        return faceValue;

    }


    /**
     * returns Max Face Value allowed
     *
     * @return max value
     */
    public static int getMaxValue() {
        return MAX_VALUE;
    }

    /**
     * returns Wild Face Value
     *
     * @return value that is wild
     */
    public static int getWildValue() {
        return WILD_VALUE;
    }


    /**
     * Getter of die current Face Value
     *
     * @return die value
     */
    public int getFaceValue() {
        return faceValue;
    }

    /**
     * @return
     */
    public String toString() {
        return String.valueOf(faceValue);
    }

}
