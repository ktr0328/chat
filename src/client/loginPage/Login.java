package client.loginPage;

import client.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

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

    private String host = "localhost";
    private int port = 2017;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO テスト
        username.setText("admin");
        password.setText("admin");
        username.setPromptText("UserName : a-z A-z 0-9...");
        password.setPromptText("PassWord : a-z A-z 0-9...");

        login.setOnAction(e -> goNext(new Authorize(username.getText(), password.getText()).authorize(host, port),
            "ログインできませんでした。", "認証エラー"));

        register.setOnAction(e -> goNext(new Register(username.getText(), password.getText()).register(host, port),
            "重複或いは不正な文字列です。", "登録エラー"));
    }

    /**
     * 移動に必要なelementを渡し渡し認証されればメイン画面へ移動
     */
    private void goNext(boolean isTrue, String content, String header) {
        if (isTrue) {
            Main.changeScene("root", 800, 600);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, content, ButtonType.OK);
            alert.setHeaderText(header);
            alert.show();
        }
    }
}
