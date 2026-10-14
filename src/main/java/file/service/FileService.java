package file.service;

import file.dto.StorageResource;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {

    void save(Path directory, String fileName, InputStream content);

    StorageResource read(Path directory, String fileName);
}
