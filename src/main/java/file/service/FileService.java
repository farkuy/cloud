package file.service;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {

    void save(Path directory, String fileName, InputStream content);
}
