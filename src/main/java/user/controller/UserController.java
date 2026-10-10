package user.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.client.CrudOperation;
import common.client.HttpResponseCustom;
import common.utils.logger.Logger;

import java.io.IOException;
import java.util.HashMap;

import static common.client.HttpResponseCustom.sendJsonResponse;

public final class UserController implements HttpHandler {

    private final HashMap<CrudOperation, HttpResponseCustom.CrudHandler> methodsAndParams =
            new HashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        methodsAndParams.put(CrudOperation.GET, () -> getUer(exchange));

        HttpResponseCustom.handle(exchange, methodsAndParams);
    }

    static void getUer(HttpExchange exchange) throws IOException {
        sendJsonResponse(exchange, 200, """
                {
                        "id": 1,
                        "name": "Ivan Petrov",
                        "email": "ivan@example.com",
                        "role": "Admin"
                }
            """
        );

        Logger.info("Информация отпавленна");
    }
}
