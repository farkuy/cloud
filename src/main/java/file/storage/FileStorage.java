package file.storage;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorage {
    static String save(
            String fileName,
            InputStream data,
            long size
    ) throws IOException {
        return null;
    }
}
