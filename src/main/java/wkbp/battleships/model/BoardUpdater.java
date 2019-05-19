package wkbp.battleships.model;

import lombok.EqualsAndHashCode;

/**
 * Updates board based on given lastMove {@link Move}.
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@EqualsAndHashCode
class BoardUpdater {

    private Move lastMove;
    private Board currentBoard;
    private GameReferee gameReferee;
    private Board refereeBoard; //todo czemu to jest nieużywane?

    BoardUpdater(Board currentBoard) {
        this.currentBoard = currentBoard;
        this.gameReferee = new GameReferee(currentBoard);
    }

    ShotOutcome updateBoard(Move move, Board board) {
        this.lastMove = move;
        int fieldToShootId = move.getFieldToShoot().getId();
        Field fieldToShoot = board.getFieldList().get(fieldToShootId);
        Field updatedField = changeStateOfField(fieldToShoot);
        board.getFieldList().get(fieldToShootId).setStateOfField(StateOfField.OCCUPIED);
        notifyReferee(move);// TODO: 19.05.2019 naprawione
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

    void setRefereeBoard(Board board) {
        gameReferee.setBoard(board);
    }
}
