package wkbp.battleships.businesslogic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

    @BeforeMethod
    public void beforeMethods() {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        Board board = new Board(fieldList);
        Fleet fleet = new Fleet(Arrays.asList(new Ship(4), new Ship(3), new Ship(3), new Ship(2), new Ship(2), new Ship(2), new Ship(1), new Ship(1), new Ship(1), new Ship(1)));
        fullFleetList = new ShipsRandomiser(board, fleet).randomizeShips();
    }

    @Test
    public void checkIfReturnedFieldsAreOccupied(){
        for (Field field : fullFleetList) {
            assertEquals(field.getStateOfField(), StateOfField.OCCUPIED);
        }
    }

}