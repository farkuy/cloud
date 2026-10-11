package common.client.exceptions;

public class BaseException extends RuntimeException {
    final int statusCode;
    final String message;
    final String description;

    public BaseException(int statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
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
