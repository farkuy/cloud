package common.client.exceptions;

public class MethodNotAllowedException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Метод не поддерживается";
    private static final String DEFAULT_DESCRIPTION =
            "HTTP-метод не поддерживается для запрашиваемого ресурса.";

    public MethodNotAllowedException() {
        this(null, null);
    }

    public MethodNotAllowedException(String message) {
        this(message, null);
    }

    public MethodNotAllowedException(String message, String description) {
        super(
                405,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
