package nl.rug.aoop.messagequeue.Messagez;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestMessage {

    /**
     * test is field Key is null after being initialised in the constructor
     */
    @Test
    public void testEnqueueMessageKey() {
        Message message = new Message("ana", "paul");
        assertNotNull(message.getHeader());
    }

    /**
     * test is field VAlue is null after being initialised in the constructor
     */
    @Test
    public void testEnqueueMessageValue() {
        Message message = new Message("ana", "paul");
        assertNotNull(message.getBody());
    }

    /**
     * test is field TimeStamp is null after being initialised in the constructor
     */
    @Test
    public void testEnqueueMessageTime() {
        Message message = new Message("ana", "paul");
        assertNotNull(message.getTimeStamp());
    }

}