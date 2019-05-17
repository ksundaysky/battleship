package wkbp.battleships.model;

/**
 * Updates board based on given move {@link Move}.
 *
 * @author Wiktor Rup
 */
public class BoardUpdater {

    private Move move;
    private Board currentBoard;
    private GameReferee gameReferee;

    BoardUpdater(Board currentBoard) {
        this.currentBoard = currentBoard;
        this.gameReferee = new GameReferee(currentBoard);
    }

    ShotOutcome updateBoard(Move move) {
        this.move = move;
        int fieldToShootId = move.getFieldToShoot().getId();
        Field fieldToShoot = currentBoard.getFieldList().get(fieldToShootId);
        Field updatedField = changeStateOfField(fieldToShoot);
        currentBoard.getFieldList().set(fieldToShootId, updatedField);
        return new ShotOutcome(notifyReferee(move), updatedField.getStateOfField());
    }
//todo jak zwrócić czy wygrał
    private boolean notifyReferee(Move move) {
        gameReferee.setLastMove(move);
        return gameReferee.checkIfHitTheShip();
    }


    private Field changeStateOfField(Field field) {
        if (field.getStateOfField().isHit)
            return field;
        else {
            field.getStateOfField().setHit(true);
            return field;
        }
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public GameReferee getGameReferee() {
        return gameReferee;
    }
}
