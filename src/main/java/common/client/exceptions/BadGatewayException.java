package common.client.exceptions;

public class BadGatewayException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Ошибка шлюза";
    private static final String DEFAULT_DESCRIPTION =
            "Сервер получил некорректный ответ от вышестоящего сервера.";

    public BadGatewayException() {
        this(null, null);
    }

    public BadGatewayException(String message) {
        this(message, null);
    }

    public BadGatewayException(String message, String description) {
        super(
                502,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
