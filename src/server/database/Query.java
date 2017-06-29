package server.database;

import common.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ktr on 2017/06/26.
 */
public class Query {
    private static Query query = new Query();
    private Database db = Database.getDb();

    public static Query getInstance() {
        return query;
    }

    public List<Message> getAllMessages(String currentUser) {
        return db.getMessageList();
    }

    /**
     *  日付で差分の取得
     */
    public List<Message> getListFilteredInDate(String currentUser, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return db.getMessageList().stream()
            .filter(e -> {
                long a = 0;
                long b = 0;
                try {
                    a = sdf.parse(e.getTime()).getTime();
                    b = sdf.parse(date).getTime();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                return a > b;
            })
            .collect(Collectors.toList());
    }

    /**
     * Roomでフィルター
     */
    public List<Message> getListFilteredInRoom(String currentUser, int room) {
        return db.getMessageList().stream()
            .filter(e -> e.getRoom() == room)
            .collect(Collectors.toList());
    }
}
