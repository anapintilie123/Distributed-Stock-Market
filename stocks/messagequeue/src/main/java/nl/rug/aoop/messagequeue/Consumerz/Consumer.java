package nl.rug.aoop.messagequeue.Consumerz;

import lombok.Getter;
import lombok.Setter;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Queuez.MessageQueue;

/**
 * A consumer class that can dequeue a message from a queue.
 */
@Setter@Getter
public class Consumer implements MQConsumer {
    private MessageQueue queue;

    /**
     * Constructor for the consumer.
     *
     * @param queue the queue that a consumer can dequeue a message from.
     */

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    /**
     * dequeues a message from a queue.
     *
     * @return the message that was dequeued
     */
    @Override
    public Message poll() {
        return this.queue.dequeue();
    }
}
