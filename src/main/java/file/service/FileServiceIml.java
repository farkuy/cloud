package file.service;

import com.sun.net.httpserver.HttpExchange;
import common.client.exceptions.BadRequestException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileServiceIml implements FileService {

    private static String errorMessage = "Ошибка при записи передавемого файла";

    public static void writeFile(HttpExchange exchange) {
        byte[] binaryData;
        try (InputStream readFile = exchange.getRequestBody()) {
            binaryData = readFile.readAllBytes();
        } catch (IOException e) {
            throw new BadRequestException(errorMessage, e.getMessage());
        }

        if (binaryData.length == 0) throw new BadRequestException();


        var filePath = "C:\\Users\\Onton\\Desktop\\Gerwant.jpg";
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(binaryData);
        } catch (IOException e) {
            throw new BadRequestException(errorMessage, e.getMessage());
        }
    };
}
