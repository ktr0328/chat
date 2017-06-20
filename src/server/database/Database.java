package server.database;

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
    private static ArrayList<UserData> userList;
    private static List logData;

    private String userDataPath;
    private String logDataPath;

    public Database() {
        initialize();
    }

    private void initialize() {
        this.userDataPath = "src/server/database/userData.csv";
        this.logDataPath = "src/server/database/logData.csv";

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
            logData = getFile(logDataPath);
        } catch (IOException e) {
            logData = new ArrayList();
            System.err.println("ファイルが見つかりません。");
        }
    }

    private List<String> getFile(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.toCollection(ArrayList::new));
    }

    private void writeFile(String path, List<String> list) throws IOException {
        Files.write(Paths.get(path), list, Charset.forName("UTF-8"));
    }

    public static List<UserData> getUserList() {
        return userList;
    }

    public static List getLogData() {
        return logData;
    }
}
