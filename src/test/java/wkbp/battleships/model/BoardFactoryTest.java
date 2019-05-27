package wkbp.battleships.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class BoardFactoryTest {

    @Test(dataProvider = "generateEmptyFields")
    public void allFieldsShouldBeEmpty_WhenCreateBoardIsCalled(int fieldId) {

        BoardFactory boardFactory = new BoardFactory(
                new GameConfig(null, 10, null, true), FleetFactory.standardFleet());
        Board emptyBoard = boardFactory.createBoard();
        assertEquals(emptyBoard.getField(fieldId).getStateOfField(), StateOfField.EMPTY);
    }

    @DataProvider
    public Object[][] generateEmptyFields() {
        Object[][] statesOfField = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            statesOfField[i][0] = 1;
        }
        return statesOfField;
    }

    @Test(dataProvider = "generateDimensions")
    public void sizeShouldBeDimensionPow2Test(int dimension) {
        BoardFactory boardFactory = new BoardFactory(
                new GameConfig(null, dimension, null, true),FleetFactory.standardFleet());
        Board emptyBoard = boardFactory.createBoard();
        assertEquals(emptyBoard.getFieldList().size(), (int) Math.pow(dimension, 2));
    }

    @DataProvider
    public Object[][] generateDimensions() {
        Object[][] dimensions = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            dimensions[i][0] = i;
        }
        return dimensions;
    }
}