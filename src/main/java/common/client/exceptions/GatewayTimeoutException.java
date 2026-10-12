package common.client.exceptions;

public class GatewayTimeoutException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Время ожидания шлюза истекло";
    private static final String DEFAULT_DESCRIPTION =
            "Вышестоящий сервер не ответил за отведённое время.";

    public GatewayTimeoutException() {
        this(null, null);
    }

    public GatewayTimeoutException(String message) {
        this(message, null);
    }

    public GatewayTimeoutException(String message, String description) {
        super(
                504,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
