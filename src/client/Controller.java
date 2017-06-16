package client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ListView<String> mainListView;
    public ListView<String> userListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainListView.getItems().add("チャット");
        userListView.getItems().add("ユーザーリスト");
    }
}
