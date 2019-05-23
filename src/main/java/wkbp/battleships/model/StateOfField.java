package wkbp.battleships.model;

import wkbp.battleships.businesslogic.ShipRandomiser;

/**
 * Represents current state of field on board
 * ILLEGAL_TO_PLACE is used only for purpose
 * of placing ships on board
 * <p>
 * {@link ShipRandomiser}
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
public enum StateOfField {

    EMPTY, OCCUPIED, ILLEGAL_TO_PLACE
}
