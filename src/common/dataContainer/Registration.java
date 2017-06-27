package common.dataContainer;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/21.
 */
public class Registration extends Data implements Serializable {
    private String username;
    private String password;

    /**
     *  登録キー
     */
    public Registration(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
