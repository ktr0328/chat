package common;

import java.io.Serializable;

/**
 * Created by ktr on 2017/06/27.
 */
public class SearchKey extends Data implements Serializable {
    String type;
    String currentUser;

    String date;
    String name;
    String from;
    int room;

    public SearchKey(String currentUser) {
        this.currentUser = currentUser;
        this.type = "all";
    }

    /**
     * 日付の差分を取るキーを生成
     */
    public SearchKey(String currentUser,String date) {
        this.currentUser = currentUser;
        this.type = "date";
        this.date = date;
    }

    /**
     * roomでキーを生成
     */
    public SearchKey(String currentUser, int room) {
        this.currentUser = currentUser;
        this.type = "room";
        this.room = room;
    }
}
