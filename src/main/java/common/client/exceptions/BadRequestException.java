package common.client.exceptions;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, String description) {
        super(
                400,
                message,
                description
        );
    }
}
