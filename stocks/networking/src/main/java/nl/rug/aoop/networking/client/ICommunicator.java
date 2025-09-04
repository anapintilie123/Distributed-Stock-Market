package nl.rug.aoop.networking.client;

/**
 * Communicator interface that allows different classes to send messages and terminate.
 */
public interface ICommunicator {

    /**
     * sends a message.
     *
     * @param message message to be sent.
     */
    void sendMessage(String message);

    /**
     * Terminates a connection to a server.
     */
    void terminate();
}
