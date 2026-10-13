package file.storage;

import file.exception.FileStorageException;
import file.exception.FileValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public final class LocalFileStorage implements FileStorage {

    private final Path rootDirectory;

    public LocalFileStorage(Path rootDirectory) {
        this.rootDirectory = Objects.requireNonNull(rootDirectory, "rootDirectory")
                .toAbsolutePath()
                .normalize();
    }

    @Override
    public void save(Path relativePath, InputStream content) {
        Objects.requireNonNull(content, "content");

        if (relativePath == null || relativePath.isAbsolute()) {
            throw new FileValidationException("Путь к файлу должен быть относительным");
        }

        Path target = rootDirectory.resolve(relativePath).normalize();
        if (!target.startsWith(rootDirectory)) {
            throw new FileValidationException("Путь выходит за пределы хранилища");
        }

        try {
            Files.createDirectories(target.getParent());
            Files.copy(content, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Не удалось сохранить файл", e);
        }
    }
}
