package client.loginPage;

import client.Main;
import common.Certification;
import common.Flag;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ktr on 2017/06/14.
 */
public class Login implements Initializable {

    @FXML
    public TextField username;
    public TextField password;
    public Button login;
    public Button register;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setPromptText("UserName...");
        password.setPromptText("PassWord...");

        login.setOnAction(e -> {
            if (Authorize()) Main.changeScene("root", 800, 600);
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "ログインできませんでした。", ButtonType.OK);
                alert.setHeaderText("認証エラー");
                alert.show();
            }
        });
        register.setOnAction(e -> Register());
    }

    private boolean Authorize() {
        boolean flag = false;
        try (
            Socket socket = new Socket("localhost", 2017);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ) {
            oos.writeObject(new Certification(username.getText(), password.getText()));
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Flag boo = (Flag) ois.readObject();
                flag = boo.isAuthorized();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    private void Register() {
        // TODO  認証 / rooting 機能
        if (true) Main.changeScene("root", 800, 600);
        else System.out.println("認証エラー");
    }
}
