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

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service with logic whenever request to generate
 * random fleet is made
 *
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

    /**
     * Sets up randomly fleet on board for user request
     *
     * @param gameId   in which request was made
     * @param username player who made request
     * @return List<Field> with ships placed on
     * @throws CantPlaceShipsException 0,2% times when randomizing algorithm can't properly place a ship
     */
    public List<Field> randomiseShipsForUser(Long gameId, String username) throws CantPlaceShipsException {
        Game game = activeGamesService.getGameById(gameId);
        Fleet fleet = FleetFactory.standardFleet();
        Board board = new BoardFactory(game.getGameConfig(), fleet).createBoard();
        ShipRandomiser shipRandomiser = new ShipRandomiser(board);
        List<Field> ships = shipRandomiser.randomizeShips();
        for (Field field : ships) {
            board.getFieldList().get(field.getId()).setStateOfField(StateOfField.OCCUPIED);
        }
        game.addUserAndHisBoard(activeGamesService.getUserFromDataBase(username), board);
        game.setGameplay(new Gameplay(board));
        logger.info("class ShipPlacement, method randomiseUserFleet(); returning: " + ships.toString());
        return ships;
    }

    /**
     * provides user fleet to display at game board
     *
     * @param gameId   id of the game
     * @param username player who made request
     * @return List<Field> which is containing all fields with fleet
     * @throws NoPermissionException when request was made by player outside of given game
     */
    public List<Field> getUserFleet(Long gameId, String username) throws NoPermissionException {
        User user = userRepository.findByUsername(username).get();
        Game game = activeGamesService.getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(user))
            throw new NoPermissionException("You have no power here!");
        else {
            game.addReadyPlayer();
            Board userBoard = game.getBoardByUser(user);

            return userBoard.getFieldList()
                    .stream()
                    .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                    .collect(Collectors.toList());
        }
    }
}
