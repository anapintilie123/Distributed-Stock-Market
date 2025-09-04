package nl.rug.aoop.messagequeue.Queuez;

import nl.rug.aoop.messagequeue.Messagez.Message;

/**
 * Interface for message queues.
 */
public interface MessageQueue {

    /**
     * used to add a message to a queue.
     *
     * @param message the message to be added.
     */
    void enqueue(Message message);

    /**
     * Removes a message from the queue.
     *
     * @return the message from the queue.
     */
    Message dequeue();

    /**
     * returns the size of message.
     *
     * @return the number of messages in queue.
     */
    int getSize();
}
