package wkbp.battleships.model;

import javax.jws.soap.SOAPBinding;
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

    public Game(GameConfig gameConfig) {
        currentPlayers = new HashMap<>();
        this.gameConfig = gameConfig;
        this.gameplay = new Gameplay(); //TODO coś dostanie chyba kongifg
        this.gameState = GameState.IN_PREPARATION;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameConfig getConfig() {
        return gameConfig;
    }

    public void addPlayerToTheGame(User user, Board board) {
        currentPlayers.put(user, board);
    }

    public Board getBoardByUser(User user){
        return currentPlayers.get(user);
    }
}
