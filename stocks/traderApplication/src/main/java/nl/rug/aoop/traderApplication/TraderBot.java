package nl.rug.aoop.traderApplication;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.orders.Order;

import java.io.IOException;

/**
 * initilises a trader.
 * connects to a server.
 * gets an update and updates the trader field.
 * creates an order.
 * send the order through the client.
 */
@Slf4j
public class TraderBot implements Runnable {
    private Strategy strategy;
    private Interactor interactor;
    private boolean running;
    @Getter
    private Trader trader;
    private Message message;

    /**
     * constructor.
     *
     * @param strategy   strategy.
     * @param interactor interactor.
     * @param running    boolean if running.
     * @param trader     the trader.
     */
    public TraderBot(Strategy strategy, Interactor interactor, boolean running, Trader trader) {
        this.strategy = strategy;
        this.interactor = interactor;
        this.running = running;
        this.trader = trader;
    }

    /**
     * changes the strategy for the trader.
     *
     * @param newStrategy the strategy.
     */
    public void changeStrategy(Strategy newStrategy) {
        this.strategy = newStrategy;
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
        log.info(this.trader.getId());
        running = true;
        try {
            interactor.connect();
        } catch (IOException e) {
            running = false;
            log.error(this.trader.getId() + " cannot connect to server ", e);
            terminate();
        }
        while(running) {
            message = createMessageOrder();
            if (message != null) {
                sendOrder(message);
            }else{
                message = createSubscriber();
                sendSubscriber(message);
            }
            if (!interactor.isConnected()) {
                terminate();
                break;
            }
        }
    }

    /**
     * creates a wrapped message.
     *
     * @return wrappedMessage a message.
     */
    public Message createMessageOrder() {
        Order order = strategy.createOrder();
        if (order == null) {
            return null;
        }
        Message message = strategy.wrapOrder(order);
        return message;
    }

    /**
     * Creates a subscriber using the strategy.
     * @return wrappedMessage dd.
     */
    public Message createSubscriber(){
        Message message = strategy.createSubscriber();
        return message;
    }

    /**
     * sends an order.
     *
     * @param message the message.
     */
    public void sendOrder(Message message) {
        interactor.sendOrder(message);
    }

    /**
     * sends the subscriber in a message.
     *
     * @param message the trader in a message.
     */
    public void sendSubscriber(Message message){
        interactor.sendSubscriber(message);
    }

    /**
     * terminates the bot.
     */
    public void terminate() {
        log.info("Terminating");
        running = false;

    }
}
