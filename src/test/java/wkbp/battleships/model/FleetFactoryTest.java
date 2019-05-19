package wkbp.battleships.model;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
public class FleetFactoryTest {

    private Map<Integer, Integer> configMap = new HashMap<>();
    private List<Ship> shipsInFleet = new ArrayList<>();

    @Test
    public void testGenerateFleet() {

        configMap.put(1, 1);
        shipsInFleet.add(new Ship(1));
        Fleet fleet = new Fleet(shipsInFleet);
        FleetConfig fleetConfig = new FleetConfig(configMap);
        Fleet factoryFleet = new FleetFactory().generateFleet(fleetConfig);

        assert fleet.equals(factoryFleet);
    }
}