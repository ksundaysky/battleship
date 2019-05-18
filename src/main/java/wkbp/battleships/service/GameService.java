package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.GameIsFullException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.model.Game;

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


    public long createGame(ConfigDTO configDTO) {
        Game game = new Game(configDTO.assembly());
        GameEntity gameEntity = new GameEntity(game.getGameState());
        gameEntity = gameRepository.save(gameEntity);
        game.setId(gameEntity.getId());
        activeGamesService.addGameToActiveGames(gameEntity.getId(), game);
        return gameEntity.getId();
    }

    public String tryToJoinTheGame(long gameId, String playersName) throws GameIsFullException {
        if (!activeGamesService.checkIfUserCanJoinTheGame(gameId, playersName)) {
            throw new GameIsFullException("You cannot join. Game is full!");
        } else {
            Game game = activeGamesService.getGameById(gameId);
            activeGamesService.setStartingPlayer(game, playersName);
            activeGamesService.addPlayerToTheGame(gameId, playersName, game);
        }
        return "Success";
    }
}
