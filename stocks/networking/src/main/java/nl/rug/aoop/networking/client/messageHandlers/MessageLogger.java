package nl.rug.aoop.networking.client.messageHandlers;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.ICommunicator;

import java.util.Map;

/**
 * Logs various messages to make sure things are working.
 */
@Slf4j
public class MessageLogger implements MessageHandler {
    /**
     * Getter for list of connected traders.
     *
     * @return The map of traders.
     */
    @Override
    public Map<String, ICommunicator> getConnectedTraders() {
        throw new UnsupportedOperationException();
    }

    /**
     * Logs the given message.
     * @param message     message to be logged.
     * @param communicator the communicator from the class that is using the message.
     */
    @Override
    public void handleMessage(String message, ICommunicator communicator) {
        log.info(message);
    }
}
