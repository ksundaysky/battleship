package wkbp.battleships.model;

/**
 * @author Patryk Kucharski
 */
public class ShotOutcome {

    boolean playerTurn;
    Field field;
    boolean playerWon;

    public ShotOutcome(boolean playerTurn, Field field, boolean playerWon) {
        this.playerTurn = playerTurn;
        this.field = field;
        this.playerWon = playerWon;
    }
}
