package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/27.
 */
public class SearchKey extends Data implements Serializable {
    String type;

    String date;
    String name;
    String from;
    int room;

    /**
     * 日付の差分を取るキーを生成
     */
    public SearchKey(String date) {
        this.type = "date";
        this.date = date;
    }

    /**
     * roomでキーを生成
     */
    public SearchKey(int room) {
        this.type = "room";
        this.room = room;
    }
}
