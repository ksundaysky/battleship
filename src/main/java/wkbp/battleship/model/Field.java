package wkbp.battleship.model;

/**
 * Class which represents the smallest possible area on the Board {@link wkbp.battleship.model.Board} and a part of Ship {@link wkbp.battleship.model.Ship}
 * @author Wiktor Rup
 */
public class Field {
    private int id;
    private StateOfField stateOfField;

    public Field(int id) {
        this.id = id;
        stateOfField = StateOfField.EMPTY;
        stateOfField.isHit = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStateOfField(StateOfField stateOfField)
    {
        this.stateOfField = stateOfField;
    }

    public StateOfField getStateOfField() {
        return stateOfField;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", isTrafiony=" + stateOfField +
                '}';
    }
}
