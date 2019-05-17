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
    private Map<User, Board> playersInGame;
    private GameConfig gameConfig;
    private GameState gameState;
    private User currentPlayer;

    public Game(GameConfig gameConfig) {
        playersInGame = new HashMap<>();
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

    public void addPlayerToTheGame(User user) {
        playersInGame.put(user, new BoardFactory(gameConfig).createBoard());
    }

    public void addUserBoard(User user, Board board) {
        playersInGame.put(user, board);
    }

    public Board getBoardByUser(User user) {
        return playersInGame.get(user);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfPlayers() {
        return playersInGame.size();
    }

    public boolean gameContainsPlayer(User user) {
        return playersInGame.containsKey(user);
    }

    public Map<User, Board> getPlayersInGame() {
        return playersInGame;
    }

    public void setCurrentPlayer(User currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }
}
