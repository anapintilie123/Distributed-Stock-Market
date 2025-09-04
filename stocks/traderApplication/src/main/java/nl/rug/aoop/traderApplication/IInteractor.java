package nl.rug.aoop.traderApplication;

import nl.rug.aoop.messagequeue.Messagez.Message;

import java.io.IOException;

/**
 * Interface for sending connecting traders, and sending orders.
 */
public interface IInteractor {
    /**
     * connects.
     */
    void connect() throws IOException;

    /**
     * sends an order that is wrapped in a message.
     *
     * @param message the message.
     */
    void sendOrder(Message message);
}
