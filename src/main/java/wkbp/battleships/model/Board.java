package wkbp.battleships.model;

import lombok.Data;

import java.util.List;

/**
 * Represents board and it's content for gameplay purpose
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Data
public class Board {

    private List<Field> fieldList;
    private int dimension;
    private Fleet fleet;

    /**
     * since board is always a square dimensions
     * are taken as a square root of filed number
     *
     * @param fieldList which is to be created based on gameConfig passed
     *                  to BoardFactory.class
     * @see BoardFactory
     */
    public Board(List<Field> fieldList, Fleet fleet) {
        this.fieldList = fieldList;
        dimension = (int) Math.sqrt(fieldList.size());
        this.fleet = fleet;
    }

    /**
     * Returns neighbouring fields {@link Field}
     * of the ship {@link Ship} if one is indeed sunken,
     * otherwise returns null which is handled by client
     *
     *
     * @param lastFiredField last field that was fired at
     * @return List<Field> with neighbouring fields of ship,
     *                     or null if shoot didn't sunk the ship
     */
    List<Field> neighboursOfShip(Field lastFiredField) {
        int counter = 0;
        for (Ship ship : fleet.getShipList()) {
            for (Field field : ship.getFieldsOfShip()) {
                if (field.getId() == lastFiredField.getId()) {
                    for (Field f : ship.getFieldsOfShip()) {
                        if (f.isHit()) {
                            counter++;
                            if (counter == ship.getSize()) {
                                return ship.getNeighbourFields();
                            }
                        }
                    }
                }
            }
        }
        return null; //this is desired outcome
    }

    public Field getField(int fieldId) {
        return fieldList.get(fieldId);
    }

    public boolean indexExists(final int index) {
        return index >= 0 && index < fieldList.size();
    }

    public int getSize() {
        return fieldList.size();
    }
}
