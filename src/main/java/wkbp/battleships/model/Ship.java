package wkbp.battleships.model;

import java.util.ArrayList;
import java.util.List;

/**
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
