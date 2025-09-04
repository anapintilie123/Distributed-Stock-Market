package nl.rug.aoop.messagequeue.Messagez;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.ICommand;
import nl.rug.aoop.messagequeue.Queuez.MessageQueue;
import nl.rug.aoop.messagequeue.Queuez.PriorityQueue;

import java.util.Map;

/**
 * puts a message into a queue.
 */
@Slf4j
public class MqPutCommand implements ICommand {
    @Getter
    private final MessageQueue queue;

    /**
     * Constructor.
     *
     * @param queue the queue.
     */
    public MqPutCommand(PriorityQueue queue) {
        if(queue == null){
            throw new IllegalArgumentException();
        }
        this.queue = queue;
    }

    /**
     * puts a message into queue.
     */

    @Override
    public void execute(Map<String, Object> options) {
        String JsonMessage = (String) options.get("body");
        Message message = Message.fromJSONString(JsonMessage);
        queue.enqueue(message);
    }
}
