package nl.rug.aoop.networking.client.messageHandlers;

import nl.rug.aoop.networking.client.ICommunicator;

import java.util.Map;

/**
 * Interface for handling messages with the communicator class.
 */
public interface MessageHandler {

    /**
     * Map of connected traders to the stock app.
     *
     * @return the list of traders.
     */
    Map<String, ICommunicator> getConnectedTraders();

    /**
     * handles a message.
     *
     * @param message      message to be handled.
     * @param communicator the communicator from the class that is using the message.
     */
    void handleMessage(String message, ICommunicator communicator);
}
