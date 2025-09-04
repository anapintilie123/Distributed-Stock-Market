package nl.rug.aoop.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandHandlerTest {

    @Test
    public void testRegisterCommand() {
        assertThrows(IllegalArgumentException.class ,()->new CommandHandler(null, null));

    }

    @Test
    public void testNullOptions(){
        ICommand command = Mockito.mock(ICommand.class);
        CommandHandler handler = new CommandHandler("MqPut",command);
        assertThrows(IllegalArgumentException.class ,()->handler.executeCommand(null,new HashMap<>()));
    }

    @Test
    public void testNullCmdName(){
        ICommand command = Mockito.mock(ICommand.class);
        CommandHandler handler = new CommandHandler("MqPut",command);
        assertThrows(IllegalArgumentException.class ,() ->handler.executeCommand(null, new HashMap<>()));
    }

    @Test
    public void testWrongKey(){
        ICommand command = Mockito.mock(ICommand.class);
        CommandHandler handler = new CommandHandler("ana",command);
        Map<String, Object> options = new HashMap<String, Object> ();
        assertThrows(IllegalArgumentException.class ,() -> handler.executeCommand("MqPut",options));
    }

}