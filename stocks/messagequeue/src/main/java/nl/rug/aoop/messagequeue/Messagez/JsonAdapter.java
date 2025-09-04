package nl.rug.aoop.messagequeue.Messagez;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Makes it possible to convert a message into a Json.
 */
public class JsonAdapter extends TypeAdapter<Message> {

    /**
     * body.
     */
    public static final String BODY_FIELD = "body";
    /**
     * header.
     */
    public static final String HEADER_FIELD = "header";
    /**
     * time.
     */
    public static final String TIME_FIELD = "time";

    @Override
    public Message read(JsonReader reader) throws IOException {
        reader.beginObject();
        String header = null;
        String body = null;
        LocalDateTime time = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            String fieldName = null;
            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }
            if (fieldName == null) {
                continue;
            }
            switch (fieldName) {
                case HEADER_FIELD -> {
                    header = reader.nextString();
                }
                case BODY_FIELD -> {
                    body = reader.nextString();
                }
                case TIME_FIELD -> {
                    time = LocalDateTime.parse(reader.nextString());
                }
            }
        }
        reader.endObject();
        return new Message(header, body, time);
    }

    @Override
    public void write(JsonWriter writer, Message jsonExample) throws IOException {
        writer.beginObject();
        writer.name(HEADER_FIELD);
        writer.value(jsonExample.getHeader());
        writer.name(BODY_FIELD);
        writer.value(jsonExample.getBody());
        writer.name(TIME_FIELD);
        writer.value(jsonExample.getTimeStamp().toString());
        writer.endObject();
    }
}

