package server;

/**
 * Created by ktr on 2017/06/14.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(2017);
        server.runServer();
    }
}
