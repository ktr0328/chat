package client.mainPage;

import client.mainPage.components.MainCell;
import client.mainPage.components.SubmitTextArea;
import common.Message;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Message> mainListView;
    public ListView<String> userListView;
    public SubmitTextArea messageArea;

    private static ObservableList<Message> messages = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMainListView();

        // TODO test
        userListView.getItems().add("ユーザーリスト");

        messages.addAll(Sender.getSender().getAllMessage());
    }

    private void initializeMainListView() {
        mainListView.setCellFactory(e -> new MainCell());
        mainListView.setItems(messages);
        messageArea.setText("");
        messageArea.setPromptText("input messages ...");

        mainListView.getItems().addListener((ListChangeListener<Message>) c -> mainListView.scrollTo(mainListView.getItems().size()));
    }

    public void onPressedEnterKey(KeyEvent keyEvent) {
        String line = System.getProperty("line.separator");
        if (keyEvent.getCode() == KeyCode.ENTER && keyEvent.isShiftDown()) {
            messageArea.appendText(line + line); // 改行させるためのフラグ ref SubmitTextArea
        }
    }

    public static ObservableList<Message> getMessages() {
        return messages;
    }
}
