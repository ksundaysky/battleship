package wkbp.battleships.model;

import java.util.List;

/**
 * @author Wiktor Rup
 */
// TODO: 13.05.19 dokumentacja
public class Board {


    private List<Field> fieldList;
    private int dimension;

    public Board(List<Field> fieldList) {
        this.fieldList = fieldList;
        dimension = (int) Math.sqrt(fieldList.size());
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public int getDimension() {
        return dimension;
    }
}
