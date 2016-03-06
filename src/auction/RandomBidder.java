package auction;

public class RandomBidder implements Bidder {
    
    private String name = "Random Bidder";
    private int cash;
    private int quantity;
    private int max;

    public RandomBidder(String name, int max) {
        this.name = this.name + " " + name;
        this.max = max;
    }
    
    @Override
    public void init(int quantity, int cash) {
        this.cash = cash;
        this.quantity = quantity;

    }

    @Override
    public int placeBid() {
       
        return (int) Math.round(Math.random() * Math.min(max, cash));
    }

    @Override
    public void bids(int own, int other) {
        cash -= own;

    }
    
    public String getName () {
        return name;
    }

}
