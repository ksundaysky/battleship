package wkbp.battleships.model;

/**
 * @author krzysztof.niedzielski
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