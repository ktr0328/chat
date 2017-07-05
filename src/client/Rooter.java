package client;

import client.mainPage.Controller;
import common.Data;
import common.Message;

/**
 * Created by ktr on 2017/07/03.
 */
public class Rooter {
    private static Rooter rooter = new Rooter();

    private Rooter() {
    }

    public void Rooting(Data data) {
        if (data instanceof Message) ifMessage((Message) data);
    }

    private void ifMessage(Message data) {
        if (!Controller.getMessages().contains(data))
            Controller.getMessages().add(data);
    }

    public static Rooter getRooter() {
        return rooter;
    }
}
