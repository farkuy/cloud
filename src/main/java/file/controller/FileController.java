package file.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.client.CrudOperation;
import common.client.HttpResponseCustom;
import common.utils.logger.Logger;
import file.service.FileServiceIml;

import java.io.IOException;
import java.util.HashMap;

import static common.client.HttpResponseCustom.sendResponse;

public final class FileController implements HttpHandler {

    private final HashMap<CrudOperation, HttpResponseCustom.CrudHandler> methodsAndParams =
            new HashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        methodsAndParams.put(CrudOperation.POST, () -> postUser(exchange));

        HttpResponseCustom.handle(exchange, methodsAndParams);
    }

    private static void postUser(HttpExchange exchange) throws IOException {
        FileServiceIml.writeFile(exchange);
        sendResponse(exchange, 200, "Файл сохранен");

        Logger.info("Файл сохранен");
    }
}
