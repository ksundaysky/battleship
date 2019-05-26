package wkbp.battleships.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

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
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    /**
     * Creates game and adds it to active games
     *
     * @param configDTO params of the game specified by user
     * @return id of the created game
     */
    public long createGame(ConfigDTO configDTO) {
        Game game = new Game(configDTO.assembly());
        GameEntity gameEntity = new GameEntity(game.getGameState());
        gameEntity = gameRepository.save(gameEntity);
        logger.info("class GameService, method createGame(); createdGame: " + game.toString()
                + " and saved gameEntity: " + gameEntity.toString() + " to database");
        game.setId(gameEntity.getId());
        activeGamesService.addGameToActiveGames(gameEntity.getId(), game);
        logger.info("class GameService, method createGame(); adding game to activeGames: " + game.toString());
        logger.info("class GameService, method createGame(); returning gameId: " + gameEntity.getId());
        return gameEntity.getId();
    }

    /**
     * Checks if game can be join
     *
     * @param gameId id of the game to join
     * @param playersName player requesting joining
     * @return outcome message of request
     * @throws GameIsFullException when game already contains 2 players
     */
    public String tryToJoinTheGame(long gameId, String playersName) throws GameIsFullException {
        if (!userCanJoinTheGame(gameId)) {
            throw new GameIsFullException("Cannot join the game, it's already full");
        } else {
            Game game = getGameById(gameId);
            setStartingPlayer(game, playersName);
            addPlayerToTheGame(gameId, playersName, game);
            logger.info("class GameService, method tryToJoinTheGame(); added player: "
                    + playersName + " to the game with id: " + gameId);
            if (game.getNumberOfPlayers() == 2) {
                game.setGameState(GameState.IN_PROGRESS);
            }
            logger.info("class GameService, method tryToJoinTheGame(); Number of players: " +
                    game.getNumberOfPlayers() + ". Set gameState: "
                    + game.getGameState() + " to the game with id: " + gameId);
        }
        return "Success";
    }

    public boolean isPlayersGame(long id, String user) throws NoPermissionException {
        User player = getUserFromDataBase(user);
        Game game = getGameById(id);
        if (!game.getPlayersInGame().containsKey(player))
            throw new NoPermissionException("No permissions for such action!");
        else
            return true;
        }
    }

    private void setStartingPlayer(Game game, String playersName) {
        if (game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 0) {
            game.setCurrentPlayer(getUserFromDataBase(playersName));
        } else if (!game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 1) {
            game.setCurrentPlayer(getUserFromDataBase(playersName));
        }
    }

    private void addPlayerToTheGame(Long gameId, String playersName, Game game) {
        User owner = getUserFromDataBase(playersName);
        GameEntity gameEntity = gameRepository.getOne(gameId);
        UserInGameEntity userInGameEntity = new UserInGameEntity(owner, gameEntity);
        userInGameRepository.save(userInGameEntity);
        game.addPlayerToTheGame(owner);
    }

    private boolean userCanJoinTheGame(long gameId) {
        Game game = getGameById(gameId);
        return game.getNumberOfPlayers() < 2;
    }

    private Game getGameById(long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String username) {
        return userRepository.findByUsername(username).get();
    }
}
