package file.service;

import file.exception.FileValidationException;
import file.storage.FileStorage;

import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Objects;

public final class FileServiceImpl implements FileService {

    private final FileStorage storage;

    public FileServiceImpl(FileStorage storage) {
        this.storage = Objects.requireNonNull(storage, "storage");
    }

    @Override
    public void save(Path directory, String fileName, InputStream content) {
        if (fileName == null || fileName.isBlank()) {
            throw new FileValidationException("Имя файла не может быть пустым");
        }
        if (fileName.contains("/")
                || fileName.contains("\\")
                || fileName.equals(".")
                || fileName.equals("..")) {
            throw new FileValidationException("Имя файла не должно содержать путь");
        }

        try {
            storage.save(directory.resolve(fileName), content);
        } catch (InvalidPathException e) {
            throw new FileValidationException("Некорректное имя файла", e);
        }
    }
}
