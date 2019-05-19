package wkbp.battleships.model;

import lombok.*;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShotOutcome {

    boolean playerTurn;
    Field field;
    boolean playerWon;
}
