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

    public ShotOutcome isPlayerTurn(long id, String playersName) {
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(id);
        if (game.getGameQueues().get(player).peek() != null) {
            ShotOutcome shotOutcome = game.getGameQueues().get(player).poll();
            if (player.equals(game.getCurrentPlayer())) {
                shotOutcome.setMessage(game.getMessagesForOwner().poll());
                return shotOutcome;
            } else {
                shotOutcome.setMessage(game.getMessagesForOpponent().poll());
                return shotOutcome;
            }
        } else {
            return new ShotOutcome(player.equals(game.getCurrentPlayer()), null, false, null);
        }// TODO: 22.05.19 nie działa xd, dlaczego? Wiktur halp
    }

    private Game getGameById(Long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String playersName) {
        return userRepository.findByUsername(playersName).get();
    }
}
