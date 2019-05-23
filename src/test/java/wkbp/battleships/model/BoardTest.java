package wkbp.battleships.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

@Test
public class BoardTest {

    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
    private Board board = new BoardFactory(gameConfig, FleetFactory.standardFleet()).createBoard();

    @Test(dataProvider = "indexProvider")
    public void shouldBeTrue_AllIndexesExist(int index) {
        BoardFactory boardFactory = new BoardFactory(
                new GameConfig("testGame", 10, GameMode.STANDARD, true), FleetFactory.standardFleet());
        Board board = boardFactory.createBoard();
        assertTrue(board.indexExists(index));
    }

    @DataProvider
    public Object[][] indexProvider() {
        Object[][] indexes = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            indexes[i][0] = i;
        }
        return indexes;
    }


    @Test(dataProvider = "fakeIndexesProvider")
    public void shouldBeFalse_IndexesDontExist(int index) {
        BoardFactory boardFactory = new BoardFactory(
                new GameConfig("testGame", 10, GameMode.STANDARD, true), FleetFactory.standardFleet());
        Board board = boardFactory.createBoard();
        assertFalse(board.indexExists(index));
    }

    @DataProvider
    public Object[][] fakeIndexesProvider() {
        Object[][] indexes = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            indexes[i][0] = i + 100;
        }
        return indexes;
    }

    @Test
    public void testGetField() {
        assert board.getField(10).equals(new Field(10));
    }

    @Test
    public void testGetSize() {
        assert board.getSize() == 100;
    }

    @Test
    public void testGetFieldList() {
        List<Field> list = new ArrayList<>();
        for (int i = 0; i < Math.pow(board.getDimension(), 2); i++)
            list.add(new Field(i));
        assertEquals(board.getFieldList(), list);
    }

    @Test
    public void testGetDimension() {
        assert board.getDimension() == 10;
    }

    @Test
    public void testSetFieldList() {
        List<Field> list = new ArrayList<>();
        for (int i = 0; i < Math.pow(13, 2); i++)
            list.add(new Field(i));
        board.setFieldList(list);

        assertEquals(board.getFieldList(), list);
    }

    @Test
    public void testSetDimension() {
        board.setDimension(13);
        assertEquals(board.getDimension(), 13);
    }

    @Test
    public void testEquals1() {
        Board testBoard = new BoardFactory(gameConfig, FleetFactory.standardFleet()).createBoard();

        assertEquals(testBoard, board);

    }
}