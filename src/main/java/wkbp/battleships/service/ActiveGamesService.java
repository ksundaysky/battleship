package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.NoAvailableGamesException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.model.*;

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Service
public class ActiveGamesService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInGameRepository userInGameRepository;
    @Autowired
    private GameRepository gameRepository;

    private Map<Long, Game> games = new HashMap<>();

    public ShotOutcome makeAShoot(Long gameId, String playersName, Field field) throws NoPermissionException { // TODO: 17.05.19 ta metoda również nie tutaj końcowo
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("No permissions for such action");
        }
        return game.moveHasBeenMade(new Move(gameId, player, field));
    }

    public boolean isGameReady(long gameId) {
        return games.get(gameId).getNumberOfPlayers() == 2;
    }

    public Map<Long, Game> getListOfGames() {
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display");
        }
        return games;
    }

    User getUserFromDataBase(String username) {
        return userRepository.findByUsername(username).get();
    }

    void addGameToActiveGames(long gameId, Game game) {
        games.put(gameId, game);
    }

    Game getGameById(Long gameId) {
        return games.get(gameId);
    }
}
