package wkbp.battleships.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class which creates Boards {@link Fleet}
 *
 * @author Wiktor Rup
 */
public class FleetFactory {

    FleetConfig fleetConfig;
    private GameConfig gameConfig;


    public Fleet generateFleet() {
        List<Ship> shipsInFleet = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : fleetConfig.getFleetConfig().entrySet()) {
            for (int i = 0; i < entry.getKey(); i++) {
                shipsInFleet.add(new Ship(entry.getValue()));
            }
        }
        return new Fleet(shipsInFleet);
    }
}


