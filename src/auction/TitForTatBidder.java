package auction;
import java.util.ArrayList;

public class TitForTatBidder implements Bidder {

    private String name = "Tit for Tat"; 

    // keep track of the current values for own cash and quantity
    private int quantity;
    private int cash;

    // keep track of enemy cash
    private int cashOther;

    // keep track of enemy bids
    private ArrayList<Integer> otherBids = new ArrayList<Integer>();

    // properties of the auction
    private int qPerAuction = 2;
    
    // properties of tit for tat
    private int relevantBids = 5; // how many of the enemies recent bids are considered
    private int maxAmount = 7; // treshhold for not cooperating

    public TitForTatBidder (String name) {
        this.name = this.name + " " + name;    
    }

    @Override
    public void init(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
        cashOther = cash;
    }

    @Override
    public int placeBid() {
        
        // the simplest cases
        if (cash == 0) return 0;  // no cash = no bid
        if (cashOther == 0) return 1; // no cash on the other side = buy cheap

        // bid 1 for the first n rounds
        if (otherBids.size() < relevantBids ) {
            return 1;
        }

        // count the number of last Bids
        int sumOfLastX = countBids(otherBids, relevantBids);
        
        // if the enemy cooperated enough times, do the same
        if (sumOfLastX < maxAmount) {
            return 1;
        }else{
            // else set a Price that is higher but does not drain cash 
            int maxPrice = (int) Math.ceil(cash / quantity) +2;
            if (maxPrice < cash) {
                return maxPrice;
            } else {
                return cash;
            }
        }
    }

    @Override
    public void bids(int own, int other) {
        otherBids.add(other);

        cash -= own;
        cashOther -= other;

        quantity -= qPerAuction;

    }

    public String getName() {
        return name;
    }

    private int countBids(ArrayList<Integer> list, int numberOfEntries) {
        int sum = 0;
        for(int i = list.size() - numberOfEntries; i< list.size(); i++){
            sum += list.get(i);
        }
        /*
        System.out.println(list.toString());
        System.out.println(sum);
        System.out.println("-------------------");
         */
        return sum;
    }

}
