package wkbp.battleships.model;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Wiktor Rup
 */
public class FleetConfigTest {

    Map<Integer, Integer> map = new HashMap<>();

    @Test
    public void testGetFleetConfig() {
        map.put(1, 3);
        FleetConfig fleetConfig = new FleetConfig(map);

        assertEquals(fleetConfig.getFleetConfig(), map);
    }
}