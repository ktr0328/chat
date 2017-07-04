package client.mainPage;

import client.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ktr on 2017/07/03.
 */
public class Receiver extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(Config.client.getReceivePort());
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try {
                    System.out.println(new ObjectInputStream(socket.getInputStream()).readObject().getClass());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
