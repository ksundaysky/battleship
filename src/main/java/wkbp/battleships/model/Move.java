package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@AllArgsConstructor
public class Move {

    private long gameId;
    private User player;
    private Field fieldToShoot;
}
