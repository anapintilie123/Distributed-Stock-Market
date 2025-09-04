package nl.rug.aoop.messagequeue.Consumerz;

import nl.rug.aoop.messagequeue.Messagez.Message;

/**
 * Consumer interface to dequeue.
 */
public interface MQConsumer {

    /**
     * will dequeue a message.
     * @return returns a message.
     */
    Message poll();
}
