package wkbp.battleships.businesslogic;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Wiktor Rup,
 * @author Patryk Kucharski,
 * @author Krzysztof Niedzielski,
 * @author Bartosz Kupajski
 */
@Test
public class ShipsRandomiserTest {

    private List<Field> fullFleetList;
    private Board board;
    private Fleet fleet;


    // TODO: 16.05.19 BoardFactory będzie tworzył te floty
    private void initializeBoard() {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        board = new Board(fieldList);
    }


    @BeforeTest
    public void beforeMethods() {
        initializeBoard();
        fleet = new Fleet(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1)));
        fullFleetList = new ShipsRandomiser(board, fleet).randomizeShips();
    }

    @Test(invocationCount = 10000, successPercentage = 90)
    public void shouldPlaceGivenFleet() {
        initializeBoard();
        new ShipsRandomiser(board, fleet).randomizeShips();
    }

    @Test
    public void checkIfReturnedFieldsAreOccupied() {
        for (Field field : fullFleetList) {
            assertEquals(field.getStateOfField(), StateOfField.OCCUPIED);
        }
    }

    @Test
    public void checkIfAllShipsHasBeenPlaced() {
        // TODO: 16.05.19 DataProvider żeby sprawdzić różne floty
        int shipFieldCounter = 0;
        for (Ship ship : fleet.getShipList()) {
            shipFieldCounter += ship.getSize();
        }

        assertEquals(fullFleetList.size(), shipFieldCounter);
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenAllFieldsAreOccupied() {
        for (Field field : board.getFieldList()) {
            field.setStateOfField(StateOfField.OCCUPIED);
        }
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenAllFieldsAreIllegalToPlace() {
        for (Field field : board.getFieldList()) {
            field.setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
        }
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenToBigFleetIsGiven() {
        Fleet tooBigFleet = new Fleet(Arrays.asList(
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4), new Ship(4), new Ship(4),
                new Ship(4), new Ship(4)));
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, tooBigFleet);
        shipsRandomiser.randomizeShips();
    }

    @Test(expectedExceptions = CantPlaceShipsException.class)
    public void shouldThrowCantPlaceShipsExceptionWhenShipCannotFitOnBoard() {
        Fleet containingTooBigShip = new Fleet(Arrays.asList(new Ship(11)));
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, containingTooBigShip);
        shipsRandomiser.randomizeShips();
    }

}