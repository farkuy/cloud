package common.client.exceptions;

public class PayloadTooLargeException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Слишком большой запрос";
    private static final String DEFAULT_DESCRIPTION =
            "Размер тела запроса превышает допустимый предел.";

    public PayloadTooLargeException() {
        this(null, null);
    }

    public PayloadTooLargeException(String message) {
        this(message, null);
    }

    public PayloadTooLargeException(String message, String description) {
        super(
                413,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
