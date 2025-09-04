package nl.rug.aoop.stockApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.CommandHandler;
import nl.rug.aoop.messagequeue.Consumerz.Consumer;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Messagez.MqMessageHandler;
import nl.rug.aoop.messagequeue.Messagez.MqPutCommand;
import nl.rug.aoop.messagequeue.Queuez.MessageQueue;
import nl.rug.aoop.messagequeue.Queuez.PriorityQueue;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;
import nl.rug.aoop.networking.server.Server;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.operations.Stocks;
import nl.rug.aoop.stockApplication.operations.Traders;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * here we instantiate the models and read from yaml file.
 * here we also start networking.
 */
@Slf4j
public class StackApp implements Runnable {
    private Stocks stocks;
    private Consumer consumer;
    private MessageQueue messageQueue;
    private MessageHandler mqMessageHandler;
    private CommandHandler commandHandler;
    private Server server;
    private static final int DEFAULT_PORT = 6100;
    private int port;
    private boolean running;
    private StocksManager stocksManager;
    private Traders traders;
    private Broadcaster broadcaster;

    /**
     * Creates a server that everything connects to.
     *
     * @return the server.
     */
    private Server initServer() {
        this.stocksManager= new StocksManager(this.stocks, this.traders);
        this.commandHandler= new CommandHandler("MqPut",new MqPutCommand((PriorityQueue) this.messageQueue));
        this.commandHandler.registerCommand("Stocks",new StocksCommand(stocksManager));
        this.mqMessageHandler = new MqMessageHandler(this.commandHandler,this.stocksManager.getConnectedTraders());
        try {
            port = Integer.parseInt(System.getenv("MESSAGE_QUEUE_PORT"));
        } catch (NumberFormatException e) {
            port = DEFAULT_PORT;
        }

        try {
            this.server = new Server(port,mqMessageHandler);
            Thread serverThread = new Thread(server);
            serverThread.start();
            return server;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Server could not e started");
        }
        return null;
    }

    /**
     * Creates the queue for the orders.
     */
    private void initQueue() {
        this.messageQueue = new PriorityQueue();
    }

    /**
     * loads all the stock data from the yaml file into our structure.
     *
     * @throws IOException if value read is wrong.
     */
    private void loadStocksYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Path stringPath = Path.of("data", "stocks.yaml");
        stocks = objectMapper.readValue(stringPath.toFile(), Stocks.class);
    }

    /**
     * loads all the trader data from the yaml file into our structure.
     *
     * @throws IOException if value read is wrong.
     */
    private void loadTradersYaml() throws IOException {
        List<Trader> listTraders;
        this.traders = new Traders();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Path stringPath = Path.of("data", "traders.yaml");
        listTraders = objectMapper.readerForListOf(Trader.class).readValue(stringPath.toFile());
        this.traders.setTradersMap(listTraders,this.stocks);
    }

    /**
     * Initiates the traders, stocks, and the server.
     */
    public void init() {
        try {
            this.loadStocksYaml();
            this.loadTradersYaml();
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
        initQueue();
        this.server = initServer();
        consumer = new Consumer(messageQueue);
        broadcaster = new Broadcaster(this.mqMessageHandler.getConnectedTraders(),this.traders);
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            Message message = consumer.poll();
            if (message != null) {
                log.info("polled message is " + message.getHeader() + message.getBody());
                Map<String, Object> options = new HashMap<String, Object>();
                options.put("header", message.getHeader());
                options.put("body", message.getBody());
                log.info("polled message  is "+ message.getHeader() +" "+ message.getBody());
                this.commandHandler.executeCommand(message.getHeader(), options);
            }

            broadcaster.sendUpdatesToTraders();
        }
    }
}
