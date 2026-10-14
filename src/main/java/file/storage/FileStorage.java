package file.storage;

import file.dto.StorageResource;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileStorage {

    void save(Path relativePath, InputStream content);

    StorageResource read(Path relativePath);
}
