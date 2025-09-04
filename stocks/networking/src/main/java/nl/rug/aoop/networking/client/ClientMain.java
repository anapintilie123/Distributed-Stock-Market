package nl.rug.aoop.networking.client;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;
import nl.rug.aoop.networking.client.messageHandlers.MessageLogger;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Main class just for testing.
 */
@Slf4j
public class ClientMain {

    /**
     * the main class for testing.
     *
     * @param args main.
     */
    public static void main(String[] args) {
        MessageHandler messageHandler = new MessageLogger();
        try {
            Client client = new Client(new InetSocketAddress("localhost", 61000), messageHandler);
            new Thread(client).start();
            new Thread(new MessageFilter(client)).start();
            
        } catch (IOException e) {
            log.error("client could not connect to the server",e);
        }
    }
}
