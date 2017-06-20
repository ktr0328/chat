package server;

import common.Certification;
import common.Data;
import common.Flag;
import common.Message;
import server.database.Database;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ktr on 2017/06/21.
 */
public class Rooting {

    public static void rooting(Data data, Socket socket) throws IOException {
        if (data instanceof Certification) authorize((Certification) data, socket);
        else if (data instanceof Message) ifMessage((Message) data, socket);
    }

    private static void authorize(Certification data, Socket socket) throws IOException {
        boolean isAuthorized = Database.getUserList()
            .stream()
            .anyMatch(e -> e.getUsername().equals(data.getUsername()) && e.getPassword().equals(data.getPassword()));

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(new Flag(isAuthorized));
    }

    // TODO メソッド名要修正
    private static void ifMessage(Message data, Socket socket) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(data.getType());
    }
}
