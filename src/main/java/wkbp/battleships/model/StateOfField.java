package wkbp.battleships.model;

/**
 * @author Wiktor Rup
 */
public enum StateOfField {

    EMPTY, OCCUPIED, ILLEGAL_TO_PLACE;

    boolean isHit;

    public void setHit(boolean hit) {
        isHit = hit;
    }}
