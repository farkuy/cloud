package common.client;

import com.sun.net.httpserver.HttpServer;
import common.client.constants.ApiPaths;
import common.utils.logger.Logger;
import file.controller.FileController;
import file.service.FileServiceImpl;
import file.storage.LocalFileStorage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public final class Server {
    public static HttpServer startServer(int port) {
        HttpServer server = createServer(port);
        String portStr = String.valueOf(port);

        try {
            createAllContext(server);
            server.setExecutor(null);
            server.start();

            Logger.info("Server started at port =",portStr);
        } catch (RuntimeException e) {
            Logger.error("Server problem start at port =", portStr, e.getMessage());
            throw new RuntimeException(e);
        }

        return server;
    }

    private static HttpServer createServer(int port) {
        HttpServer server;
        try {
            server = HttpServer.create(
                    new InetSocketAddress(port),
                    0
            );
        } catch (IOException e) {
            Logger.error("Server not create at port =", String.valueOf(port), e.getMessage());
            throw new RuntimeException(e);
        }

        return server;
    }

    private static void createAllContext(HttpServer server) {
        Path storageRoot = Path.of(System.getProperty("file.storage.root", "storage"));
        var fileStorage = new LocalFileStorage(storageRoot);
        var fileService = new FileServiceImpl(fileStorage);

        server.createContext(ApiPaths.FILE, new FileController(fileService));
    }
}
