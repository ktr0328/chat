package server.database;

/**
 * Created by ktr on 2017/06/21.
 */
public class UserData {
    private static int serial = 0;
    private int id;
    private String username;
    private String password;

    /**
     * Userクラス
     * <p>
     * 認証や登録に使う
     */
    UserData(int id, String username, String password) {
        this.id = id;
        serial = id;
        this.username = username;
        this.password = password;
    }

    public UserData(String username, String password) {
        this.id = ++serial;
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
