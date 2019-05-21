package wkbp.battleships.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents single game, and is responsible for initializing
 * and creating necessary components
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
@Setter
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
        this.gameState = GameState.WAITING_FOR_PLAYER;
    }

    public void addPlayerToTheGame(User user) {
        playersInGame.put(user, new BoardFactory(gameConfig).createBoard());
    }

    public void addUserAndHisBoard(User user, Board board) {
        playersInGame.put(user, board);
    }

    public Board getBoardByUser(User user) {
        return playersInGame.get(user);
    }

    public int getNumberOfPlayers() {
        return playersInGame.size();
    }

    public boolean containsPlayer(User user) {
        return playersInGame.containsKey(user);
    }

    /**
     * This method is responsible for marking changes on opponent's components basing on Move parameter.
     * Negations inside conditional sentences are used for extracting proper Player from the Game.
     *
     * @param move - {@link Move}
     * @return outcome - {@link ShotOutcome}
     */
    public ShotOutcome moveHasBeenMade(Move move) {
        for (Map.Entry<User, Board> entry : playersInGame.entrySet()) {
            if (!entry.getKey().equals(move.getPlayer())) {
                ShotOutcome outcome = gameplay.update(move, entry.getValue());
                if (!outcome.isPlayerTurn()) {
                    setCurrentPlayer(entry.getKey());
                }
                return outcome;
            }
        }
        return null; // TODO: 2019-05-18 - ta linijka nigdy nie zostanie wykonana
    }
}
