package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.model.*;

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

    public ShotOutcome makeAShoot(Long gameId, String playersName, Field field) throws NoPermissionException { // TODO: 17.05.19 ta metoda również nie tutaj końcowo
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("No permissions for such action");
        }
        return game.moveHasBeenMade(new Move(gameId, player, field));
    }

    public boolean isPlayerTurn(long id, String playersName) {
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(id);
        return player.equals(game.getCurrentPlayer());
    }

    private Game getGameById(Long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String playersName) {
        return userRepository.findByUsername(playersName).get();
    }
}
