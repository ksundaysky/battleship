package wkbp.battleships.model;

/**
 * @author Wiktor Rup
 */
public enum StateOfField {

    EMPTY, OCCUPIED;

    boolean isHit;

    public void setHit(boolean hit) {
        isHit = hit;
    }}
