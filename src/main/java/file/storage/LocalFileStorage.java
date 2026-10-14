package file.storage;

import file.dto.StorageResource;
import file.exception.FileStorageException;
import file.exception.FileValidationException;

import java.io.*;
import java.nio.file.FileStore;
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
    public StorageResource read(Path relativePath) {
        Path target = getTarget(relativePath);

        File file = new File(target.toUri());
        if (!file.exists()) {
            throw new FileStorageException("Не удалось получить файл", null);
        }

        try {
            InputStream readableFile = Files.newInputStream(target);
            long fileSize = Files.size(target);

            return new StorageResource(readableFile, fileSize);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Не удалось получить файл", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Path relativePath, InputStream content) {
        Objects.requireNonNull(content, "content");
        Path target = getTarget(relativePath);

        try {
            Files.createDirectories(target.getParent());
            Files.copy(content, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Не удалось сохранить файл", e);
        }
    }

    private Path getTarget(Path relativePath) {
        if (relativePath == null || relativePath.isAbsolute()) {
            throw new FileValidationException("Путь к файлу должен быть относительным");
        }

        Path target = rootDirectory.resolve(relativePath).normalize();
        if (!target.startsWith(rootDirectory)) {
            throw new FileValidationException("Путь выходит за пределы хранилища");
        }

        return target;
    }
}
