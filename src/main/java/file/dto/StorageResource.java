package file.dto;

import java.io.InputStream;

public record StorageResource(InputStream inputStream, long size) {}
