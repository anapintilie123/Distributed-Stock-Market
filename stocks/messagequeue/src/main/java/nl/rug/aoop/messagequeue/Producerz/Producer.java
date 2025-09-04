package nl.rug.aoop.messagequeue.Producerz;

import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Queuez.MessageQueue;

/**
 * A producer can add a message to a queue.
 */
public class Producer implements MQProducer {
    private final MessageQueue queue;

    /**
     * Constructor for the producer.
     *
     * @param queue the queue for the producer.
     */
    public Producer(MessageQueue queue) {
        if(queue == null){
            throw new NullPointerException();
        }
        this.queue = queue;
    }


    /**
     * Will enqueue a message.
     *
     * @param message the message to be enqueued.
     */
    @Override
    public void put(Message message) {
        this.queue.enqueue(message);

    }
}
