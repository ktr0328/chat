package client.mainPage;

import client.Config;
import client.loginPage.Login;
import common.Data;
import common.Message;
import common.MessageList;
import common.SearchKey;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ktr on 2017/06/30.
 */
public class Sender {
    private static Sender sender = new Sender();
    private String currentUser = Login.getCurrentUser();

    private Sender() {

    }

    public Data send(Data data) throws ClassNotFoundException, IOException {
        Socket socket = new Socket(Config.client.getHost(), Config.client.getPort());
        new ObjectOutputStream(socket.getOutputStream()).writeObject(data);

        Data d = (Data) new ObjectInputStream(socket.getInputStream()).readObject();

        socket.close();
        return d;
    }

    List<Message> getAllMessage() {
        SearchKey searchKey = new SearchKey(this.currentUser);
        MessageList messageList;
        try {
            messageList = (MessageList) send(searchKey);
        } catch (ClassNotFoundException | IOException e) {
            messageList = new MessageList(new ArrayList<>());
        }

        return Optional.ofNullable(messageList.getMessageList()).orElse(new ArrayList<>());
    }

    public static Sender getSender() {
        return sender;
    }
}
