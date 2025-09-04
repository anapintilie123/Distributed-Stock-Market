package nl.rug.aoop.networking.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NetworkMessageTest {

    @Test
    public void testConstructor(){
       assertThrows(IllegalArgumentException.class ,() ->new NetworkMessage(null,"ana"));
       assertThrows(IllegalArgumentException.class ,() -> new NetworkMessage("ana",null));
    }
    @Test
    public void testToJSONString() {
        NetworkMessage message = new NetworkMessage("aa","nn");
        String newMess = message.toJSONString();
        NetworkMessage message2 = message.fromJSONString(newMess);
        assertEquals(message.getHeader(),message2.getHeader());
        assertEquals(message.getBody(),message2.getBody());

    }

    @Test
    public void testNullMessageToJson(){
        NetworkMessage message = null;
       assertThrows(NullPointerException.class ,()-> message.toJSONString());
    }

    @Test
    public void testNullMessageJsonToMessage(){
        NetworkMessage message = null;
        assertThrows(NullPointerException.class ,()-> message.fromJSONString(null));
    }
}