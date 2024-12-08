package events;

public class StringPayload implements Payload<String> {

    private final String payload;

    public StringPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public int getSize() {
        return payload.length();
    }

    @Override
    public String getPayload() {
        return payload;
    }
}
