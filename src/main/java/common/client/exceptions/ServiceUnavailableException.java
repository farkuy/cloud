package common.client.exceptions;

public class ServiceUnavailableException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Сервис временно недоступен";
    private static final String DEFAULT_DESCRIPTION =
            "Сервис временно не может обработать запрос. Повторите попытку позже.";

    public ServiceUnavailableException() {
        this(null, null);
    }

    public ServiceUnavailableException(String message) {
        this(message, null);
    }

    public ServiceUnavailableException(String message, String description) {
        super(
                503,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
