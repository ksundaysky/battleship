package wkbp.battleships.model;

/**
 * @author Patryk Kucharski
 */
public class ShotOutcome {

    boolean playerTurn;
    Field field;
    boolean playerWon;

    ShotOutcome(){}

    public ShotOutcome(boolean playerTurn, Field field, boolean playerWon) {
        this.playerTurn = playerTurn;
        this.field = field;
        this.playerWon = playerWon;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }
}
