package nl.rug.aoop.messagequeue.Messagez;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.CommandHandler;
import nl.rug.aoop.messagequeue.Queuez.MessageQueue;
import nl.rug.aoop.networking.client.ICommunicator;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * puts the fields of a message into a map.
 */
@Slf4j
public class MqMessageHandler implements MessageHandler {

    private MessageQueue queue;

    private CommandHandler commandHandler;
    @Getter
    private Map<String, ICommunicator> connectedTraders;

    /**
     * Constructor with Connected traders.
     * @param commandHandler the command handler.
     * @param connectedTraders the connected traders.
     */
    public MqMessageHandler(CommandHandler commandHandler,Map<String, ICommunicator> connectedTraders) {
        if(commandHandler == null){
            throw new IllegalArgumentException("Null commandHandler for NetworkMessage");
        }
        this.commandHandler = commandHandler;
        this.connectedTraders=connectedTraders;
    }

    /**
     * Constructor.
     * @param commandHandler the command handler.
     */
    public MqMessageHandler(CommandHandler commandHandler) {
        if(commandHandler == null){
            throw new IllegalArgumentException("Null commandHandler for NetworkMessage");
        }
        this.commandHandler = commandHandler;
    }

    /**
     * Initiates a map with the header body and communicator for the command handler.
     *
     * @param message message to be added.
     * @return a map.
     */
    public Map<String, Object> initMap(Message message) {
        if(message == null){
            throw new IllegalArgumentException();
        }
        Map<String, Object> options;
        options = new HashMap<>();
        options.put("header", message.getHeader());
        options.put("body", message.getBody());
        options.put("communicator", ICommunicator.class);
        return options;
    }

    /**
     * Converts message from json and gives it to command handler to be executed.
     *
     * @param message message to be executed.
     */
    @Override
    public void handleMessage(String message, ICommunicator communicator) {
        Message newMess = new Message();
        log.info("Received message" + message);
        if(message != null) {
            newMess = newMess.fromJSONString(message);
            Map<String, Object> options = initMap(newMess);
            String header = (String) options.get("header");
            if(header.equals("Subscribe")){
                String Id = (String) options.get("body");
                connectedTraders.put(Id,communicator);
            }else{
                commandHandler.executeCommand(header, options);
            }
        }
    }
}
