package file.storage;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileStorage {

    void save(Path relativePath, InputStream content);
}
