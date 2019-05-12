package wkbp.battleships.model;

import java.util.List;

/**
 * @author Wiktor Rup
 */
public class Board {


    private List<Field> fieldList;
    private int columns;

    public Board(List<Field> fieldList) {
        this.fieldList = fieldList;
        columns = (int) Math.sqrt(fieldList.size());
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public int getColumns() {
        return columns;
    }
}
