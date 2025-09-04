package nl.rug.aoop.messagequeue.Queuez;

import nl.rug.aoop.messagequeue.Messagez.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPriorityQueue {

    /**
     * Checks for a successful enqueue.
     */

    @Test
    public void enqueue() {
        PriorityQueue Pqueue = new PriorityQueue();
        Message message = new Message("da", "nu");
        int size = Pqueue.getSize();
        Pqueue.enqueue(message);
        assertTrue(Pqueue.getSize() == size + 1);
    }

    /**
     *checks if the enqueue had a correct date.
     */

    @Test
    public void enqueueWithDate() {
        PriorityQueue Pqueue = new PriorityQueue();
        Message message = new Message("da", "nu");
        Pqueue.enqueue(message);
        assertNotNull(Pqueue.getQueue());
    }

    /**
     * makes sure that the dequeued message is not null
     */
    @Test
    public void elementDequeuedNotNull() {
        PriorityQueue queue = new PriorityQueue();
        Message message1 = new Message("da", "nu");
        queue.enqueue(message1);
        assertNotNull(queue.dequeue());
    }

    /**
     * checks if a message is dequeued correctly.
     */
    @Test
    public void successfullyDequeue() {
        PriorityQueue Pqueue = new PriorityQueue();
        Message message1 = new Message("da", "nu");
        Message message2 = new Message("da2", "nu2");
        Pqueue.enqueue(message1);
        Pqueue.enqueue(message2);
        int size = Pqueue.getSize();
        Pqueue.dequeue();
        assertTrue(Pqueue.getSize() == size - 1);
    }

    /**
     * Checks if trying to enqueue into a null queue.
     */

    @Test
    public void EnqueuingNull(){
        PriorityQueue queue = new PriorityQueue();
        assertThrows(NullPointerException.class ,() -> queue.enqueue(null));
    }
}
