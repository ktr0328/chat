package client.mainPage;

import client.Config;
import client.loginPage.Login;
import common.Message;
import common.MessageList;
import common.SearchKey;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ktr on 2017/06/30.
 */
public class MessageQuery {
    private static MessageQuery messageQuery = new MessageQuery();
    private String currentUser = Login.getCurrentUser();

    private MessageQuery() {

    }

    public List<Message> getAllMessage() {
        MessageList messageList;
        SearchKey searchKey = new SearchKey(this.currentUser);

        try(Socket socket = new Socket(Config.client.getHost(), Config.client.getPort())) {
            new ObjectOutputStream(socket.getOutputStream()).writeObject(searchKey);

            messageList = (MessageList) new ObjectInputStream(socket.getInputStream()).readObject();
        } catch (ClassNotFoundException | IOException e) {
            messageList = new MessageList(null);
        }

        return Optional.ofNullable(messageList.getMessageList()).orElse(new ArrayList<>());
    }

    public static MessageQuery getMessageQuery() {
        return messageQuery;
    }
}
