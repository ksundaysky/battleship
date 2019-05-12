package wkbp.battleships.model;

import java.util.List;

/**
 * @author Wiktor Rup
 */
public class Fleet {


    private List<Ship> shipList;

    public Fleet(List<Ship> shipList) {
        this.shipList = shipList;
    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
