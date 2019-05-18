package wkbp.battleships.controller;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
public class GameIsFullException extends RuntimeException {
    public GameIsFullException(String message) {
        super(message);
    }
}
