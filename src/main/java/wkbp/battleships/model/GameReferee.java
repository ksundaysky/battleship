package wkbp.battleships.model;

/**
 * Responsible for judging players moves and deciding if game is won,
 * or if shot hit its target and whose turn comes next.
 *
 * @author Wiktor Rup
 */
public class GameReferee {

    private Board board;
    private Move move;
    private Auditor auditor;

    public GameReferee(Board board, Move move, Auditor auditor) {
        this.board = board;
        this.move = move;
        this.auditor = auditor;
    }

    public boolean checkIfWon() {
        for (Field field : board.getFieldList()) {
            if (field.getStateOfField().equals(StateOfField.OCCUPIED) && field.getStateOfField().isHit)
                return false;
        }
        return true;
    }

    public boolean checkIfHitTheShip() {
        return board.getFieldList().get(move.getFieldToShoot().getId()).getStateOfField().equals(StateOfField.OCCUPIED);
    }

    public void notifyAuditor() {
        auditor.update(move, checkIfWon(), checkIfHitTheShip());
    }

}
