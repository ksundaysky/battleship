package wkbp.battleships.model;

import lombok.*;

import java.util.List;

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
    private List<Field> neighbourFieldsOfSunkenShip;
    private boolean playerWon;
    private String message;
}
