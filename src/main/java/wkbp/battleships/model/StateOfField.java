package wkbp.battleships.model;

/**
 * Represents current state of field on board
 * ILLEGAL_TO_PLACE is used only for purpose
 * of placing ships on board
 * <p>
 * {@link wkbp.battleships.businesslogic.ShipsRandomiser}
 *
 * @author Wiktor Rup
 */
public enum StateOfField {

    EMPTY, OCCUPIED, ILLEGAL_TO_PLACE;

    boolean isHit;

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
