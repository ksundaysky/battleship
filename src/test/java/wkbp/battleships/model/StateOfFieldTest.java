package wkbp.battleships.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class StateOfFieldTest {

    @Test
    public void testSetHit() {
        StateOfField stateOfField = StateOfField.OCCUPIED;

        stateOfField.setHit(true);

        assertTrue(stateOfField.isHit);
    }
}