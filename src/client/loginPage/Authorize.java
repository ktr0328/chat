package client.loginPage;

import common.Certification;
import common.Flag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ktr on 2017/06/21.
 */
public class Authorize {
    private String username;
    private String password;

    /**
     * 認証キーを生成し送信するクラス
     */
    Authorize(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean authorize(String host, int port) {
        boolean flag;

        try (Socket socket = new Socket(host, port)) {
            new ObjectOutputStream(socket.getOutputStream()).writeObject(new Certification(username, password));

            Flag isAuthorized = (Flag) new ObjectInputStream(socket.getInputStream()).readObject();
            flag = isAuthorized.isTrue();

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            flag = false;
        }

        return flag;
    }
}
