package file.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileStorage {
    void save(String fileName, Path path, InputStream data, long size) throws IOException;
}
