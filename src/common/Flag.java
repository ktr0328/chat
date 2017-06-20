package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/21.
 */
public class Flag extends Data implements Serializable {
    private boolean isAuthorized;

    public Flag(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
}
