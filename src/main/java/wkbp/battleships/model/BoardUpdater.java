package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Updates board based on given lastMove {@link Move}.
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
class BoardUpdater {

    private Move lastMove;
    private Board currentBoard;
    private GameReferee gameReferee;


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
        if (!field.isHit()) {
            field.setIsHit(true);
            gameReferee.setLastShootHit(true);
        }else {
            gameReferee.setLastShootHit(false);
        }
    }

    void setRefereeBoard(Board board) {
        gameReferee.setBoard(board);
    }


}
