package wkbp.battleships.model;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

/**
 * @author Wiktor Rup
 */
public class UpdatesAfterShotTest {

    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
    private Board board = new BoardFactory(gameConfig).createBoard();
    private BoardUpdater boardUpdater = new BoardUpdater(board);


    @Test
    public void testUpdateBoard() {
        board.getField(0).setStateOfField(StateOfField.OCCUPIED);
        board.getField(1).setStateOfField(StateOfField.OCCUPIED);
        ShotOutcome shotOutcome = boardUpdater.updateBoard(new Move(1, new User(), new Field(1)));
        Field testField = new Field(1);
        testField.setStateOfField(StateOfField.OCCUPIED);
        testField.setIsHit(true);
        assertTrue(shotOutcome.isPlayerTurn());
        assertFalse(shotOutcome.isPlayerWon());
        assertEquals(testField, shotOutcome.getField());
    }
}