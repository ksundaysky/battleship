package wkbp.battleship.model;

import java.util.List;

/**
 * Class which is a representation of Board which contains Fields {@link wkbp.battleship.model.Field}
 * @author Wiktor Rup
 */
public class Board {
    private List<Field> fieldList;
    private int sizeOfBoard;

    public Board(List<Field> fieldList) {
        this.fieldList = fieldList;
        sizeOfBoard = (int) Math.sqrt(fieldList.size());
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }
}
