package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/20.
 */
public class Message extends Data implements Serializable {
    private String message;
    private String from;
    private String name;
    private String time;
    private int room;

    public Message(String from, String name, String message, String time, int room) {
        this.from = from;
        this.name = name;
        this.message = message;
        this.time = time;
        this.room = room;
    }

    @Override
    public String toString() {
        return this.name + " : ";
    }

    public String getFrom() {
        return from;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getRoom() {
        return room;
    }

    public String getMessage() {
        return message;
    }
}
