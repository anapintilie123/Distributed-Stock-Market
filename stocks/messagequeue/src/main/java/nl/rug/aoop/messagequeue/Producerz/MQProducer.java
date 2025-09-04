package nl.rug.aoop.messagequeue.Producerz;

import nl.rug.aoop.messagequeue.Messagez.Message;

/**
 * Interface for a producer.
 */
public interface MQProducer {

    /**
     * Used to enqueue a message in a queue.
     *
     * @param message the message to be enqueued.
     */
    void put(Message message);

}
