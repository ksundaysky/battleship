package wkbp.battleships.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents single ship unit that is part of fleet {@link Fleet}
 * Consists of List of fields with given state
 *
 * @author Wiktor Rup
 */
@Getter
public class Ship {

    private int size;
    private List<Field> fieldsOfShip;

    public Ship(int size) {
        this.size = size;
        this.fieldsOfShip = new ArrayList<>();
    }
}
