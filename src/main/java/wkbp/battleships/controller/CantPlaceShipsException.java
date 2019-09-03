package wkbp.battleships.controller;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
public class CantPlaceShipsException extends RuntimeException {
    public CantPlaceShipsException(String message) {
        super(message);
    }
}
