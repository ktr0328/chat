package client;

/**
 * Created by ktr on 2017/06/30.
 */
public enum Config {
    client("localhost", 2017);

    private String host;
    private int port;

    Config(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
