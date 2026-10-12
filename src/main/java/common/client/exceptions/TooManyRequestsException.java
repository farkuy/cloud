package common.client.exceptions;

public class TooManyRequestsException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Слишком много запросов";
    private static final String DEFAULT_DESCRIPTION =
            "Превышен допустимый лимит запросов. Повторите попытку позже.";

    public TooManyRequestsException() {
        this(null, null);
    }

    public TooManyRequestsException(String message) {
        this(message, null);
    }

    public TooManyRequestsException(String message, String description) {
        super(
                429,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
