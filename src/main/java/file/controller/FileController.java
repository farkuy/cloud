package file.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.client.CrudOperation;
import common.client.HttpResponseCustom;
import common.utils.logger.Logger;
import file.service.FileService;
import file.service.FileServiceIml;

import java.io.IOException;
import java.util.HashMap;

import static common.client.HttpResponseCustom.sendResponse;

public final class FileController implements HttpHandler {

    FileService fileService = new FileServiceIml();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HashMap<CrudOperation, HttpResponseCustom.CrudHandler> methodsAndParams =
                new HashMap<>();

        methodsAndParams.put(CrudOperation.POST, () -> save(exchange));

        HttpResponseCustom.handle(exchange, methodsAndParams);
    }

    private void save(HttpExchange exchange) throws IOException {
        fileService.save(exchange);
        sendResponse(exchange, 200, "Файл сохранен");

        Logger.info("Файл сохранен");
    }
}
