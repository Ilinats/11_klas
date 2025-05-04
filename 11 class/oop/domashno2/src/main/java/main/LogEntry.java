package main;

public class LogEntry {
    private final String timestamp;
    private final String level;
    private final String sourceClass;
    private final String message;

    public LogEntry(String timestamp, String level, String sourceClass, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.sourceClass = sourceClass;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return timestamp + " " + level + " " + sourceClass + " " + message;
    }
}
