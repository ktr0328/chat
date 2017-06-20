package client.mainPage.components;

import common.Message;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by ktr on 2017/06/20.
 */
public class MainCell extends ListCell<Message> {
    private VBox cellContainer;
    private Text from;
    private Text name;
    private Text message;
    private boolean empty;
    private Text time;

    private boolean bound = false;

    public MainCell() {
        initiateComponent();
    }

    private void initiateComponent() {
        this.cellContainer = new VBox(3);
        this.from = new Text();
        this.name = new Text();
        this.message = new Text();
        this.time = new Text();

        this.cellContainer.getChildren().addAll(name, from, message, time);
    }
    // TODO 見た目なんとかする
    @Override
    protected void updateItem(Message message, boolean empty) {
        this.message = new Text("Message");
        this.empty = empty;
        super.updateItem(message, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            this.from.setText("from " + message.getFrom());
            this.name.setText("username : " + message.getName());
            this.message.setText("message");
            this.time.setText("date : " + message.getTime());

            setGraphic(cellContainer);
        }
    }
}
