package wkbp.battleships.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class MoveValidatorTest {

    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
    Board board = new BoardFactory(gameConfig).createBoard();

    @Test
    public void testValidMove() {

        MoveValidator moveValidator = new MoveValidator(new Move(1, new User(), new Field(2)), gameConfig, board);

        assert moveValidator.validateMove();
    }

    @Test
    public void testNegativeNotValidMove(){
        MoveValidator moveValidator = new MoveValidator(new Move(1, new User(), new Field(-12)), gameConfig, board);

        assert !moveValidator.validateMove();

    }

    @Test
    public void testPositiveNotValidMove(){
        MoveValidator moveValidator = new MoveValidator(new Move(1, new User(), new Field(155)), gameConfig, board);

        assert !moveValidator.validateMove();
    }

}