package file;

import java.util.Optional;

public enum FileExtension {
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg"),
    GIF("gif"),
    WEBP("webp"),
    BMP("bmp"),
    TIFF("tiff"),
    SVG("svg"),
    ICO("ico"),
    AVIF("avif"),
    HEIC("heic"),

    MP4("mp4"),
    AVI("avi"),
    MKV("mkv"),
    MOV("mov"),
    WEBM("webm"),
    MPEG("mpeg"),
    MPG("mpg"),
    M4V("m4v"),
    WMV("wmv"),
    FLV("flv"),
    THREE_GP("3gp");

    private final String value;

    FileExtension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<FileExtension> fromFileName(String fileName) {
        if (fileName == null) {
            return Optional.empty();
        }

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0 || dotIndex == fileName.length() - 1) {
            return Optional.empty();
        }

        String extension = fileName.substring(dotIndex + 1);
        for (FileExtension candidate : values()) {
            if (candidate.value.equalsIgnoreCase(extension)) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }
}
