package nl.rug.aoop.traderApplication;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.operations.Stocks;
import nl.rug.aoop.stockApplication.orders.Order;

import java.util.Random;

/**
 * The strategy that the traders use to buy and sell stocks.
 */
@Slf4j
public class Strategy implements IStrategy {
    private final Trader trader;
    private final Stocks stocks;
    private static final int UPPERBOUND = 50;

    /**
     * constructor.
     *
     * @param trader the trader.
     */

    public Strategy(Trader trader) {
        this.trader = trader;
        stocks = trader.getStocks();
    }

    /**
     * creates a random order, either buy or sell.
     *
     * @return the created order.
     */
    @Override
    public Order createOrder(){
        if (trader.getStockPortfolio() == null || trader.getStocks() == null) {
            return null;
        }
        String type = new Random().nextBoolean() ? "buy" : "sell";
        String stockID;
        int price = new Random().nextInt(UPPERBOUND) - 25;
        if (type.equals("buy")) {
            int randomValue = new Random().nextInt(stocks.getStocks().size());
            stockID = (String) stocks.getStocks().keySet().toArray()[randomValue];
            int stockPrice = stocks.getStocks().get(stockID).getInitialPrice();
            price += stockPrice;
            this.trader.subtractMoney(price);
        } else {
            stockID = this.trader.getIDofRandomOwnedShare();
            if (stockID==null) {
                return null;
            }
            int stockPrice = stocks.getStocks().get(stockID).getInitialPrice();
            price += stockPrice;
            this.trader.subtractShare(stockID);
        }
        log.info("Strategy: " + type + stockID + price);
        Order order = new Order(type, stockID, price, trader.getId());
        return order;
    }

    /**
     * puts order into a message.
     *
     * @param order the order.
     * @return message a message.
     */

    @Override
    public Message wrapOrder(Order order) {
        Message message = new Message(order.getType(), order.toJSONString());
        String Json = message.toJSONString();
        Message newNetMess = new Message("MqPut", Json);
        return newNetMess;
    }

    /**
     * creates a subscriber.
     *
     * @return a message;
     */
    @Override
    public Message createSubscriber(){
        Message message = new Message("Subscribe", this.trader.getId());
        return message;
    }

}
