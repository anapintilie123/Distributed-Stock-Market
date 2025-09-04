package nl.rug.aoop.messagequeue.Queuez;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.messagequeue.Messagez.Message;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * a thread safe priority blocking queue.
 */

@Getter@Slf4j
public class PriorityQueue implements MessageQueue {

    private final PriorityBlockingQueue<Message> queue;

    /**
     * Constructor of a treemap for the ordered queue.
     */
    public PriorityQueue() {
        this.queue = new PriorityBlockingQueue<>();
    }

    /**
     * adds message to the queue.
     *
     * @param message the message to be added.
     */
    @Override
    public void enqueue(Message message) {
        if(message == null){
            throw new NullPointerException();
        }
        log.info("Message is enqueued");
        queue.add(message);
    }

    /**
     * removes the first message in the queue.
     *
     * @return the message.
     */
    @Override
    public Message dequeue() {
        Message message = queue.poll();
        return message;
    }

    /**
     * Gets the size of the queue.
     *
     * @return returns the number of messages in the queue.
     */
    @Override
    public int getSize() {
        return this.queue.size();
    }
}
