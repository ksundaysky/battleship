package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents single ship unit that is part of fleet {@link Fleet}
 * Consists of List of fields with given state
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@Setter
@EqualsAndHashCode
public class Ship {

    private int size;
    private List<Field> fieldsOfShip;
    private List<Field> neighbourFields;
    private int id;

    public Ship(int size) {
        this.size = size;
        this.fieldsOfShip = new ArrayList<>();
        this.neighbourFields = new ArrayList<>();
    }

    public void addField(Field field) {
        fieldsOfShip.add(field);
    }

    public void addToIllegalListOfFields(Field startingPosition) {
        neighbourFields.add(startingPosition);
    }
}
