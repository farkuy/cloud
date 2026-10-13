package file.exception;

public final class FileValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileValidationException(String message) {
        super(message);
    }

    public FileValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
