package common.dataContainer;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/21.
 */
public class Flag extends Data implements Serializable {
    private boolean isTrue;

    /**
     * True or Falseを送信するためのクラス
     * @param isTrue flag
     */
    public Flag(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
