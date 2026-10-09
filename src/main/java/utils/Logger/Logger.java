package utils.Logger;

import static utils.Logger.Colors.*;

public final class Logger {

    private final static StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static void info(String... messages) {
        String gluedMessage = buildMsg(messages);
        String logMessage = createAllLogMsg(LogLevel.INFO, gluedMessage);

        System.out.println(logMessage);
    }

    public static void error(String... messages) {
        String gluedMessage = buildMsg(messages);
        String logMessage = createAllLogMsg(LogLevel.ERROR, gluedMessage);

        System.err.println(logMessage);
    }

    private static String createAllLogMsg(LogLevel logLevel, String responseMsg) {
        Class<?> classAboveCaller = stackWalker
                .walk(stream -> stream
                        .map(StackWalker.StackFrame::getDeclaringClass)
                        .skip(2)
                        .findFirst()
                        .orElse(null));

        Thread current = Thread.currentThread();

        return new StringBuilder()
                        .append(logLevel)
                        .append(" ")
                        .append(CYAN)
                        .append(String.format("[%s]", current.getName()))
                        .append(" ")
                        .append(GRAY)
                        .append(classAboveCaller.getName())
                        .append(RESET)
                        .append(" ")
                        .append(responseMsg)
                        .toString();
    }

    private static String buildMsg(String... messages) {
        StringBuilder responseBuilder = new StringBuilder();

        for (int i = 0; i < messages.length; ++i) {
            responseBuilder.append(messages[i]).append(" ");
        }

        return responseBuilder.toString();
    }

}
