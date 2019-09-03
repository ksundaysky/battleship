package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Move {

    private long gameId;
    private User player;
    private Field fieldToShoot;
}
