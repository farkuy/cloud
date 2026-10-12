package common.client.exceptions;

public class ForbiddenException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Доступ запрещён";
    private static final String DEFAULT_DESCRIPTION =
            "У пользователя недостаточно прав для доступа к ресурсу.";

    public ForbiddenException() {
        this(null, null);
    }

    public ForbiddenException(String message) {
        this(message, null);
    }

    public ForbiddenException(String message, String description) {
        super(
                403,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
