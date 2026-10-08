import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static String resourcesPath = "application.properties";

    private int port;

    public Config(int port) {
        this.port = port;
    }

    public static Config load() {
        Properties properties = new Properties();

        int port = 8080;

        try (InputStream input = Config.class
                .getClassLoader()
                .getResourceAsStream(resourcesPath))
        {
            if (input == null) {
                throw new FileNotFoundException(String.format("Файл %s не найден!", resourcesPath));
            }

            properties.load(input);
            port = Integer.parseInt(properties.getProperty("server.port"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new Config(port);
    }

    int getPort() {
        return port;
    }
}
