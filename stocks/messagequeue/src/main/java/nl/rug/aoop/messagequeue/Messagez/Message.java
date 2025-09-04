package nl.rug.aoop.messagequeue.Messagez;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Class is for constructing messages.
 */
@Getter

public class Message implements Comparable{
    private final String header;
    private final String body;
    private final LocalDateTime timeStamp;

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Message.class,
            new JsonAdapter().nullSafe()).create();

    /**
     * Constructor for a message.
     */
    public Message() {
        this.header = null;
        this.body = null;
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Constructor for a message.
     *
     * @param header unique value to identify a message.
     * @param body   content of the message.
     */

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Message constructor for message with time.
     *
     * @param header header of message.
     * @param body body of message.
     * @param time time stamp of message.
     */

    public Message(String header, String body, LocalDateTime time) {
        this.header = header;
        this.body = body;
        this.timeStamp = time;
    }

    /**
     * used in testing to print out a message.
     */
    public void printMessage() {
        System.out.println(this.header + ": " + this.body + ": " + this.timeStamp);
    }

    /**
     * converts string to json.
     *
     * @return returns json.
     */
    public String toJSONString() {
        return GSON.toJson(this);
    }

    /**
     * converts json to string.
     *
     * @param JSONString the json.
     * @return a string.
     */
    public static  Message fromJSONString(String JSONString) {
        Message message;
        try {
            message = GSON.fromJson(JSONString, Message.class);
        } catch (JsonSyntaxException ex) {
            return null;
        }
        return message;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

