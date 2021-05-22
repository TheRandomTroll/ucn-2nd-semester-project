package exceptions;

/**
 * An exception class used to notify that there is insufficient data
 * for a given request.
 */
public class InsufficientDataException extends Exception {
    public InsufficientDataException(String message) {
        super(message);
    }

    public InsufficientDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
