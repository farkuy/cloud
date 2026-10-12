package common.client.exceptions;

public class RequestTimeoutException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Время ожидания запроса истекло";
    private static final String DEFAULT_DESCRIPTION =
            "Сервер не получил полный запрос за отведённое время.";

    public RequestTimeoutException() {
        this(null, null);
    }

    public RequestTimeoutException(String message) {
        this(message, null);
    }

    public RequestTimeoutException(String message, String description) {
        super(
                408,
                message,
                description,
                DEFAULT_MESSAGE,
                DEFAULT_DESCRIPTION
        );
    }
}
