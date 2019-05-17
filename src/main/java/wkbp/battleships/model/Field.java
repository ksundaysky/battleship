package wkbp.battleships.model;

/**
 * Represents single cell of the board which can be shot at
 * <p>
 * as board object {@link Board} has a List of fields, each field has it's own unique id
 * also field has a state {@link StateOfField}
 *
 * @author Krzysztof Niedzielski
 * @author Patryk Kucharski
 */
public class Field {

    private int id;
    private StateOfField stateOfField;

    public Field(int id, StateOfField stateOfField) {
        this.id = id;
        this.stateOfField = stateOfField;
    }

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

    public void setStateOfField(StateOfField stateOfField) {
        this.stateOfField = stateOfField;
    }

    public StateOfField getStateOfField() {
        return stateOfField;
    }

    public void isHit(boolean isHit) {
        stateOfField.setHit(isHit);
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", stateOfField=" + stateOfField +
                "is hit=" + stateOfField.isHit +
                '}';
    }
}