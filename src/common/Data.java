package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/20.
 */

// DataContainerに載せるための抽象クラス
public abstract class Data implements Serializable {
    // TODO これ必要？
    String type;

    public String getType() {
        return type;
    }

}