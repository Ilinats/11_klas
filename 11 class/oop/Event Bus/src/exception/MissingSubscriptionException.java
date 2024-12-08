package exception;

public class MissingSubscriptionException extends RuntimeException {
    public MissingSubscriptionException(String message) {
        super(message);
    }
}
