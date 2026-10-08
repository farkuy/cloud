package utils.Logger;

public abstract class Logger {

    public static void info(String... messages) {
        String responseMsg = buildMsg(messages);

        System.out.println(LogLevel.INFO + responseMsg);
    }

    public static void warn(String... messages) {
        String responseMsg = buildMsg(messages);

        System.out.println(LogLevel.WARN + responseMsg);
    }

    public static void error(String... messages) {
        String responseMsg = buildMsg(messages);

        System.err.println(LogLevel.ERROR + responseMsg);
    }

    private static String buildMsg(String... messages) {
        StringBuilder responseBuilder = new StringBuilder();

        for (int i = 0; i < messages.length; ++i) {
            responseBuilder.append(messages[i]).append(" ");
        }

        return responseBuilder.toString();
    }

}
