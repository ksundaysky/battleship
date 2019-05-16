package wkbp.battleships.model;

/**
 * Updates board based on given move {@link Move}.
 *
 * @author Wiktor Rup
 */
public class BoardUpdater {

    private Move move;
    private Board currentBoard;


    public void shootGivenField() {

        Field fieldToShoot = currentBoard.getFieldList().get(move.getFieldToShoot().getId());
        currentBoard.getFieldList().set(move.getFieldToShoot().getId(), changeStateOfField(fieldToShoot));
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
