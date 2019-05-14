package wkbp.battleships.model;

import java.util.Map;

/**
 * Represents single game, and is responsible for initializing
 * and creating necessary components
 * @author Wiktor Rup
 */
public class Game {

    private Gameplay gameplay;
    private Map<String, User> currentPlayers;
    private GameConfig gameConfig;
    private GameState gameState;


}
