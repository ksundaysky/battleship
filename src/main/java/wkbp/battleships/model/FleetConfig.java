package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@AllArgsConstructor
@Getter
public class FleetConfig {

    private Map<Integer, Integer> fleetConfig;
}
