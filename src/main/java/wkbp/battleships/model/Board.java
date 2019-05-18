package wkbp.battleships.model;

import lombok.Getter;

import java.util.List;

/**
 * Represents board and it's content for gameplay purpose
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 */
@Getter
public class Board {

    private List<Field> fieldList;
    private int dimension;

    /**
     * since board is always a square dimensions
     * are taken as a square root of filed number
     *
     * @param fieldList which is to be created based on gameConfig passed
     *                  to BoardFactory.class
     * @see BoardFactory
     */
    public Board(List<Field> fieldList) {
        this.fieldList = fieldList;
        dimension = (int) Math.sqrt(fieldList.size());
    }

    public boolean indexExists(final int index) {
        return index >= 0 && index < fieldList.size();
    }
}
