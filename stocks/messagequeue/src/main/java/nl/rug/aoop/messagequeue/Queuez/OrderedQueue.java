package nl.rug.aoop.messagequeue.Queuez;

import lombok.Getter;
import lombok.Setter;
import nl.rug.aoop.messagequeue.Messagez.Message;

import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Orders messages into the queue with their timestamp.
 */
@Setter@Getter
public class OrderedQueue implements MessageQueue {
    private final SortedMap<LocalDateTime, Message> map;

    /**
     * Constructor of a treemap for the ordered queue.
     */
    public OrderedQueue() {
        this.map = new TreeMap<>();
    }

    @Override
    public void enqueue(Message message) {
        this.map.put(message.getTimeStamp(), message);
    }

    /**
     * removes the first message in the queue.
     *
     * @return the first message in the queue.
     */
    @Override
    public Message dequeue() {
        Message message = this.map.remove(map.firstKey());
        return message;
    }

    /**
     * Gets the size of the queue.
     *
     * @return returns the amount of messages in the queue.
     */
    @Override
    public int getSize() {
        return this.map.size();
    }
}
