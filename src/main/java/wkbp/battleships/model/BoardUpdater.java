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
        this.gameReferee = new GameReferee(this.currentBoard);
    }

    ShotOutcome updateBoard(Move move) {
        this.lastMove = move;
        int fieldToShootId = move.getFieldToShoot().getId();
        changeStateOfField(currentBoard.getFieldList().get(fieldToShootId));
        Field updatedField = currentBoard.getFieldList().get(fieldToShootId);
        notifyReferee(move);
        return new ShotOutcome(gameReferee.checkIfHitTheShip(), updatedField, gameReferee.checkIfWon());
    }

    private void notifyReferee(Move move) {
        gameReferee.setLastMove(move);
    }

    private void changeStateOfField(Field field) {
        if (!field.isHit())
            field.setIsHit(true);
    }

    void setRefereeBoard(Board board) {
        gameReferee.setBoard(board);
    }
}
