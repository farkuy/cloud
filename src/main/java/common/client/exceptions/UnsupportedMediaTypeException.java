package common.client.exceptions;

public class UnsupportedMediaTypeException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Неподдерживаемый тип данных";
    private static final String DEFAULT_DESCRIPTION =
            "Сервер не поддерживает формат тела запроса.";

    public UnsupportedMediaTypeException() {
        this(null, null);
    }

    public UnsupportedMediaTypeException(String message) {
        this(message, null);
    }

    public UnsupportedMediaTypeException(String message, String description) {
        super(
                415,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
