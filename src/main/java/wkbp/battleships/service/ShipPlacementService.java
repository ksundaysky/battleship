package wkbp.battleships.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.businesslogic.ShipRandomiser;
import wkbp.battleships.controller.CantPlaceShipsException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.model.*;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Service
public class ShipPlacementService {

    @Autowired
    private ActiveGamesService activeGamesService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    public List<Field> randomiseShipsForUser(Long gameId, String playersName) throws CantPlaceShipsException {
        Game game = activeGamesService.getGameById(gameId);
        Fleet fleet = FleetFactory.standardFleet();
        Board board = new BoardFactory(game.getGameConfig(), fleet).createBoard();
        ShipRandomiser shipRandomiser = new ShipRandomiser(board);
        List<Field> ships = shipRandomiser.randomizeShips();
        for (Field field : ships) {
            board.getFieldList().get(field.getId()).setStateOfField(StateOfField.OCCUPIED);
        }
        game.addUserAndHisBoard(activeGamesService.getUserFromDataBase(playersName), board);
        game.setGameplay(new Gameplay(board));
        logger.info("class ShipPlacement, method randomiseUserFleet(); returning: " + ships.toString());
        return ships;
    }

    public List<Field> getUserFleet(Long id, String username) {
        User user = userRepository.findByUsername(username).get();
        Game game = activeGamesService.getGameById(id);
        game.addReadyPlayer();
        Board userBoard = game.getBoardByUser(user);

        return userBoard.getFieldList()
                .stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .collect(Collectors.toList());
    }
}
