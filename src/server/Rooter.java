package server;

import common.*;
import server.database.Database;
import server.database.Query;
import server.database.UserData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ktr on 2017/06/21.
 */
class Rooter {
    private Database db = Database.getDb();
    private static Rooter rooter = new Rooter();

    private Rooter() {
    }

    /**
     * 送信されてきたものをルーティングし個別の処理を行う
     */
    void rooting(Data data, Socket socket) throws IOException {
        if (data instanceof Certification) authorize((Certification) data, socket);
        else if (data instanceof Registration) register((Registration) data, socket);
        else if (data instanceof Message) ifMessage((Message) data, socket);
        else if (data instanceof SearchKey) isQuery((SearchKey) data, socket);
    }

    private void authorize(Certification data, Socket socket) throws IOException {
        boolean isAuthorized = db.getUserList()
            .stream()
            .anyMatch(user -> user.getUsername().equals(data.getUsername()) && user.getPassword().equals(data.getPassword()));

        new ObjectOutputStream(socket.getOutputStream()).writeObject(new Flag(isAuthorized));
    }

    private void register(Registration data, Socket socket) throws IOException {
        boolean isNotDuplicate = db.getUserList()
            .stream()
            .noneMatch(user -> user.getUsername().equals(data.getUsername()));

        Pattern p = Pattern.compile("[a-z]+|[A-Z]+|\\d+");
        if (isNotDuplicate && p.matcher(data.getUsername()).matches() && p.matcher(data.getPassword()).matches()) {
            db.getUserList().add(new UserData(data.getUsername(), data.getPassword()));

            db.writeFile(db.getUserDataPath(),
                db.getUserList().stream()
                    .map(user -> user.getId() + "," + user.getUsername() + "," + user.getPassword())
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }
        new ObjectOutputStream(socket.getOutputStream()).writeObject(new Flag(!isNotDuplicate));
    }

    private void ifMessage(Message data, Socket socket) throws IOException {
        db.writeFile(db.getLogDataPath(),
            db.getMessageList().stream()
                .map(msg -> msg.getFrom() + "," + msg.getName() + "," + msg.getMessage() + "," + msg.getTime() + "," + msg.getRoom())
                .collect(Collectors.toCollection(ArrayList::new))
        );
        new ObjectOutputStream(socket.getOutputStream()).writeObject(new MessageList(db.getEachLogData(data.getCurrentUser())));
        System.out.println(data.toString());
    }

    private void isQuery(SearchKey data, Socket socket) throws IOException {
        List<Message> list = Query.getInstance().getAllMessages(data.getCurrentUser());
        new ObjectOutputStream(socket.getOutputStream()).writeObject(new MessageList(list));
    }

    public static Rooter getRooter() {
        return rooter;
    }
}
