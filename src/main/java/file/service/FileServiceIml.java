package file.service;

import com.sun.net.httpserver.HttpExchange;
import common.client.constants.ApiPaths;
import common.client.exceptions.BadRequestException;
import file.storage.FileStorage;
import file.storage.LocalFileStorage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class FileServiceIml implements FileService {

    FileStorage localFileStorage = new LocalFileStorage();

    public void save(HttpExchange exchange) {
        String fileName = getFileName(exchange);
        long size = getSize(exchange);
        Path path = getPath(exchange);

        try (InputStream input = exchange.getRequestBody()) {
            localFileStorage.save(fileName, path, input, size);
        } catch (IOException e) {
            throw new BadRequestException("Ошибка чтения файла", e.getMessage());
        }
    }

    private String getFileName(HttpExchange exchange) {
        String fileName = exchange.getRequestHeaders()
                .getFirst("X-File-Name");

        if (fileName == null || fileName.isBlank()) {
            throw new BadRequestException("Имя не может быть пустым", "Проблема с извлечением имени файла");
        }

        return fileName;
    }

    private long getSize(HttpExchange exchange) {
        String contentLength = exchange.getRequestHeaders()
                .getFirst("Content-Length");
        long size = contentLength != null
                ? Long.parseLong(contentLength)
                : -1;

        return size;
    }

    private Path getPath(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();

        String subPath = null;
        if (path.startsWith(ApiPaths.FILE)) {
            subPath = path.substring(ApiPaths.FILE.length());
        }

        if (subPath == null && subPath.isBlank()) {
            throw new BadRequestException("Пустой путь", "Проблема с извлечением пути");
        }

        return Path.of(subPath);
    }
}
