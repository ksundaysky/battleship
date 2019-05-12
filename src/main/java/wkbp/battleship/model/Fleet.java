package wkbp.battleship.model;

import java.util.List;

/**
 * This is a docker for Ships {@link wkbp.battleship.model.Ship}. It represents fleet which is available in battleship game.
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
