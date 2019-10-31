package liars.test;

import liars.Bid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BidTest {

    @Test
    void isOtherBigger() {
        Bid normalBid = new Bid(5, 2);

        Bid biggerBid = new Bid(6, 2);
        Bid biggerBid1 = new Bid(5, 3);

        Bid smallerBid = new Bid(4, 2);
        Bid smallerBid1 = new Bid(5, 1);


        //Compare a bid with a newer bid to see which is greater

        assertTrue(normalBid.isOtherBigger(biggerBid));
        assertTrue(normalBid.isOtherBigger(biggerBid1));

        assertFalse(normalBid.isOtherBigger(smallerBid));
        assertFalse(normalBid.isOtherBigger(smallerBid1));

        assertFalse(normalBid.isOtherBigger(normalBid));
    }
}