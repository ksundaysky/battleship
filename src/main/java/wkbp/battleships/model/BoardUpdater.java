package wkbp.battleships.model;

/**
 * Updates board based on given move {@link Move}.
 *
 * @author Wiktor Rup
 */
public class BoardUpdater {

    private Move move;
    private Board currentBoard;

    public BoardUpdater(Move move, Board currentBoard) {
        this.move = move;
        this.currentBoard = currentBoard;
    }

    public Field updateBoard() {

        Field fieldToShoot = currentBoard.getFieldList().get(move.getFieldToShoot().getId());
        Field updatedField = changeStateOfField(fieldToShoot);
        currentBoard.getFieldList().set(move.getFieldToShoot().getId(), updatedField);
        return updatedField;
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
