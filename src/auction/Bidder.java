package auction;

/**
 * Represents a bidder for the auction
 * @author m
 *
 */
public interface Bidder {
    /**
     * @param quantity 
     *          the quantity
     * @param cash
     *          the cash limit 
     */
    void init(int quantity, int cash);
    
    /**
     * Retrieves the next bid for the product, which may be zero
     * @return the next bid
     */
    int placeBid();
    
    /**
     * Shows the bids of the two bidders.
     * 
     * @param own the bid of this bidder
     * @param other the bid of the other bidder
     */
    void bids(int own, int other);

}
