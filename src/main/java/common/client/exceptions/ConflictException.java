package common.client.exceptions;

public class ConflictException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Конфликт данных";
    private static final String DEFAULT_DESCRIPTION =
            "Запрос конфликтует с текущим состоянием ресурса.";

    public ConflictException() {
        this(null, null);
    }

    public ConflictException(String message) {
        this(message, null);
    }

    public ConflictException(String message, String description) {
        super(
                409,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
