package nl.rug.aoop.messagequeue.Producerz;

import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.messagequeue.Queuez.OrderedQueue;
import nl.rug.aoop.messagequeue.Queuez.UnorderedQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestProducer {


    /**
     * check if instnace Producer is implemented correctly when usinf an Unordered queue
     */
    @Test
    public void constructorUnorderedQueue() {
        UnorderedQueue queue = new UnorderedQueue();
        assertNotNull(new Producer(queue));
    }

    /**
     * check if instnace Producer is implemented correctly when usinf an Ordered queue
     */
    @Test
    public void constructorOrderedQueue() {
        OrderedQueue queue = new OrderedQueue();
        assertNotNull(new Producer(queue));
    }


    /**
     * checks if programme throws an error if a producer is initialised with a null parameter
     */
    @Test
    public void FieldIsNullCheck(){
        assertThrows(NullPointerException.class ,() -> new Producer(null));
    }


    /**
     * checks if it throws an error if null object is given as a parameter to a producer(unordered queue)
     */
    @Test
    public void PuttingANullMessageUnordered(){
        assertThrows(NullPointerException.class ,() -> {
            Producer producer = new Producer(new UnorderedQueue());
           producer.put(null);
        });
    }

    /**
     * checks if it throws an error if null object is given as a parameter to a producer(Ordered queue)
     */
    @Test
    public void PuttingANullMessageOrdered(){
        assertThrows(NullPointerException.class ,() -> {
            Producer producer = new Producer(new OrderedQueue());
            producer.put(null);
        });
    }

    /**
     * checks if a message is succesully enqueued(unordered queue)
     */
    @Test
    public void EnqueueUnordered(){
        Message message = new Message("ana","bau");
        UnorderedQueue queue = new UnorderedQueue();
        Producer producer = new Producer(queue);
        int size1 = queue.getSize();
        producer.put(message);
        assertEquals(size1 + 1, queue.getSize());
    }
    /**
     * checks if a message is succesully enqueued(ordered queue)
     */
    @Test
    public void EnqueueOrdered(){
      Message message = new Message("ana","bau");
      OrderedQueue queue = new OrderedQueue();
      Producer producer = new Producer(queue);
      int size1 = queue.getSize();
      producer.put(message);
      assertEquals(size1 + 1, queue.getSize());
    }
}