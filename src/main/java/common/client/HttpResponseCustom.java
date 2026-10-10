package common.client;

import com.sun.net.httpserver.HttpExchange;
import common.utils.logger.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public final class HttpResponseCustom {

    @FunctionalInterface
    public interface CrudHandler {
        void execute() throws IOException;
    }

    public static void handle(HttpExchange exchange, HashMap<CrudOperation, CrudHandler> methodsAndParams) throws IOException {
        String method = exchange.getRequestMethod();
        CrudOperation operation = CrudOperation.fromHttpMethod(method);

        try {
            CrudHandler action = methodsAndParams.get(operation);
            action.execute();
        } catch (Exception e) {
            Logger.error("Ошибка сервера:", e.getMessage());
            sendResponse(exchange, 500, "Ошибка сервера: " + e.getMessage());
        }
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public static void sendJsonResponse(HttpExchange exchange, int statusCode, String jsonResponse) throws IOException {
        byte[] bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
