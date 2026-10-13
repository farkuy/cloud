package file.storage;

import common.client.exceptions.BadRequestException;
import common.client.exceptions.PayloadTooLargeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LocalFileStorage implements FileStorage {

    public void save(String fileName, Path path, InputStream data, long size) throws IOException {
        if (fileName == null || fileName.isBlank()) {
            throw new BadRequestException("Не указано имя файла");
        }

        Path baseDirectory = Path.of("C:", "Users", "Onton", "Desktop");
        String pathStr = path.toString();
        if (pathStr.startsWith("/") || pathStr.startsWith("\\")) {
            pathStr = pathStr.substring(1);
        }
        Path fullPath = baseDirectory.resolve(pathStr);

        Path target = fullPath.resolve(fileName).normalize();
        long usableSpace = Files.getFileStore(target.getParent()).getUsableSpace();

        if (!target.startsWith(fullPath)) {
            throw new BadRequestException("Некорректное имя файла");
        }
        if (usableSpace < size) {
            throw new PayloadTooLargeException();
        }

        try {
            Files.createDirectories(fullPath);
            Files.copy(data, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BadRequestException("Ошибка при сохранении файла", e.getMessage());
        }
    }

}
