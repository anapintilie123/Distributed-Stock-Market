package nl.rug.aoop.stockApplication.models;

import lombok.Getter;

/**
 * A stock structure.
 */

@Getter
public class Stock {

    private String symbol;

    private String name;

    private long sharesOutstanding;
    private int initialPrice;

    /**
     * updates price of stock.
     *
     * @param newPrice new price of stock.
     */
    public void updatePrice(int newPrice){
        if(newPrice < 0 ){
            throw new IllegalArgumentException();
        }
        this.initialPrice = newPrice;
    }
}
