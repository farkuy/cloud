import common.client.Server;
import common.configs.Config;

public class Main {

    public static void main(String[] args) {
        Config config = Config.load();
        var port = config.getPort();

        Server.startServer(port);
    }
}