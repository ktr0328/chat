package server.database;

import common.Message;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ktr on 2017/06/20.
 */
public class Database {
    private ArrayList<UserData> userList;
    private List<Message> messageList;

    private String userDataPath;
    private String logDataPath;

    private static Database db = new Database();

    private Database() {
        initialize();
    }

    private void initialize() {
        userDataPath = "src/server/database/userData.csv";
        logDataPath = "src/server/database/logData.csv";

        try {
            userList = getFile(userDataPath)
                .stream()
                .map(e -> e.split(","))
                .map(e -> new UserData(Integer.valueOf(e[0]), e[1], e[2]))
                .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            userList = new ArrayList<>();
            System.err.println("ファイルが見つかりません。");
        }

        try {
            messageList = getFile(logDataPath)
                .stream()
                .map(e -> e.split(","))
                .map(e -> new Message(e[0], e[1], e[2], e[3], Integer.parseInt(e[4])))
                .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            messageList = new ArrayList<>();
            System.err.println("ファイルが見つかりません。");
        }
    }

    private List<String> getFile(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toCollection(ArrayList::new));
    }

    public void writeFile(String path, List<String> list) throws IOException {
        Files.write(Paths.get(path), list, Charset.forName("UTF-8"));
    }

    public List<Message> getEachLogData(String currentUser) {
        // TODO
        return new ArrayList<>();
    }

    public List<UserData> getUserList() {
        return userList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public String getUserDataPath() {
        return userDataPath;
    }

    public String getLogDataPath() {
        return logDataPath;
    }

    public static Database getDb() {
        return db;
    }
}
