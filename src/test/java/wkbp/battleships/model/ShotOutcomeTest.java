package wkbp.battleships.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class ShotOutcomeTest {

    private Field field = new Field(2);
    private ShotOutcome shotOutcome;

    @BeforeMethod
    public void initialize(){
        field.setStateOfField(StateOfField.OCCUPIED);
        field.isHit(true);
        shotOutcome = new ShotOutcome(true, field, false);
    }

    @Test
    public void testSetPlayerTurn() {
        boolean expectedPlayerTurn = false;
        shotOutcome.setPlayerTurn(false);
        boolean actualPlayerTurn = shotOutcome.isPlayerTurn();

        assert expectedPlayerTurn == actualPlayerTurn;

    }

    @Test
    public void testSetField() {

        Field expectedField = new Field(10);
        shotOutcome.setField(expectedField);
        Field actualField = shotOutcome.getField();

        assert expectedField.equals(actualField);

    }

    @Test
    public void testSetPlayerWon() {

        boolean expectedPlayerWon = true;
        shotOutcome.setPlayerWon(true);

        boolean actualPlayerWon = shotOutcome.isPlayerWon();

        assert expectedPlayerWon == actualPlayerWon;

    }

    @Test
    public void testIsPlayerTurn() {

        boolean expectedPlayerTurn = true;
        boolean actualPlayerTurn = shotOutcome.isPlayerTurn();

        assert expectedPlayerTurn == actualPlayerTurn;
    }

    @Test
    public void testGetField() {

        Field expectedField = new Field(2);
        expectedField.setStateOfField(StateOfField.OCCUPIED);
        expectedField.getStateOfField().setHit(true);

        Field actualField = shotOutcome.getField();

        assert expectedField.equals(actualField);
    }

    @Test
    public void testIsPlayerWon() {

        boolean expectedPlayerWon = false;
        boolean actualPlayerWon = shotOutcome.isPlayerWon();

        assert expectedPlayerWon == actualPlayerWon;
    }
}