package nl.rug.aoop.messagequeue.Messagez;

import nl.rug.aoop.command.CommandHandler;
import nl.rug.aoop.command.ICommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestMqMessageHandler {

    @Test
    public void testNullCommandHandler(){
        assertThrows(IllegalArgumentException.class ,()-> new MqMessageHandler(null));
    }

    @Test
    public void testNullMessageToMap(){
        ICommand command = Mockito.mock(ICommand.class);
        CommandHandler commandHandler = new CommandHandler("test",command);
        MqMessageHandler handler = new MqMessageHandler(commandHandler);
        assertThrows(IllegalArgumentException.class ,()-> handler.initMap(null));
    }


}