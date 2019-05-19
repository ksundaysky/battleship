package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsible for judging players moves and deciding if game is won,
 * or if shot hit its target and whose turn comes next.
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Component
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class GameReferee {

    private Board board;
    private Move lastMove;
    private Auditor auditor;

    public GameReferee(Board board) {
        this.board = board;
        auditor = new Auditor();
    }

    public boolean checkIfWon() {
        List<Field> hitFields = board.getFieldList().stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .filter(field -> !field.getStateOfField().isHit)
                .collect(Collectors.toList());
        return hitFields.isEmpty();
    }

    public boolean checkIfHitTheShip() {
        StateOfField stateOfField = board.getFieldList().get(lastMove.getFieldToShoot().getId()).getStateOfField();
        return stateOfField.equals(StateOfField.OCCUPIED);
    }

    public void notifyAuditor() {
        auditor.update(lastMove, checkIfWon(), checkIfHitTheShip());
    }

}
