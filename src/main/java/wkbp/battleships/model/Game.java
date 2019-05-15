package wkbp.battleships.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents single game, and is responsible for initializing
 * and creating necessary components
 *
 * @author Wiktor Rup
 */
public class Game {

    private long id;
    private Gameplay gameplay;
    private Map<User, Board> currentPlayers;
    private GameConfig gameConfig;
    private GameState gameState;

    public Game(User owner, GameConfig gameConfig){ //TODO hehe
        currentPlayers = new HashMap<>();
        currentPlayers.put(owner, null);
        this.gameConfig = gameConfig;
        this.gameplay = new Gameplay(); //TODO coś dostanie chyba kongifg
        this.gameState = GameState.IN_PREPARATION;
    }
}
