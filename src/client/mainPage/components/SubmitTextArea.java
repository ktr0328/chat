package client.mainPage.components;

import client.loginPage.Login;
import client.mainPage.Controller;
import client.mainPage.Sender;
import common.Message;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ktr on 2017/07/01.
 */
public class SubmitTextArea extends TextArea {

    @Override
    public void replaceText(int start, int end, String text) {
        String line = System.getProperty("line.separator");

        if (text.equals(line)) {
            Message message = new Message(
                Login.getCurrentUser(), "all", this.getText(),
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()), 1
            );
            Controller.getMessages().add(message);
            try {
                Sender.getSender().send(message);
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            this.setText("");
        } else if (text.equals(line + line)) {
            super.replaceText(start, end, line);
        } else {
            super.replaceText(start, end, text);
        }
    }
}
