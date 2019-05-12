package wkbp.battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is representation of a ship - main component used in the battleship game.
 * The ship consist of fields {@link wkbp.battleship.model.Field}
 * @author Wiktor Rup
 */
public class Ship {
    private int size;
    private List<Field> fieldList;

    public Ship(int size) {
        this.size = size;
        this.fieldList = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }
}
