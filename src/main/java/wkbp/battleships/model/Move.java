package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wiktor Rup
 */
@Getter
@AllArgsConstructor
public class Move {

    private long gameId;
    private User player;
    private Field fieldToShoot;
}
