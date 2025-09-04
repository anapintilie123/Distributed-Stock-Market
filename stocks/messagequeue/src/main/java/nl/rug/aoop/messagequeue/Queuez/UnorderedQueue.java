package nl.rug.aoop.messagequeue.Queuez;

import lombok.Getter;
import lombok.Setter;
import nl.rug.aoop.messagequeue.Messagez.Message;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Will add messages to the queue in the order that they are recieved.
 */
@Getter
@Setter
public class UnorderedQueue implements MessageQueue {
    private Queue<Message> queue;

    /**
     * constructor for the queue.
     */

    public UnorderedQueue() {
        queue = new LinkedList<>();
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
