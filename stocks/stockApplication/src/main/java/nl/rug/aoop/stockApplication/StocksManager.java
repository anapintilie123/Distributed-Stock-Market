package nl.rug.aoop.stockApplication;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.ICommunicator;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.operations.Stocks;
import nl.rug.aoop.stockApplication.operations.Traders;
import nl.rug.aoop.stockApplication.orders.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used for dealing with all the orders received from the traders.
 */
@Slf4j
public class StocksManager {
    private List<Order> asks;
    private List<Order> bids;
    private Stocks stocks;
    private Traders traders;
    @Getter
    @Setter
    private Map<String, ICommunicator> connectedTraders;

    /**
     * constructor.
     *
     * @param stocks the stock.
     * @param traders the traders.
     */
    public StocksManager(Stocks stocks, Traders traders) {
        this.asks = new ArrayList<Order>();
        this.bids = new ArrayList<Order>();
        this.connectedTraders = new HashMap<String, ICommunicator>();
        this.stocks = stocks;
        this.traders = traders;
    }

    /**
     * adds an order request.
     *
     * @param ask the order.
     */
    public void addAsk(Order ask) {
        this.asks.add(ask);
    }

    /**
     * adds a bid.
     *
     * @param bid the order.
     */

    public void addBid(Order bid) {
        this.asks.add(bid);
    }

    /**
     * checks if there is an order that can be matched with the given one.
     *
     * @param ask the order.
     */
    public void acceptAsk(Order ask) {
        for (Order storedBid : bids) {
            if (storedBid.getStockID().equals(ask.getStockID())) {
                if (storedBid.getStockID().equals(ask.getStockID())) {
                    if (storedBid.getPrice() <= ask.getPrice()) {
                        executeAsk(ask, storedBid.getPrice());
                        return;
                    }
                }
            }
            addAsk(ask);
        }
    }

    /**
     * accepts an order bid if there is a matched bid.
     *
     * @param bid the order.
     */
    public void acceptBid(Order bid) {
        for (Order storedAsk : asks) {
            if (storedAsk.getStockID().equals(bid.getStockID())) {
                if (storedAsk.getStockID().equals(bid.getStockID())) {
                    if (storedAsk.getPrice() <= bid.getPrice()) {
                        executeBid(bid, storedAsk.getPrice());
                        return;
                    }
                }
            }
            addBid(bid);
        }
    }

    /**
     * Executes an ask.
     *
     * @param ask the order.
     * @param newPrice new price.
     */
    private void executeAsk(Order ask, int newPrice) {
        String traderID = ask.getTraderID();
        Trader trader = traders.getTrader(traderID);
        trader.updateBuyTrader(ask.getStockID(), ask.getPrice());
        stocks.getStocks().get(ask.getStockID()).updatePrice(newPrice);
        log.info("executed ask : "+ "trader" + traderID );
    }

    /**
     * executes a bid.
     *
     * @param bid the order.
     * @param newPrice the price.
     */
    private void executeBid(Order bid, int newPrice) {
        String traderID = bid.getTraderID();
        Trader trader = traders.getTrader(traderID);
        trader.updateBuyTrader(bid.getStockID(), bid.getPrice());
        stocks.getStocks().get(bid.getStockID()).updatePrice(newPrice);
        log.info("executed bid : "+ "trader" + traderID );
    }
}
