package server.database;

/**
 * Created by ktr on 2017/06/21.
 */
public class UserData {
    private int id;
    private String username;
    private String password;

    UserData(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
