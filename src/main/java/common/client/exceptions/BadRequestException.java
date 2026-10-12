package common.client.exceptions;

public class BadRequestException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Некорректный запрос";
    private static final String DEFAULT_DESCRIPTION =
            "Сервер не может обработать запрос из-за ошибки в его данных.";

    public BadRequestException() {
        this(null, null);
    }

    public BadRequestException(String message) {
        this(message, null);
    }

    public BadRequestException(String message, String description) {
        super(
                400,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
