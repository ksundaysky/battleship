package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.businesslogic.ShipsRandomiser;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.exception.NoAvailableGamesException;
import wkbp.battleships.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author krzysztof.niedzielski
 */
@Service
public class GameService {

    @Autowired
    private
    GameRepository gameRepository;
    @Autowired
    private
    UserRepository userRepository;
    @Autowired
    private UserInGameRepository userInGameRepository;

    private Map<Long, Game> games = new HashMap<>();


    public long createGame(String name, ConfigDTO configDTO) {

        User owner = userRepository.findByUsername(name).get();

        Game game = new Game(configDTO.assembly());
        System.out.println(configDTO);
        GameEntity gameEntity = new GameEntity(game.getGameState());

        UserInGameEntity userInGameEntity = new UserInGameEntity(owner, gameEntity);

        userRepository.save(owner);
        gameEntity = gameRepository.save(gameEntity);
        userInGameRepository.save(userInGameEntity);

        games.put(gameEntity.getId(), game);

        return gameEntity.getId();

    }


    public List<Field> randomShips(Long id, String username) throws CantPlaceShipsException {
        User owner = userRepository.findByUsername(username).get();
        Game game = games.get(id);
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new BoardFactory(game.getConfig()).createBoard();
        System.out.println(board.toString());
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        List<Field> ships = shipsRandomiser.randomizeShips();


        for (Field field : ships) {
            board.getFieldList().get(field.getId()).setStateOfField(StateOfField.OCCUPIED);
        }
        game.addPlayerToTheGame(owner, board);

        return ships;
    }

    public List<Field> returnUserFleet(Long id, String username) {
        System.out.println("Mapa gier: " + games);
        System.out.println(id + " " + username);
        User user = userRepository.findByUsername(username).get();
        Game game = games.get(id);
        game.setGameState(GameState.IN_PROGRESS);
        System.out.println("GRA :" + game);
        Board userBoard = game.getBoardByUser(user);
        System.out.println("USER BOARD: " + userBoard);

        List<Field> collect = userBoard.getFieldList()
                .stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    public Map<Long, Game> returnListOfGames(){
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display!");
        }
        return games;
    }
}
