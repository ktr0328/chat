package client.mainPage;

import common.Message;
import client.mainPage.components.MainCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Message> mainListView;
    public ListView<String> userListView;
    public TextArea messageArea;

    private ObservableList<Message> messages = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMainListView();

        messages.add(new Message("fromB", "あうあう", "message2", "2017/06/19", 1));
        userListView.getItems().add("ユーザーリスト");
    }

    private void initializeMainListView() {
        mainListView.setCellFactory(e -> new MainCell());
        mainListView.setItems(messages);
    }

    public void pressedEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !keyEvent.isShiftDown()) {
            messageArea.setText("");
        } else if (keyEvent.getCode() == KeyCode.ENTER && keyEvent.isShiftDown()) {
            messageArea.appendText("\r\n");
        }
    }
}
