package events;

import java.time.Instant;

public class AnotherEvent implements Event<StringPayload> {

    private final Instant timestamp;
    private final int priority;
    private final String source;
    private final StringPayload payload;

    public AnotherEvent(int priority, String source, String payload) {
        this.timestamp = Instant.now();
        this.priority = priority;
        this.source = source;
        this.payload = new StringPayload(payload);
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public StringPayload getPayload() {
        return payload;
    }
}