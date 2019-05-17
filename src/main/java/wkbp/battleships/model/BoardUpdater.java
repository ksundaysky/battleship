package wkbp.battleships.model;

/**
 * Updates board based on given lastMove {@link Move}.
 *
 * @author Wiktor Rup
 */
public class BoardUpdater {

    private Move lastMove;
    private Board currentBoard;
    private GameReferee gameReferee;

    BoardUpdater(Board currentBoard) {
        this.currentBoard = currentBoard;
        this.gameReferee = new GameReferee(currentBoard);
    }

    ShotOutcome updateBoard(Move move) {
        this.lastMove = move;
        int fieldToShootId = move.getFieldToShoot().getId();
        Field fieldToShoot = currentBoard.getFieldList().get(fieldToShootId);
        Field updatedField = changeStateOfField(fieldToShoot);
        currentBoard.getFieldList().set(fieldToShootId, updatedField);
        notifyReferee(move);
        return new ShotOutcome(gameReferee.checkIfHitTheShip(), updatedField, gameReferee.checkIfWon());
    }

    private void notifyReferee(Move move) {
        gameReferee.setLastMove(move);
    }

    private Field changeStateOfField(Field field) {
        if (field.getStateOfField().isHit)
            return field;
        else {
            field.getStateOfField().setHit(true);
            return field;
        }
    }
}
