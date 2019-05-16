package wkbp.battleships.exception;

/**
 * @author Wiktor Rup
 */
public class NoAvailableGamesException extends RuntimeException {
    public NoAvailableGamesException(String message) {
        super(message);
    }
}
