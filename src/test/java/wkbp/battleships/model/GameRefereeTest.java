package wkbp.battleships.model;

import org.testng.annotations.Test;
import wkbp.battleships.businesslogic.ShipRandomiser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Test
public class GameRefereeTest {

    private Board initializeBoard() {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        return new Board(fieldList);
    }

    public List<Field> addFleetToBoard(Board board) {
        Fleet fleet = new Fleet(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1)));

        return new ShipRandomiser(board, fleet).randomizeShips();
    }

    public Board burnFleet(Board board) {
        List<Field> fleet = board.getFieldList();
        for (Field field : fleet) {
            if (field.getStateOfField().equals(StateOfField.OCCUPIED)) {
                field.setIsHit(true);
            }
        }
        return board;
    }

    public int getFirstShipId(Board board) {
        for (Field f : board.getFieldList()) {
            if (f.getStateOfField().equals(StateOfField.OCCUPIED)) {
                return f.getId();
            }
        }
        return 1;
    }

    @Test
    public void test_shouldReturnFalse_NotAllFieldsAreHit() {
        Board board = initializeBoard();
        addFleetToBoard(board);
        GameReferee gameReferee = new GameReferee(board);
        assertFalse(gameReferee.checkIfWon());
    }

    @Test
    public void test_shouldReturnTrue_AllFieldsAreHit() {
        Board board = initializeBoard();
        addFleetToBoard(board);
        board = burnFleet(board);
        GameReferee gameReferee = new GameReferee(board);
        assertTrue(gameReferee.checkIfWon());
    }

    @Test(invocationCount = 100, successPercentage = 90)
    public void test_shouldBeTrue_WhenHit() {
        Board board = initializeBoard();
        addFleetToBoard(board);
        GameReferee gameReferee = new GameReferee(board);
        board.getFieldList().get(getFirstShipId(board)).setHit(true);
        gameReferee.setLastMove(new Move(1, null, board.getFieldList().get(getFirstShipId(board))));
        gameReferee.setLastShootHit(true);
        assertTrue(gameReferee.checkIfHitTheShip());
    }
}