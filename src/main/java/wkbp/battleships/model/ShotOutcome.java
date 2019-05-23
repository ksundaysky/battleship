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
public class ShotOutcome {

    private boolean playerTurn;
    private Field field;
    private boolean playerWon;
    private String message;
    //private List<Field> neighbourFieldsOfSunkenShip;

    @Override
    public String toString() {
        return "ShotOutcome{" +
                "playerTurn=" + playerTurn +
                ", field=" + field +
                ", playerWon=" + playerWon +
                ", message=" + message +
                '}';
    }
}
