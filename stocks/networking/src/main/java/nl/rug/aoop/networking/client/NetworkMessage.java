package nl.rug.aoop.networking.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

/**
 * A message that can contain another message to be sent over the network.
 */
@Getter
public class NetworkMessage {

    private final String header;
    private final String body;
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Constructor for a NetworkMessage.
     *
     * @param header key for message.
     * @param body body of message.
     */

    public NetworkMessage(String header, String body) {
        if(header == null) {
            throw new IllegalArgumentException("Null header for NetworkMessage");
        }

        if (body ==null){
            throw new IllegalArgumentException("Null body for NetworkMessage");
        }
        this.header = header;
        this.body = body;
    }

    /**
     * Converts string to json.
     *
     * @return returns a json string.
     */
    public String toJSONString() {
        return GSON.toJson(this);
    }

    /**
     * Converts json to string.
     *
     * @param JSONString the string to be converted.
     * @return the string.
     */
    public NetworkMessage fromJSONString(String JSONString) {

        return GSON.fromJson(JSONString, NetworkMessage.class);
    }

}
