package nl.rug.aoop.stockApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import nl.rug.aoop.stockApplication.operations.Stocks;

import java.util.Random;

/**
 * A structure for the trader.
 */

@Getter
public class Trader {
    private String id;
    private String name;
    @Setter
    private int funds;
    @Setter
    private StockPortfolio stockPortfolio;
    @JsonIgnore @Setter
    private Stocks stocks;
    private static final Gson GSON = new Gson();

    /**
     * Constructor.
     */
    public Trader() {
    }

    /**
     * constructor.
     *
     * @param id             id.
     * @param name           name.
     * @param funds          funds.
     * @param stockPortfolio stockPortfolio.
     * @param stocks         stocks.
     */
    public Trader(String id, String name, int funds, StockPortfolio stockPortfolio, Stocks stocks) {
        this.id = id;
        this.name = name;
        this.funds = funds;
        this.stockPortfolio = stockPortfolio;
        if(stocks==null){
            throw new IllegalArgumentException();
        }
        this.stocks = stocks;
    }

    /**
     * converts string to json.
     *
     * @return returns json.
     */
    public String toJSONString() {
        return GSON.toJson(this);

    }

    /**
     * converts json to string.
     *
     * @param JSONString the json.
     * @return a string.
     */
    public static Trader fromJSONString(String JSONString) {
        return GSON.fromJson(JSONString, Trader.class);
    }

    /**
     * stock randomizer for making an order.
     *
     * @return returns id for a stock.
     */
    public String getIDofRandomOwnedShare() {
        int nrStocks = this.stockPortfolio.getOwnedShares().keySet().size();
        String stockID;
        if (!ownsStocks()) {
            return null;
        }

        do {
            int randomValue = new Random().nextInt(nrStocks);
            stockID = (String) stockPortfolio.getOwnedShares().keySet().toArray()[randomValue];
        } while (stockPortfolio.getOwnedShares().get(stockID) == 0);

        return stockID;
    }

    /**
     * Checks if a trader owns a stock.
     *
     * @return true if owned.
     */
    public boolean ownsStocks() {
        for (String id : stockPortfolio.getOwnedShares().keySet()) {
            if (stockPortfolio.getOwnedShares().get(id) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * removes money from a trader after a order is finished.
     *
     * @param amount new total money.
     */
    public void subtractMoney(int amount) {
        this.funds -= amount;
    }

    /**
     * adds or removes stocks from portfolio.
     *
     * @param stockID new amounts of stocks owned.
     */
    public void subtractShare(String stockID) {
        if(stockID.equals(null)){
            throw new IllegalArgumentException();
        }
        stockPortfolio.getOwnedShares().put(stockID, stockPortfolio.getOwnedShares().get(stockID) - 1);
    }

    /**
     * updates the trader with the bought stock.
     *
     * @param stockID id of stock.
     * @param price   the price.
     */
    public void updateBuyTrader(String stockID, int price) {
        int currNumberofShares;
        if (this.stockPortfolio.getOwnedShares().containsKey(stockID)) {
            currNumberofShares = this.stockPortfolio.getOwnedShares().get(stockID);
        } else {
            currNumberofShares = 0;
        }
        this.stockPortfolio.getOwnedShares().put(stockID, currNumberofShares + 1);
        this.funds -= price;
    }

    /**
     * updates a trader after stock is sold.
     *
     * @param stockID id of stock.
     * @param price price.
     */
    public void updateSellTrader(String stockID, int price) {
        int curNumberShares;
        if (this.stockPortfolio.getOwnedShares().containsKey(stockID)) {
            curNumberShares = this.stockPortfolio.getOwnedShares().get(stockID);
            this.stockPortfolio.getOwnedShares().put(stockID, curNumberShares - 1);
            this.funds += price;
        }
    }

    /**
     * Updates a trader.
     *
     * @param newTrader the trader.
     */
    public void update(Trader newTrader){
        this.id = newTrader.getId();
        this.name = newTrader.getName();
        this.funds=newTrader.getFunds();
        this.stockPortfolio = newTrader.getStockPortfolio();
        this.stocks = newTrader.getStocks();
    }
}
