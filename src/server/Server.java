package server;

import server.database.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ktr on 2017/06/19.
 */
class Server {
    private int port;
    private Database database;
    private ExecutorService exec;

    Server(int port) {
        this.port = port;
        this.database = Database.getDb();
        exec = Executors.newFixedThreadPool(3);
    }

    void runServer() {
        try (ServerSocket server = new ServerSocket(this.port)) {
            System.out.println("server running...");

            while (!server.isClosed()) {
                try {
                    final Socket socket = server.accept();
                    exec.execute(new ServerThread(socket));
                } catch (IOException e) {
                    System.err.println("なんかおかしい");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
