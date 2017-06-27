package client.loginPage;

import common.dataContainer.Flag;
import common.dataContainer.Registration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ktr on 2017/06/21.
 */
class Register {
    private String username;
    private String password;

    Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean register(String host, int port) {
        boolean flag = false;
        try (Socket socket = new Socket(host, port)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Username : " + username
                + "\r\nPassword : " + password, ButtonType.OK, ButtonType.CANCEL);
            alert.setHeaderText("登録確認");

            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                new ObjectOutputStream(socket.getOutputStream()).writeObject(new Registration(username, password));

                Flag isNotEmpty = (Flag) new ObjectInputStream(socket.getInputStream()).readObject();
                flag = isNotEmpty.isTrue();

                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            flag = false;
        }

        return flag;
    }
}
