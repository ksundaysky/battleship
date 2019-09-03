package wkbp.battleships.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class which creates Boards {@link Fleet}
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
public class FleetFactory { //not used currently, but will be in future

    FleetConfig fleetConfig;
    private GameConfig gameConfig;

    public static Fleet standardFleet() {
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        for (int i = 0; i < fleet.getShipList().size(); i++) {
            fleet.getShipList().get(i).setId(i);
        }
        return fleet;
    }

    Fleet generateFleet(FleetConfig fleetConfig) {
        List<Ship> shipsInFleet = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : fleetConfig.getFleetConfig().entrySet()) {
            for (int i = 0; i < entry.getKey(); i++) {
                shipsInFleet.add(new Ship(entry.getValue()));
            }
        }
        return new Fleet(shipsInFleet);
    }
}


