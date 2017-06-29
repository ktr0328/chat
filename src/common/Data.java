package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/20.
 */

/**
 * データを送るための抽象クラス
 * <p>
 * Serializableが必要
 */
public abstract class Data implements Serializable {
    String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

}
