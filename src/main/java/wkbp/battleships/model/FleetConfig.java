package wkbp.battleships.model;

import java.util.Map;

/**
 * @author Wiktor Rup
 */
public class FleetConfig {

    private Map<Integer, Integer> fleetConfig;

    public FleetConfig(Map<Integer, Integer> fleetConfig) {
        this.fleetConfig = fleetConfig;
    }

    public Map<Integer, Integer> getFleetConfig() {
        return fleetConfig;
    }
}
