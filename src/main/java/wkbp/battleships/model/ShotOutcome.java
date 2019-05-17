package wkbp.battleships.model;

/**
 * @author Patryk Kucharski
 */
public class ShotOutcome {

    boolean playerTurn;
    StateOfField stateOfField;
    public ShotOutcome(boolean playerTurn, StateOfField stateOfField) {
        this.playerTurn = playerTurn;
        this.stateOfField = stateOfField;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public StateOfField getStateOfField() {
        return stateOfField;
    }
}
