package client;

/**
 * Created by ktr on 2017/06/30.
 */
public enum Config {
    client("localhost", 2017, 8080);

    private String host;
    private int port;
    private int receivePort;

    Config(String host, int port, int receivePort) {
        this.host = host;
        this.port = port;
        this.receivePort = receivePort;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getReceivePort() {
        return receivePort;
    }
}
