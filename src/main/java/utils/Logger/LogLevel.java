package utils.Logger;

public enum LogLevel {
    INFO("INFO", Colors.BLUE),
    WARN("WARN", Colors.YELLOW),
    ERROR("ERROR", Colors.RED);

    private final String label;
    private final Colors color;

    LogLevel(String label, Colors color) {
        this.label = label;
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "[" + label + "]" + Colors.RESET;
    }
}