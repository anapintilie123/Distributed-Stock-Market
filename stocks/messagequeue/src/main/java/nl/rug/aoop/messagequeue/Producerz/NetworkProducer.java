package nl.rug.aoop.messagequeue.Producerz;

import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.networking.client.Client;

/**
 * Gives message to client.
 */
public class NetworkProducer implements MQProducer {
    private final Client client;

    /**
     * constructor.
     *
     * @param client the used client.
     */
    public NetworkProducer(Client client) {
        if (client == null) {
            throw new NullPointerException();
        }
        this.client = client;
    }

    /**
     * Used to enqueue a message in a queue.
     *
     * @param message the message to be enqueued.
     */
    @Override
    public void put(Message message) {
        if(message!=null) {
            this.client.sendMessage(message.toJSONString());
        }
    }
}
