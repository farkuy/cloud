package common.client;

public class Response {
    private String message;
    private String error;

    public Response(int statusCode, String message, String error) {
        this.message = message;
        this.error = error;
    }
}
