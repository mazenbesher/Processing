package exceptions;

public class NotSetException extends RuntimeException {
    public NotSetException(String message) {
        super(message);
    }
}
