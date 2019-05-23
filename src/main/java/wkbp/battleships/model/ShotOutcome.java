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
@ToString
public class ShotOutcome {

    private boolean playerTurn;
    private Field field;
    private boolean playerWon;
}
