package auction;

public class RunAuction {

    public static void main(String[] args) {

        int qPerAuction = 2;
        int cash = 100;
        
        RandomBidder bidder1 = new RandomBidder("1",4);
        //TitForTatBidder bidder1 = new TitForTatBidder("1");;

        TitForTatBidder bidder2 = new TitForTatBidder("2");;
        
        
        // keep tracks of wins
        int bidder1Wins = 0;
        int bidder2Wins = 0; 
        int draws = 0;
        
        

        // do 100 auctions 
        // TODO: Method for a single auction
        for (int i = 0; i<1000; i++) {      

            // (re-)set quantity for auction
            int quantity = 100;

            // initialize bidders
            bidder1 = new RandomBidder("1",4);
            //bidder1 = new TitForTatBidder("1");

            bidder1.init(quantity, cash);
            bidder2 = new TitForTatBidder("2");
            bidder2.init(quantity, cash);

            // keep track of bidders money to check for correctness after auction
            int bidder1Spent = 0;
            int bidder2Spent = 0;

            // keep track of bidders quantity to determine winner
            int bidder1Quant = 0;
            int bidder2Quant = 0;

            int bid1 = 0;
            int bid2 = 0;

            // print winner
            String winner;

            while (quantity > 0) {

                // let bidders bid
                bid1 = bidder1.placeBid();
                bid2 = bidder2.placeBid();

                // tell them 
                bidder1.bids(bid1, bid2);
                bidder2.bids(bid2, bid1);

                // update spent
                bidder1Spent += bid1;
                bidder2Spent += bid1;

                // distribute
                if (bid1 == bid2) {
                    bidder1Quant += qPerAuction / 2;
                    bidder2Quant += qPerAuction / 2;

                } else if (bid1 > bid2) {
                    bidder1Quant += qPerAuction;
                } else {
                    bidder2Quant += qPerAuction;
                }

                // update quantity
                quantity -= qPerAuction;
            }

            // Single Auction is over.

            // check for sanity
            if ((bidder1Spent > 100) || (bidder2Spent > 100) ) {
                System.out.println("Someone cheated!");
            }

            // determine winner
            if (bidder1Quant > bidder2Quant ) {
                winner = bidder1.getName();
                bidder1Wins ++;
            } else if (bidder2Quant > bidder1Quant) {
                winner = bidder2.getName();
                bidder2Wins ++;
            } else {
                winner = "";
                draws ++;
            }

            // Print current Auction 
            System.out.println(bidder1.getName() + " got " + bidder1Quant );
            System.out.println(bidder2.getName() + " got " + bidder2Quant );
            if (winner.isEmpty()) {
                System.out.println("Draw.");
            } else {
                System.out.println(winner + " won.");
            }

            System.out.println("----------------");

        }
        // Print Statistic after 1000 auctions
        System.out.println("1000 Auctions:");
        System.out.println(bidder1.getName() + " won " + bidder1Wins );
        System.out.println(bidder2.getName() + " won " + bidder2Wins );
        System.out.println("Draws: " + draws);
    }

}
