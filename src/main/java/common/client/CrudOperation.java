package common.client;

public enum CrudOperation {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String httpMethod;

    CrudOperation(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static CrudOperation fromHttpMethod(String method) {
        for (CrudOperation operation : values()) {
            if (operation.httpMethod.equals(method)) {
                return operation;
            }
        }

        throw new IllegalArgumentException(
                "Unsupported HTTP method: " + method
        );
    }
}
