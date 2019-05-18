package wkbp.battleships.message.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Docker for a messages which are to
 * be sent between server and client
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private String message;
}
