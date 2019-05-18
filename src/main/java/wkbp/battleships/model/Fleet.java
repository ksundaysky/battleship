package wkbp.battleships.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Represents given pool of ships as a unit to be placed on board
 * Consists of List of ships {@link Ship}
 *
 * @author Wiktor Rup
 */
@AllArgsConstructor
@Getter
public class Fleet {

    private List<Ship> shipList;
}
