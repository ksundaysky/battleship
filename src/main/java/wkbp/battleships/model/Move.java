package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Move {

    private long gameId;
    private User player;
    private Field fieldToShoot;

    @Override
    public String toString() {
        return "Move{" +
                "gameId=" + gameId +
                ", player=" + player +
                ", fieldToShoot=" + fieldToShoot +
                '}';
    }
}
