package wkbp.battleships.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;
@Test
public class BoardTest {

    @Test(dataProvider = "indexProvider")
    public void shouldBeTrue_AllIndexesExist(int index) {
        BoardFactory boardFactory = new BoardFactory(
                new GameConfig("testGame", 10, GameMode.STANDARD, true));
        Board board = boardFactory.createBoard();
        assertTrue(board.indexExists(index));
    }

    @DataProvider
    public Object[][] indexProvider() {
        Object[][] indexes = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            indexes [i][0] = i;
        }
        return indexes;
    }


    @Test(dataProvider = "fakeIndexesProvider")
    public void shouldBeFalse_IndexesDontExist(int index) {
        BoardFactory boardFactory = new BoardFactory(
                new GameConfig("testGame", 10, GameMode.STANDARD, true));
        Board board = boardFactory.createBoard();
        assertFalse(board.indexExists(index));
    }

    @DataProvider
    public Object[][] fakeIndexesProvider() {
        Object[][] indexes = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            indexes [i][0] = i+100;
        }
        return indexes;
    }
}