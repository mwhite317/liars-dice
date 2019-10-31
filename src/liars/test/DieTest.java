package liars.test;

import liars.Die;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    @Test
    void getMaxValue() {
        Assertions.assertEquals(Die.getMaxValue(), 6);
    }

    @Test
    void getWildValue() {
        assertEquals(Die.getWildValue(), 1);
    }

    @Test
    void isValidFaceValue() {
        assertFalse(Die.isValidFaceValue(7));
        assertTrue(Die.isValidFaceValue(6));
        assertTrue(Die.isValidFaceValue(5));
        assertTrue(Die.isValidFaceValue(4));
        assertTrue(Die.isValidFaceValue(3));
        assertTrue(Die.isValidFaceValue(2));
        assertTrue(Die.isValidFaceValue(1));
        assertFalse(Die.isValidFaceValue(0));
    }

    @Test
    void roll() {
        Die die = new Die();
        for (int i = 0; i < 100; i++) {
            int roll = die.roll();
            assertTrue(roll >= 1 && roll <= 6);
        }
    }

    @Test
    void dice2Ints() {
        Die firstDie = new Die();
        Die secondDie = new Die();

        firstDie.roll(); // 1
        secondDie.roll(); // 3
        Die[] dice = new Die[]{firstDie, secondDie};

        Die.dice2Ints(dice);
    }

    @Test
    public void count() {
        Die firstDie = new Die(6);
        Die secondDie = new Die(1);

        Die[] dice = {firstDie, secondDie};

        assertEquals(Die.count(dice, 6), 2);
        assertEquals(Die.count(dice, 5), 1);
        assertEquals(Die.count(dice, 1), 1);
    }


}