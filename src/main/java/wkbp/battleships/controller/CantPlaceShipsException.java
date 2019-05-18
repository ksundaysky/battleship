package wkbp.battleships.controller;

/**
 * @author Wiktor Rup
 */
public class CantPlaceShipsException extends RuntimeException {
    public CantPlaceShipsException(String message) {
        super(message);
    }
}
