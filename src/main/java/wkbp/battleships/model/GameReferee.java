package wkbp.battleships.model;

import org.springframework.stereotype.Component;

/**
 * Responsible for judging players moves and deciding if game is won,
 * or if shot hit its target and whose turn comes next.
 *
 * @author Wiktor Rup
 */
@Component
public class GameReferee {

    private Board board;
    private Move lastMove;
    private Auditor auditor;

    public GameReferee(Board board){
        this.board = board;
        auditor = new Auditor();
    }

    public GameReferee() {
    }

    public boolean checkIfWon() {
        for (Field field : board.getFieldList()) {
            if (field.getStateOfField().equals(StateOfField.OCCUPIED) && field.getStateOfField().isHit)
                return false;
        }
        return true;
    }

    public boolean checkIfHitTheShip() {
        return board.getFieldList().get(lastMove.getFieldToShoot().getId()).getStateOfField().equals(StateOfField.OCCUPIED);
    }

    public void notifyAuditor() {
        auditor.update(lastMove, checkIfWon(), checkIfHitTheShip());
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }
}
