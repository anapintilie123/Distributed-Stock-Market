package nl.rug.aoop.traderApplication;

import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.stockApplication.orders.Order;

/**
 * strategy interface.
 */
public interface IStrategy {

    /**
     * creates an order.
     *
     * @return an order.
     */
    Order createOrder();

    /**
     * wraps an order into a json message.
     * @param order the order.
     * @return Message.
     */
    Message wrapOrder(Order order);

    /**
     * creates a subscriber message with a trader.
     *
     * @return a message.
     */
    Message createSubscriber();

}
