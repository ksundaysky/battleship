package wkbp.battleships.dao.repository.entity;

import org.testng.annotations.Test;
import wkbp.battleships.model.GameState;

import static org.testng.Assert.assertEquals;

/**
 * @author Wiktor Rup
 */
public class GameEntityTest {

    private GameEntity gameEntity = new GameEntity(GameState.WAITING_FOR_PLAYER);

    @Test
    public void testGetGameState() {
        GameState expectedGameState = GameState.WAITING_FOR_PLAYER;
        GameState actualGameState = gameEntity.getGameState();

        assertEquals(expectedGameState, actualGameState);
    }
}