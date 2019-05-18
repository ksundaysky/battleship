package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class ShotOutcome {

    boolean playerTurn;
    Field field;
    boolean playerWon;
}
