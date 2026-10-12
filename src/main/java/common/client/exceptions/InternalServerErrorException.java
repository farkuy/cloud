package common.client.exceptions;

public class InternalServerErrorException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Внутренняя ошибка сервера";
    private static final String DEFAULT_DESCRIPTION =
            "Во время обработки запроса произошла непредвиденная ошибка.";

    public InternalServerErrorException() {
        this(null, null);
    }

    public InternalServerErrorException(String message) {
        this(message, null);
    }

    public InternalServerErrorException(String message, String description) {
        super(
                500,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
