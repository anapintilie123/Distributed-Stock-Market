package nl.rug.aoop.messagequeue.Messagez;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestMqPutCommand {

    @Test
    void testConstructor(){
        assertThrows(IllegalArgumentException.class , ()-> new MqPutCommand(null));
    }

}