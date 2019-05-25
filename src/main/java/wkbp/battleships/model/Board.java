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

    public Field getField(int fieldId) {
        return fieldList.get(fieldId);
    }

    public boolean indexExists(final int index) {
        return index >= 0 && index < fieldList.size();
    }

    public int getSize() {
        return fieldList.size();
    }

    List<Field> neighboursOfShip(Field fieldToShot) {
        int counter = 0;
        for (Ship ship : fleet.getShipList()) {
            for (Field field : ship.getFieldsOfShip()) {
                if (field.getId() == fieldToShot.getId()) {
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

//        for (Ship ship : fleet.getShipList()) {
//            ship.getFieldsOfShip().stream()
//                    .filter(x -> x.getId() == fieldToShot.getId())
//                    .filter(Field::isHit)
//                    .forEach(x -> counter.getAndIncrement());
//            if (counter.get() == ship.getSize()) {
//                return ship.getNeighbourFields();
//            }
//        }

        return null; //tak ma być
    }
}
