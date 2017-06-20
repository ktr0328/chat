package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/20.
 */
public class Certification extends Data implements Serializable {
    private String username;
    private String password;

    public Certification(String username, String password) {
        this.type = "authorize";
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
