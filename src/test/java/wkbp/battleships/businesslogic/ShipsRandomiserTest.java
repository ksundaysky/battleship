package wkbp.battleships.businesslogic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Wiktor Rup
 */
@Test
public class ShipsRandomiserTest {

    private List<Field> fullFleetList;
    private Board board;
    private Fleet fleet;


    @BeforeMethod
    public void beforeMethods() {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        board = new Board(fieldList);
        fleet = new Fleet(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1)));
        fullFleetList = new ShipsRandomiser(board, fleet).randomizeShips();
    }

    @Test
    public void checkIfReturnedFieldsAreOccupied(){
        for (Field field : fullFleetList) {
            assertEquals(field.getStateOfField(), StateOfField.OCCUPIED);
        }
    }
    @Test
    public void checkIfAllShipsHasBeenPlaced(){
        // TODO: 16.05.19 DataProvider żeby sprawdzić różne floty
        int shipFieldCounter = 0;
        for (Ship ship : fleet.getShipList()) {
            shipFieldCounter += ship.getSize();
        }

        assertEquals(fullFleetList.size(), shipFieldCounter);
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenAllFieldsAreOccupied(){
        for (Field field : board.getFieldList()) {
            field.setStateOfField(StateOfField.OCCUPIED);
        }
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenAllFieldsAreIllegalToPlace(){
        for (Field field : board.getFieldList()) {
            field.setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
        }
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenToBigFleetIsGiven(){
        fleet = new Fleet(Arrays.asList(
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4)));
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenShipCannotFitOnBoard(){
        fleet = new Fleet(Arrays.asList(new Ship(11)));
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

}