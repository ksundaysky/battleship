package wkbp.battleships.controller;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
public class NoAvailableGamesException extends RuntimeException {
    public NoAvailableGamesException(String message) {
        super(message);
    }
}
