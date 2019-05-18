package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Wiktor Rup
 */
@AllArgsConstructor
@NoArgsConstructor
public class MoveValidator {

    private Move move;
    private GameConfig gameConfig;
    private Board board;

    boolean validateMove() {
        return move.getFieldToShoot().getId() >= 0 && move.getFieldToShoot().getId() < board.getDimension();
    }
}
