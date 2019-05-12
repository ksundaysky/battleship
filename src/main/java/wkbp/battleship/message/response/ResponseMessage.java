package wkbp.battleship.message.response;

/**
 * Docker for a messages sending between server and client applications.
 * @author Wiktor Rup
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
