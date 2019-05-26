package wkbp.battleships.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.model.*;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import javax.naming.NoPermissionException;

/**
 * Service handling all request during actual gameplay between two players
 *
 * @author Patryk Kucharski
 */
@Service
public class GameplayService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ActiveGamesService activeGamesService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInGameRepository userInGameRepository;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    /**
     * Handles shoot request made by one of the players
     *
     * @param gameId      Id of game where shoot was made
     * @param playersName player that fired
     * @param field       field where player fired at
     * @return outcome of move made by player {@link ShotOutcome}
     * @throws NoPermissionException when request was made by player who is not participating in given game
     */
    public ShotOutcome makeAShoot(Long gameId, String playersName, Field field) throws NoPermissionException {
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            logger.error("class GameplayService, method makeAShoot(); game with id: " +
                    " doesn't contain player: " + playersName);
            throw new NoPermissionException("No permissions for such action");
        }
        return game.moveHasBeenMade(new Move(gameId, player, field));
    }

    /**
     * Handles request from client asking if it's his turn to make a move in the game
     *
     * @param gameId      id of the game form which request was made
     * @param playersName name of requesting player
     * @return outcome with additional information like move made by opponent, and last transcript of previous move
     * @throws NoPermissionException when request was made by user outside of the game
     */
    public ShotOutcome isPlayerTurn(long gameId, String playersName) throws NoPermissionException {
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("You have no power here!");
        } else {
            if (game.getGameQueues().get(player).peek() != null) {
                ShotOutcome shotOutcome = game.getGameQueues().get(player).poll();
                shotOutcome.setMessage(game.getMessages().get(player).poll());
                return shotOutcome;
            } else {
                return new ShotOutcome(player.equals(game.getCurrentPlayer()), null,
                        null, false, game.getMessages().get(player).poll());
            }
        }
    }

    private Game getGameById(Long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String playersName) {
        return userRepository.findByUsername(playersName).get();
    }
}
