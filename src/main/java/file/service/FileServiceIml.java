package file.service;

import com.sun.net.httpserver.HttpExchange;
import common.client.exceptions.BadRequestException;
import common.utils.logger.Logger;
import file.storage.LocalFileStorage;

import java.io.IOException;
import java.io.InputStream;

public class FileServiceIml implements FileService {

    public static void save(HttpExchange exchange) {
        String fileName = exchange.getRequestHeaders()
                .getFirst("X-File-Name");

        String contentLength = exchange.getRequestHeaders()
                .getFirst("Content-Length");
        long size = contentLength != null
                ? Long.parseLong(contentLength)
                : -1;

        try (InputStream input = exchange.getRequestBody()) {
            LocalFileStorage.save(fileName, input, size);
        } catch (IOException e) {
            throw new BadRequestException("Ошибка чтения файла", e.getMessage());
        }
    }
}
