package liars.agents;

import liars.Bid;
import liars.language.Language;

import java.util.Scanner;

public class HumanPlayer implements GamePlayer {
    private static Scanner inDevice = new Scanner(System.in);
    private static Language taal;

    public HumanPlayer(Language taal) {
        HumanPlayer.taal = taal;
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

    @Override
    public String getPlayerChoice() {
        return readString();
    }

    @Override
    public Bid getPlayerBid(Bid lastBid, int numberOfDice) {
        System.out.print(taal.makeQuantityBid());
        int quantityType = readNumber();

        System.out.print(taal.makeFaceValueBid());
        int faceValueType = readNumber();

        return new Bid(faceValueType, quantityType);
    }


}
