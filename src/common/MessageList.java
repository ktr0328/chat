package common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ktr on 2017/06/21.
 */
public class MessageList extends Data implements Serializable {
    private List<Message> messageList;

    public MessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
}
