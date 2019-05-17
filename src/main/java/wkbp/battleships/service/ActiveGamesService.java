package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.exception.NoAvailableGamesException;
import wkbp.battleships.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Patryk Kucharski
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

    public List<Field> returnUserFleet(Long id, String username) { // TODO: 17.05.19 ta metoda nie tutaj xd
        User user = getUserFromDataBase(username);
        Game game = getGameById(id);
        game.setGameState(GameState.IN_PROGRESS);
        Board userBoard = game.getBoardByUser(user);

        return userBoard.getFieldList()
                .stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .collect(Collectors.toList());
    }

    public Map<Long, Game> returnListOfGames() {
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display!");
        }
        return games;
    }

    void addPlayerToTheGame(Long gameId, String playersName, Game game) {

        User owner = getUserFromDataBase(playersName);
        GameEntity gameEntity = gameRepository.getOne(gameId);
        UserInGameEntity userInGameEntity = new UserInGameEntity(owner, gameEntity);
        userInGameRepository.save(userInGameEntity);
        game.addPlayerToTheGame(owner);
    }

    boolean checkIfUserCanJoinTheGame(long id, String username) {
        User user = getUserFromDataBase(username);
        Game game = getGameById(id);
        if (game.gameContainsPlayer(user))
            return true;
        else return game.getNumberOfPlayers() <= 2;
    }

    User getUserFromDataBase(String name) {
        return userRepository.findByUsername(name).get();
    }

    void addGameToActiveGames(long id, Game game) {
        games.put(id, game);
    }

    Game getGameById(Long id) {
        return games.get(id);
    }

    User setStartingPlayer(GameConfig config, int numberOfPlayers, String playersName, User startingPlayer) {
        if (config.doesOwnerStart() && numberOfPlayers == 0) {
            return getUserFromDataBase(playersName);
        } else if (!config.doesOwnerStart() && numberOfPlayers == 1) {
            return getUserFromDataBase(playersName);
        }
        return startingPlayer;
    }
}
