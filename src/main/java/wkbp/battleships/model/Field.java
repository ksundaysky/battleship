package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents single cell of the board which can be shot at
 * <p>
 * as board object {@link Board} has a List of fields, each field has it's own unique id
 * also field has a state {@link StateOfField}
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Data
@AllArgsConstructor
public class Field {

    private int id;
    private StateOfField stateOfField;
    private boolean hit;

    public Field(int id) {
        this.id = id;
        stateOfField = StateOfField.EMPTY;
        hit = false;
    }

    boolean isHit() {
        return hit;
    }

    void setIsHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", stateOfField=" + stateOfField +
                ", hit=" + hit +
                '}';
    }
}