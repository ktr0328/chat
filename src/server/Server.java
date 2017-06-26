package server;

import server.database.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ktr on 2017/06/19.
 */
class Server {
    private int port;
    private Database database;
    private List<Thread> threads;

    Server(int port) {
        this.port = port;
        this.database = Database.getDb();
        this.threads = new ArrayList<>();
    }

    void runServer() {
        try (ServerSocket server = new ServerSocket(this.port)) {
            System.out.println("server running...");

            while (!server.isClosed()) {
                try (Socket socket = server.accept()) {
                    Thread th = new ServerThread(socket);
                    th.start();
                    th.join();
                } catch (IOException e) {
                    System.out.println("なんかおかしい");
                } catch (InterruptedException e) {
                    System.err.println("thread error");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
