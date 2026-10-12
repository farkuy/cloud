package file.storage;

import common.client.exceptions.BadRequestException;
import common.client.exceptions.PayloadTooLargeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class LocalFileStorage implements FileStorage{

    public static void save(String fileName, InputStream data, long size) throws IOException {
        if (fileName == null || fileName.isBlank()) {
            throw new BadRequestException("Не указано имя файла");
        }

        Path baseDirectory = Path.of("C:", "Users", "Onton", "Desktop", "uploads");
        Path target = baseDirectory.resolve(fileName).normalize();
        long usableSpace = Files.getFileStore(target.getParent()).getUsableSpace();

        if (!target.startsWith(baseDirectory)) {
            throw new BadRequestException("Некорректное имя файла");
        }
        if (usableSpace < size) {
            throw new PayloadTooLargeException();
        }

        try {
            Files.createDirectories(baseDirectory);
            Files.copy(data, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BadRequestException("Ошибка при сохранении файла", e.getMessage());
        }
    }

}
