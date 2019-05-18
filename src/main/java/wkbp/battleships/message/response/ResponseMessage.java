package wkbp.battleships.message.response;

import lombok.Data;

/**
 * Docker for a messages which are to
 * be sent between server and client
 *
 * @author Wiktor Wrup
 * @author Patryk Kucharski
 */
@Data
public class ResponseMessage {

    private String message;
}
