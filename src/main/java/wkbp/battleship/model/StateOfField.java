package wkbp.battleship.model;

/**
 * This enum class represents possible states of fields {@link wkbp.battleship.model.Field}
 * @author Wiktor Rup
 */
public enum StateOfField {
    EMPTY, OCCUPIED;

    boolean isHit;

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
