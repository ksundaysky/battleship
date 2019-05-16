package wkbp.battleships.exception;

/**
 * @author Wiktor Rup
 */
public class CantPlaceShipsException extends RuntimeException {
    public CantPlaceShipsException(String s) {
        super(s);
    }
}
