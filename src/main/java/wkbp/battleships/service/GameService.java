package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.businesslogic.ShipRandomiser;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.controller.CantPlaceShipsException;
import wkbp.battleships.controller.GameIsFullException;
import wkbp.battleships.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Responsible for creation of the game and setting it up
 *
 * @author krzysztof.niedzielski
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
        }
        else {
            Game game = activeGamesService.getGameById(gameId);
            activeGamesService.setStartingPlayer(game, playersName);
            activeGamesService.addPlayerToTheGame(gameId, playersName, game);
        }
        return "Success";
    }

    // TODO: 17.05.19 do innej klasy?
    public List<Field> randomiseShips(Long gameId, String playersName) throws CantPlaceShipsException {
        Game game = activeGamesService.getGameById(gameId);

        // TODO: 17.05.19 zamiana na FleetFactory
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new BoardFactory(game.getConfig()).createBoard();
        ShipRandomiser shipRandomiser = new ShipRandomiser(board, fleet);
        List<Field> ships = shipRandomiser.randomizeShips();
        for (Field field : ships) {
            board.getFieldList().get(field.getId()).setStateOfField(StateOfField.OCCUPIED);
        }
        game.addUserAndHisBoard(activeGamesService.getUserFromDataBase(playersName), board);

        return ships;
    }
}
