package liars.agents;

import liars.Bid;

import java.util.Random;

public class BlackMirror extends AiPlayer {
    private final Random generator;

    public BlackMirror() {
        generator = new Random();
    }

    public String getPlayerChoice() {
        return String.valueOf(generator.nextInt(2) + 1);
    }

    public Bid getPlayerBid(Bid lastBid, int numberOfDice) {
        int previousFaceValue = 0;
        int previousQuantity = 1;
        if (lastBid != null) {
            previousFaceValue = lastBid.getFaceValue();
            previousQuantity = lastBid.getQuantity();
        }

        int faceValue = generator.nextInt(6 - previousFaceValue + 1) + previousFaceValue;
        int quantity = generator.nextInt(numberOfDice - previousQuantity + 1) + previousQuantity;
        return new Bid(faceValue, quantity);
    }

}

