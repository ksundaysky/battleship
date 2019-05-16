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
        gameRepository.save(gameEntity);
        userInGameRepository.save(userInGameEntity);

        games.put(1L, game);

        return 1;

    }


    public List<Field> randomShips(Long id, String name) throws CantPlaceShipsException {
        User owner = userRepository.findByUsername(name).get();
        Game game = games.get(id);
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new BoardFactory(game.getConfig()).createBoard();
        System.out.println(board.toString());
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        List<Integer> listOfIds = shipsRandomiser.randomizeShips().stream().map(Field::getId).collect(Collectors.toList());
        for (Integer fieldId : listOfIds) {
            board.getFieldList().get(fieldId).setStateOfField(StateOfField.OCCUPIED);
        }
        game.addPlayerToTheGame(owner, board);

        return shipsRandomiser.randomizeShips();
    }


}
