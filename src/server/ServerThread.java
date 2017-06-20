package server;

import common.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by ktr on 2017/06/20.
 */
public class ServerThread extends Thread {
    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream())) {
            Rooting.rooting((Data) ois.readObject(), socket);

            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("クラスが見つからない");
        }
    }
}