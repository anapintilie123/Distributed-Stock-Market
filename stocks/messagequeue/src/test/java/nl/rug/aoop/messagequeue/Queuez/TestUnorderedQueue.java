package nl.rug.aoop.messagequeue.Queuez;

import nl.rug.aoop.messagequeue.Messagez.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUnorderedQueue {

    /**
     * checks if the instance of the class is not null
     */
    @Test
    public void testconstructor() {
        UnorderedQueue Uqueue = new UnorderedQueue();
        assertNotNull(Uqueue);
    }

    /**
     * checks if the queue field is not null
     */
    @Test
    public void constructor() {
        UnorderedQueue Uqueue = new UnorderedQueue();
        assertNotNull(Uqueue.getQueue());
    }

    /**
     * checks if an instance of message is successfully enqueued in the queue
     */
    @Test
    public void enqueue() {
        UnorderedQueue Uqueue = new UnorderedQueue();
        Message message = new Message("da", "nu");
        int size = Uqueue.getSize();
        Uqueue.enqueue(message);
        assertTrue(Uqueue.getSize() == size + 1);
    }

    /**
     * checks if a dequeued element is not null
     */

    @Test
    public void elementDequeuedNotNull(){
        UnorderedQueue queue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        queue.enqueue(message1);
        assertNotNull(queue.dequeue());
    }

    /**
     * checks if a message is succesfully dequeued
     */
    @Test
    public void successfullyDequeue() {
        UnorderedQueue uqueue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        Message message2 = new Message("da2", "nu2");
        uqueue.enqueue(message1);
        uqueue.enqueue(message2);
        int size = uqueue.getSize();
        uqueue.dequeue();
        assertTrue(uqueue.getSize() == size - 1);
    }

    /**
     * checks if the first element enqueued is the first element to be dequeued
     */
    @Test
    public void enqueuedInRightOrder1() {
        UnorderedQueue queue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        Message message2 = new Message("da2", "nu2");
        queue.enqueue(message1);
        queue.enqueue(message2);
        assertTrue(message1.equals(queue.dequeue()));
    }

    /**
     * checks if the second element enqueued is the second element to be dequeued
     */
    @Test
    public void enqueuedInRightOrder2() {
        UnorderedQueue queue = new UnorderedQueue();
        Message message1 = new Message("da", "nu");
        Message message2 = new Message("da2", "nu2");
        queue.enqueue(message1);
        queue.enqueue(message2);
        queue.dequeue();
        assertTrue(message2.equals(queue.dequeue()));

    }

    /**
     * Checks if trying to enqueue into a null queue.
     */
    @Test
    public void EnqueuingNull(){
        UnorderedQueue queue = new UnorderedQueue();
        assertThrows(NullPointerException.class ,() -> queue.enqueue(null));
    }

}