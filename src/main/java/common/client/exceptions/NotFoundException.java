package common.client.exceptions;

public class NotFoundException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Ресурс не найден";
    private static final String DEFAULT_DESCRIPTION =
            "Запрашиваемый ресурс не найден.";

    public NotFoundException() {
        this(null, null);
    }

    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException(String message, String description) {
        super(
                404,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
