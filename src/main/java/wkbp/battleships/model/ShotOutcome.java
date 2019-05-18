package wkbp.battleships.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Patryk Kucharski
 */
@Data
public class ShotOutcome {

    boolean playerTurn;
    Field field;
    boolean playerWon;
}
