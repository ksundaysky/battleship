package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

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
    private Auditor auditor;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    BoardUpdater(Board currentBoard) {
        this.currentBoard = currentBoard;
        this.gameReferee = new GameReferee(this.currentBoard);
        this.auditor = new Auditor();
    }

    ShotOutcome updateBoard(Move move) {
        lastMove = move;
        int fieldToShootId = move.getFieldToShoot().getId();
        changeStateOfField(currentBoard.getFieldList().get(fieldToShootId));
        Field updatedField = currentBoard.getFieldList().get(fieldToShootId);
        notifyReferee(move);
        boolean hitTheShip = gameReferee.checkIfHitTheShip();
        boolean ifWon = gameReferee.checkIfWon();
        notifyAuditor(move, ifWon, hitTheShip);
        ShotOutcome shotOutcome = new ShotOutcome(hitTheShip, updatedField, ifWon, auditor.auditLastMove());
        // TODO: 23.05.19 currentBoard.neighboursOfShip(move.getFieldToShoot()) jako 4 parametr do shoutOutcome 
        logger.info("class BoardUpdater, method updateBoard(); returning shotOutcome: " + shotOutcome.toString());
        return shotOutcome;
    }

    private void notifyReferee(Move move) {
        logger.info("notifying referee with lastMove " + move.toString());
        gameReferee.setLastMove(move);
    }

    private void notifyAuditor(Move lastMove, boolean won, boolean hitTheShip) {
        auditor.update(lastMove, won, hitTheShip);
    }

    private void changeStateOfField(Field field) {
        if (!field.isHit()) {
            field.setIsHit(true);
            gameReferee.setLastShootHit(true);
            logger.info("changed field to isHit " + field.toString());
        } else {
            gameReferee.setLastShootHit(false);
        }
    }

    void setRefereeBoard(Board board) {
        gameReferee.setBoard(board);
    }
}
