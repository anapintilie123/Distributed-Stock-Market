package nl.rug.aoop.messagequeue.Queuez;

import nl.rug.aoop.messagequeue.Messagez.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrderedQueue {

    /**
     * Tests the constructor.
     */
    @Test
    public void constructor() {
        OrderedQueue Oqueue = new OrderedQueue();
        assertNotNull(Oqueue);
        assertNotNull(Oqueue.getMap());
    }

    /**
     * Checks for a successful enqueue.
     */
    @Test
    public void enqueue() {
        OrderedQueue Oqueue = new OrderedQueue();
        Message message = new Message("da", "nu");
        int size = Oqueue.getSize();
        Oqueue.enqueue(message);
        assertTrue(Oqueue.getSize() == size + 1);
    }

    /**
     *checks if the enqueue had a correct date.
     */
    @Test
    public void enqueueWithDate() {
        OrderedQueue Oqueue = new OrderedQueue();
        Message message = new Message("da", "nu");
        Oqueue.enqueue(message);
        assertNotNull(Oqueue.getMap().firstKey());
    }

    /**
     * makes sure that the dequeued message is not null
     */
    @Test
    public void elementDequeuedNotNull() {
        UnorderedQueue queue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        queue.enqueue(message1);
        assertNotNull(queue.dequeue());
    }

    /**
     * checks if a message is dequeued correctly.
     */
    @Test
    public void successfullyDequeue() {
        UnorderedQueue Oqueue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        Message message2 = new Message("da2", "nu2");
        Oqueue.enqueue(message1);
        Oqueue.enqueue(message2);
        int size = Oqueue.getSize();
        Oqueue.dequeue();
        assertTrue(Oqueue.getSize() == size - 1);
    }

    /**
     * Checks if trying to enqueue into a null queue.
     */
    @Test
    public void EnqueuingNull(){
        OrderedQueue queue = new OrderedQueue();
        assertThrows(NullPointerException.class ,() -> queue.enqueue(null));
    }

}
