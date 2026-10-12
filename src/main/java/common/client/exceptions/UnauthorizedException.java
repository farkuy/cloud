package common.client.exceptions;

public class UnauthorizedException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Требуется авторизация";
    private static final String DEFAULT_DESCRIPTION =
            "Для доступа к ресурсу необходимо пройти авторизацию.";

    public UnauthorizedException() {
        this(null, null);
    }

    public UnauthorizedException(String message) {
        this(message, null);
    }

    public UnauthorizedException(String message, String description) {
        super(
                401,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
