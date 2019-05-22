package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

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
    private boolean lastShootHit;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    GameReferee(Board board) {
        this.board = board;
    }

    boolean checkIfWon() {
        List<Field> hitFields = board.getFieldList().stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .filter(field -> !field.isHit())
                .collect(Collectors.toList());
        boolean won = hitFields.isEmpty();
        logger.info(hitFields.toString());
        logger.info("player won = " + won);
        return won;
    }

    boolean checkIfHitTheShip() {
        Field fieldToShoot = lastMove.getFieldToShoot();
        StateOfField stateOfField = board.getField(fieldToShoot.getId()).getStateOfField();
        boolean isOccupied = stateOfField.equals(StateOfField.OCCUPIED);
        logger.info("shot hit the ship: " + isOccupied);
        return isOccupied; // TODO: 20.05.19 błędny warunek do poprawy && board.getFieldList().get(lastMove.getFieldToShoot().getId()).isHit() && lastShootHit;
    }
}
