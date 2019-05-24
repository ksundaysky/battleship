package wkbp.battleships.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.NoAvailableGamesException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dto.GameDTO;
import wkbp.battleships.model.Game;
import wkbp.battleships.model.User;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    private Map<Long, Game> games = new HashMap<>();

    public List<GameDTO> getListOfGames() {
        if (games.isEmpty()) {
            logger.error("class ActiveGameService, method getListOfGames; games.isEmpty = true; throwing NoAvailableGamesException");
            throw new NoAvailableGamesException("No available games to display");
        }
        List<GameDTO> listOfGames = new ArrayList<>();
        for(Map.Entry<Long, Game> games : games.entrySet()){
            listOfGames.add(convertToGameDTO(games.getValue()));
        }
        logger.info("class ActiveGameService, method getListOfGames; returning List<GameDTO> games: " + listOfGames);
        return listOfGames;
    }

    private GameDTO convertToGameDTO(Game game) {
        return new GameDTO(game.getId(), game.getGameState(), game.getGameConfig().getGameName(), game.getGameConfig().getDimension(), game.getGameConfig().getGameMode());
    }

    public boolean isGameReady(long gameId) {
        boolean isGameReady = games.get(gameId).getHowManyPlayersAreReady() == 2;
        logger.info("class ActiveGameService, method isGameReady(); returning " + isGameReady);
        return isGameReady;
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
