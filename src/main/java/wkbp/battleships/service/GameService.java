package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.GameIsFullException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.model.Game;
import wkbp.battleships.model.GameState;
import wkbp.battleships.model.User;

import javax.naming.NoPermissionException;

/**
 * Responsible for creation of the game and setting it up
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ActiveGamesService activeGamesService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInGameRepository userInGameRepository;

    public long createGame(ConfigDTO configDTO) {
        Game game = new Game(configDTO.assembly());
        GameEntity gameEntity = new GameEntity(game.getGameState());
        gameEntity = gameRepository.save(gameEntity);
        game.setId(gameEntity.getId());
        activeGamesService.addGameToActiveGames(gameEntity.getId(), game);
        return gameEntity.getId();
    }

    public String tryToJoinTheGame(long gameId, String playersName) throws GameIsFullException {
        if (!userCanJoinTheGame(gameId, playersName)) {
            throw new GameIsFullException("You cannot join. Game is full!");
        } else {
            Game game = getGameById(gameId);
            setStartingPlayer(game, playersName);
            addPlayerToTheGame(gameId, playersName, game);
        }
        return "Success";
    }

    public boolean isPlayersGame(long id, String user) throws NoPermissionException {
        User player = getUserFromDataBase(user);
        Game game = getGameById(id);
        if (!game.getPlayersInGame().containsKey(player))
            throw new NoPermissionException("No permissions for such action");
        else
            return true;
    }

    private void setStartingPlayer(Game game, String playersName) {
        if (game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 0) {
            game.setCurrentPlayer(userRepository.findByUsername(playersName).get());
        } else if (!game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 1) {
            game.setGameState(GameState.IN_PROGRESS);
            game.setCurrentPlayer(userRepository.findByUsername(playersName).get());
        }
    }

    private void addPlayerToTheGame(Long gameId, String playersName, Game game) {
        User owner = getUserFromDataBase(playersName);
        GameEntity gameEntity = gameRepository.getOne(gameId);
        UserInGameEntity userInGameEntity = new UserInGameEntity(owner, gameEntity);
        userInGameRepository.save(userInGameEntity);
        game.addPlayerToTheGame(owner);
    }

    private boolean userCanJoinTheGame(long gameId, String username) {
        User player = getUserFromDataBase(username);
        Game game = getGameById(gameId);
        return game.containsPlayer(player) || game.getNumberOfPlayers() < 2;
    }

    private Game getGameById(long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String username) {
        return userRepository.findByUsername(username).get();
    }
}
