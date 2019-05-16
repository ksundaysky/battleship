package wkbp.battleships.model;

/**
 * @author Wiktor Rup
 */
public class MoveValidator {

    private Move move;
    private GameConfig gameConfig;
    private Board board;

    boolean validateMove() {
        return move.getFieldToShoot().getId() >= 0 && move.getFieldToShoot().getId() < board.getDimension();
    }
}
