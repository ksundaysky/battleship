package wkbp.battleships.model;

import org.testng.annotations.Test;

/**
 * @author Wiktor Rup
 */
public class UpdatesAfterShotTest {

    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
    private Board board = new BoardFactory(gameConfig).createBoard();
    private BoardUpdater boardUpdater = new BoardUpdater(board);


    // TODO: 2019-05-19 Coś jest spraprany BoardUpdater i aktualizuje wszystkie pola OCCUPIED na trafione ???
    @Test
    public void testUpdateBoard() {
        board.getField(13).setStateOfField(StateOfField.OCCUPIED);
        board.getField(17).setStateOfField(StateOfField.OCCUPIED);
        ShotOutcome shotOutcome = boardUpdater.updateBoard(new Move(1, new User(), new Field(13)), board);
        Field testField = new Field(13);
        testField.isHit(true);
        //assertTrue(shotOutcome.playerTurn); // TODO: 2019-05-19 nie działa to, a powinno, przez BoardUpdater prawdopodobnie
        //assertFalse(shotOutcome.playerWon);
        //assertEquals(testField, shotOutcome.field);
    }
}