package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author Wiktor Rup
 */
@AllArgsConstructor
@Getter
public class FleetConfig {

    private Map<Integer, Integer> fleetConfig;
}
