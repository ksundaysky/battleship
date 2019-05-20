package wkbp.battleships.dao.repository.entity;

import org.testng.annotations.Test;
import wkbp.battleships.model.GameState;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class GameEntityTest {

    private GameEntity gameEntity = new GameEntity(GameState.IN_PREPARATION);

    @Test
    public void testGetGameState() {
        GameState expectedGameState = GameState.IN_PREPARATION;
        GameState actualGameState = gameEntity.getGameState();

        assert expectedGameState.equals(actualGameState);
    }
}