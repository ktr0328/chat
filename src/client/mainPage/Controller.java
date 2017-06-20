package client.mainPage;

import common.Message;
import client.mainPage.components.MainCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Message> mainListView;
    public ListView<String> userListView;

    private ObservableList<Message> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableList.add(new Message("fromA", "name", "message1", "2017/06/20", 1));
        initializeMainListView();

        mainListView.getItems().add(new Message("fromB", "あうあう", "message2", "2017/06/19", 1));
        userListView.getItems().add("ユーザーリスト");
    }

    private void initializeMainListView() {
        mainListView.setCellFactory(e -> new MainCell());
        mainListView.setItems(observableList);
    }
}
