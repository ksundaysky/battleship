package wkbp.battleships.message.response;

/**
 * Docker for a messages which are to
 * be sent between server and client
 *
 * @author Wiktor Wrup
 * @author Patryk Kucharski
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
