package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents single cell of the board which can be shot at
 * <p>
 * as board object {@link Board} has a List of fields, each field has it's own unique id
 * also field has a state {@link StateOfField}
 *
 * @author Krzysztof Niedzielski
 * @author Patryk Kucharski
 */
@Getter
@Setter
@AllArgsConstructor
public class Field {

    private int id;
    private StateOfField stateOfField;

    public Field(int id) {
        this.id = id;
        stateOfField = StateOfField.EMPTY;
        stateOfField.isHit = false;
    }

    void isHit(boolean isHit) {
        stateOfField.setHit(isHit);
    }
}