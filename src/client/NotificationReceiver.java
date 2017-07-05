package client;

import client.Config;
import common.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ktr on 2017/07/03.
 *
 * 通知的なものを非同期で受け取るため、ServerSocketをClient側でも起動する。
 */
public class NotificationReceiver extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(Config.client.getReceivePort());
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try {
                    Rooter.getRooter().Rooting((Data) new ObjectInputStream(socket.getInputStream()).readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
