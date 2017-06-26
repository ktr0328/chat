package server.database;

import common.Message;

import java.util.List;

/**
 * Created by ktr on 2017/06/26.
 */
public class Query {
    private static Query query = new Query();
    private Database db = Database.getDb();

    public static Query getInstance() {
        return query;
    }

    public List<Message> getFilteredMessageList(String key) {
        return null;
    }
}
