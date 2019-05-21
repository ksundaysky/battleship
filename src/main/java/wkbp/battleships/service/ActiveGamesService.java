package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.NoAvailableGamesException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.model.Game;
import wkbp.battleships.model.User;

import java.util.HashMap;
import java.util.Map;

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

    public Map<Long, Game> getListOfGames() {
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display");
        }
        return games;
    }

    public boolean isGameReady(long gameId) {
        return games.get(gameId).getHowManyPlayersAreReady() == 2;
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
