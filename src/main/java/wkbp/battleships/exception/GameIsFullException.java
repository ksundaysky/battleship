package wkbp.battleships.exception;

/**
 * @author Wiktor Rup
 */
public class GameIsFullException extends RuntimeException {
    public GameIsFullException(String message) {
        super(message);
    }
}
