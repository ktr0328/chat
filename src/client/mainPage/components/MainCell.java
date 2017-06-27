package client.mainPage.components;

import common.dataContainer.Message;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by ktr on 2017/06/20.
 */
public class MainCell extends ListCell<Message> {
    private BorderPane cellContainer;
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
        cellContainer = new BorderPane();
        VBox box = new VBox(5);

        name = new Text();
        from = new Text();
        message = new Text();
        time = new Text();

        from.setFont(Font.font("Helvetica", FontWeight.BOLD, 13));
        message.setStyle("-fx-font-size: 16");
        box.setStyle("-fx-padding: 0 0 0 20");
        cellContainer.setLeft(name);
        from.setTextAlignment(TextAlignment.LEFT);
        box.getChildren().addAll(from, message);
        time.setFill(Paint.valueOf("darkgray"));
        cellContainer.setCenter(box);
        cellContainer.setRight(time);
    }

    // TODO 見た目なんとかする
    @Override
    protected void updateItem(Message message, boolean empty) {
        this.empty = empty;
        super.updateItem(message, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            this.name.setText("画像");
            this.from.setText("from " + message.getFrom());
            this.message.setText(message.getMessage());
            this.time.setText("@" + message.getTime());

            setGraphic(cellContainer);
        }
    }
}
