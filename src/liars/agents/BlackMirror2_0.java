package liars.agents;

import liars.Bid;

import java.util.Random;

public class BlackMirror2_0  extends AiPlayer{
    Random generator = new Random();

    public String getPlayerChoice() {
        int percentage = generator.nextInt(100)+1;
        if (percentage < 80){
            return "1";
        }
        return "2";
    }

    public Bid getPlayerBid(Bid lastBid, int numberOfDice) {
        Bid newBid = new Bid(6,numberOfDice);
        if (lastBid == null) {
            return newBid;
        }

        Bid highQuantity = new Bid(lastBid.getFaceValue(), numberOfDice);
        Bid highFaceValue = new Bid(6,lastBid.getQuantity());

        int percentage = generator.nextInt(100)+1;
        if (percentage < 80){
            return highQuantity;
        }
        return highFaceValue;
    }
}
