package nl.rug.aoop.traderApplication;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.CommandHandler;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Messagez.MqMessageHandler;
import nl.rug.aoop.messagequeue.Producerz.NetworkProducer;
import nl.rug.aoop.networking.client.Client;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;
import nl.rug.aoop.stockApplication.models.Trader;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Our facade pattern that gives all the functionality to the trader bots.
 */
@Slf4j
public class Interactor implements IInteractor {
    private Client client;
    private Trader trader;
    private NetworkProducer networkProducer;
    private MessageHandler messageHandler;
    private CommandHandler commandHandler;
    private int port;

    @Getter
    private boolean connected;
    private final int DEFAULT_PORT = 6100;

    /**
     * constructor.
     *
     * @param trader         the trader.
     * @param messageHandler the message handler.
     * @param commandHandler the command handler.
     */
    public Interactor(Trader trader, MessageHandler messageHandler, CommandHandler commandHandler) {
        this.trader = trader;
        this.commandHandler = commandHandler;
        this.messageHandler = new MqMessageHandler(commandHandler);
    }

    /**
     * starts clients, and initiates network producer.
     */
    @Override
    public void connect() throws IOException {
        startClient();
        networkProducer = new NetworkProducer(this.client);
    }

    /**
     * creates the threads and ports for all the clients.
     */
    private void startClient() throws IOException {
        try {
            this.port = Integer.parseInt(System.getenv("MESSAGE_QUEUE_PORT"));
        } catch (NumberFormatException ex) {
            port = DEFAULT_PORT;
        }
        log.info("Client connect on localhost port : " + port);
        this.client = new Client(new InetSocketAddress("localhost", port), messageHandler);
        new Thread(client).start();
    }

    /**
     * sends a message with the network producer.
     *
     * @param message the message.
     */
    public void sendOrder(Message message) {
        if(message != null) {
            networkProducer.put(message);
        }
    }

    /**
     * sends the subscription in a message.
     *
     * @param message the message.
     */
    public void sendSubscriber(Message message) {
        networkProducer.put(message);
    }

    /**
     * checks if a client is running.
     *
     * @return true if client is running.
     */
    public boolean isConnected(){
        return client.isConnected();
    }
}
