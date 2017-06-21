package server;

import common.*;
import server.database.Database;
import server.database.UserData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by ktr on 2017/06/21.
 */
class Rooting {

    /**
     *  送信されてきたものをルーティングし個別の処理を行う
     */
    static void rooting(Data data, Socket socket) throws IOException {
        if (data instanceof Certification) authorize((Certification) data, socket);
        else if (data instanceof Registration) register((Registration) data, socket);
        else if (data instanceof Message) ifMessage((Message) data, socket);
    }

    private static void authorize(Certification data, Socket socket) throws IOException {
        boolean isAuthorized = Database.getUserList()
            .stream()
            .anyMatch(user -> user.getUsername().equals(data.getUsername()) && user.getPassword().equals(data.getPassword()));

        new ObjectOutputStream(socket.getOutputStream()).writeObject(new Flag(isAuthorized));
    }

    private static void register(Registration data, Socket socket) throws IOException {
        boolean duplicated = Database.getUserList()
            .stream()
            .anyMatch(user -> user.getUsername().equals(data.getUsername()));

        if (!duplicated) {
            Database.getUserList().add(new UserData(data.getUsername(), data.getPassword()));

            Database.writeFile(Database.getUserDataPath(),
                Database.getUserList().stream()
                .map(user -> user.getId() + "," + user.getUsername() + "," + user.getPassword())
                .collect(Collectors.toCollection(ArrayList::new))
            );
        }
        new ObjectOutputStream(socket.getOutputStream()).writeObject(new Flag(!duplicated));
    }

    // TODO メソッド名要修正
    private static void ifMessage(Message data, Socket socket) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(data.getClass());
    }
}
