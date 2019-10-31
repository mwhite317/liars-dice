package liars;

/**
 * Bid of a given Face Value and Quantity
 * @author markwhite
 * @version 1.0
 */
public class Bid {
    private int faceValue;
    private int quantity;

    /**
     * Create a new Bid
     *
     * @param faceValue
     * @param quantity
     */
    public Bid(int faceValue, int quantity) {
        this.faceValue = faceValue;
        this.quantity = quantity;
    }


    /**
     * A bid is bigger if same faceValue and greater quantity
     * or larger faceValue and same quantity
     *
     * @param otherBid
     * @return
     */
    public boolean isOtherBigger(Bid otherBid) {
        if (otherBid.faceValue == faceValue && otherBid.quantity > quantity) {
            return true;
        } else return otherBid.faceValue > faceValue && otherBid.quantity == quantity;
    }


    /**
     * Get Face Value
     *
     * @return
     */
    public int getFaceValue() {
        return faceValue;
    }

    /**
     * Get Quantity Value
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * @return
     */
    public String toString() {
        return "Bid{" + "faceValue=" + faceValue + ", quantity=" + quantity + '}';
    }
}

