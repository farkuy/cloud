package common.client;

import com.sun.net.httpserver.HttpServer;
import common.utils.logger.Logger;
import user.controller.UserController;

import java.io.IOException;
import java.net.InetSocketAddress;

public final class Server {
    public static HttpServer startServer(int port) {
        HttpServer server;
        String portStr = String.valueOf(port);

        try {
            server = HttpServer.create(
                    new InetSocketAddress(port),
                    0
            );
        } catch (IOException e) {
            Logger.error("Server not create at port =", portStr, e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            createContext(server);

            server.setExecutor(null);
            server.start();

            Logger.info("Server started at port =",portStr);
        } catch (RuntimeException e) {
            Logger.error("Server problem start at port =", portStr, e.getMessage());
            throw new RuntimeException(e);
        }

        return server;
    }

    private static void createContext(HttpServer server) {
        server.createContext("/user", new UserController());
    }
}
