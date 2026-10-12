package common.client.exceptions;

public class UnprocessableEntityException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Не удалось обработать данные";
    private static final String DEFAULT_DESCRIPTION =
            "Запрос составлен корректно, но содержит недопустимые данные.";

    public UnprocessableEntityException() {
        this(null, null);
    }

    public UnprocessableEntityException(String message) {
        this(message, null);
    }

    public UnprocessableEntityException(String message, String description) {
        super(
                422,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
