package wkbp.battleships.controller;

/**
 * @author Wiktor Rup
 */
class NoPermissionException extends RuntimeException {
    NoPermissionException(String message) {
        super(message);
    }
}
