package file.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import common.client.CrudOperation;
import common.client.HttpResponseCustom;
import common.client.constants.ApiPaths;
import common.client.exceptions.BadRequestException;
import common.client.exceptions.InternalServerErrorException;
import common.utils.logger.Logger;
import file.exception.FileStorageException;
import file.exception.FileValidationException;
import file.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

import static common.client.HttpResponseCustom.sendResponse;

public final class FileController implements HttpHandler {

    private static final String FILE_NAME_HEADER = "X-File-Name";

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = Objects.requireNonNull(fileService, "fileService");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HashMap<CrudOperation, HttpResponseCustom.CrudHandler> handlers = new HashMap<>();
        handlers.put(CrudOperation.POST, () -> save(exchange));
        HttpResponseCustom.handle(exchange, handlers);
    }

    private void save(HttpExchange exchange) throws IOException {
        try (InputStream content = exchange.getRequestBody()) {
            fileService.save(
                    extractDirectory(exchange),
                    exchange.getRequestHeaders().getFirst(FILE_NAME_HEADER),
                    content
            );
        } catch (FileValidationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (FileStorageException | IOException e) {
            throw new InternalServerErrorException("Не удалось сохранить файл", e.getMessage());
        }

        sendResponse(exchange, 200, "Файл сохранён");
        Logger.info("Файл сохранён");
    }

    private Path extractDirectory(HttpExchange exchange) {
        String requestPath = exchange.getRequestURI().getPath();
        String prefix = ApiPaths.FILE + "/";

        if (!requestPath.startsWith(prefix) || requestPath.length() == prefix.length()) {
            throw new FileValidationException("Не указан каталог для сохранения файла");
        }

        try {
            return Path.of(requestPath.substring(prefix.length()));
        } catch (InvalidPathException e) {
            throw new FileValidationException("Некорректный путь", e);
        }
    }
}
