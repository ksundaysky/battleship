package wkbp.battleships.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
    private Map<User, Queue<ShotOutcome>> gameQueues;
    private GameConfig gameConfig;
    private GameState gameState;
    private User currentPlayer;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    private int howManyPlayersAreReady = 0;

    public Game(GameConfig gameConfig) {
        playersInGame = new HashMap<>();
        gameQueues = new HashMap<>();
        this.gameConfig = gameConfig;
        this.gameState = GameState.WAITING_FOR_PLAYER;
    }

    public void addPlayerToTheGame(User user) {
        playersInGame.put(user, new BoardFactory(gameConfig).createBoard());
        gameQueues.put(user, new LinkedList<>());
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

    public void addReadyPlayer(){
        howManyPlayersAreReady++;
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
                    logger.info("class Game, method moveHasBeenMade(); setting currentPlayer " + entry.getKey().toString());
                }
                logger.info("class Game, method moveHasBeenMade(); returning " + outcome.toString());
                return outcome;
            }
        }
        return null; //this line will never be reached
    }
}
