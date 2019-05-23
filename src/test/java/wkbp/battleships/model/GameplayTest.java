package wkbp.battleships.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Wiktor Rup
 */
public class GameplayTest {
    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);

    private Board board;
    private Gameplay gameplay;

    @BeforeMethod
    public void initialize() {
        board = new BoardFactory(gameConfig).createBoard();
        board.getField(1).setStateOfField(StateOfField.OCCUPIED);
        board.getField(2).setStateOfField(StateOfField.OCCUPIED);
        gameplay = new Gameplay(board);

    }

    @Test
    public void testUpdate() {
        Field resultShotField = new Field(2);
        resultShotField.setStateOfField(StateOfField.OCCUPIED);
        resultShotField.setIsHit(true);

        ShotOutcome expectedShotOutcome = new ShotOutcome(true, resultShotField, false);
        ShotOutcome actualShotOutcome = gameplay.update(new Move(1, new User(), new Field(2)), board);
        assertEquals(expectedShotOutcome, actualShotOutcome);
    }

    @Test
    public void testGetBoard() {
        assert gameplay.getBoard().equals(board);
    }

    @Test
    public void testGetBoardUpdater() {
        BoardUpdater expectedBoardUpdater = new BoardUpdater(board);
        BoardUpdater actualBoardUpdater = gameplay.getBoardUpdater();

        assertEquals(expectedBoardUpdater, actualBoardUpdater);

    }

    @Test
    public void testGetLastMove() {
        Move expectedMove = new Move(1, new User(), new Field(2));
        gameplay.update(expectedMove, board);
        Move actualMove = gameplay.getLastMove();

        assertEquals(expectedMove, actualMove);
    }

    @Test
    public void testGetMoves() {
        List<Move> expectedMoves = new ArrayList<>();
        expectedMoves.add(new Move(1, new User(), new Field(2)));

        gameplay.update(new Move(1, new User(), new Field(2)), board);
        List<Move> actualMoves = gameplay.getMoves();

        assertEquals(expectedMoves, actualMoves);
    }
}