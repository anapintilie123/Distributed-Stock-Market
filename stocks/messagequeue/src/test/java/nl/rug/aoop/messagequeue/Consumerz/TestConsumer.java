package nl.rug.aoop.messagequeue.Consumerz;

import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Queuez.OrderedQueue;
import nl.rug.aoop.messagequeue.Queuez.UnorderedQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestConsumer {
    /**
     * test if the field of type messageQueue is correctly initialised in the constructer when using a
     * Ordered Queue
     */
    @Test
    public void TestOrderedConstructor() {
        OrderedQueue queue = new OrderedQueue();
        Consumer consumer = new Consumer(queue);
        assertNotNull(consumer.getQueue());
    }
    /**
     * test if the field of type messageQueue is correctly initialised in the constructer when using a
     * Unordered Queue
     */
    @Test
    public void TestUnorderedConstructor() {
        UnorderedQueue queue = new UnorderedQueue();
        Consumer consumer = new Consumer(queue);
        assertNotNull(consumer.getQueue());
    }

    /**
     * test if the polled message is null (ordered queue)
     */
    @Test
    public void TestOrderedPoll() {
        OrderedQueue queue = new OrderedQueue();
        Consumer consumer = new Consumer(queue);
        Message message = new Message("ana", "paul");
        queue.enqueue(message);
        assertNotNull(consumer.poll());
    }

    /**
     * test if the polled message is null (unordered queue)
     */
    @Test
    public void TestUnorderedPoll() {
        UnorderedQueue queue = new UnorderedQueue();
        Consumer consumer = new Consumer(queue);
        Message message = new Message("ana", "paul");
        queue.enqueue(message);
        assertNotNull(consumer.poll());
    }
}
