package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Represents given pool of ships as a unit to be placed on board
 * Consists of List of ships {@link Ship}
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Fleet {

    private List<Ship> shipList;
}
