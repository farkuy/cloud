package common.client.exceptions;

public class BaseException extends RuntimeException {
    final int statusCode;
    final String message;
    final String description;

    public BaseException(int statusCode, String message, String description) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

    protected BaseException(
            int statusCode,
            String message,
            String description,
            String defaultMessage,
            String defaultDescription
    ) {
        this(
                statusCode,
                valueOrDefault(message, defaultMessage),
                valueOrDefault(description, defaultDescription)
        );
    }

    private static String valueOrDefault(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
