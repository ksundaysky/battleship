package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.businesslogic.ShipRandomiser;
import wkbp.battleships.controller.CantPlaceShipsException;
import wkbp.battleships.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Service
public class ShipsRandomiseService {

    @Autowired
    private ActiveGamesService activeGamesService;

    public List<Field> randomiseShipsForUser(Long gameId, String playersName) throws CantPlaceShipsException {

        Game game = activeGamesService.getGameById(gameId);
        // TODO: 17.05.19 zamiana na FleetFactory
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new BoardFactory(game.getGameConfig()).createBoard();
        ShipRandomiser shipRandomiser = new ShipRandomiser(board, fleet);
        List<Field> ships = shipRandomiser.randomizeShips();
        for (Field field : ships) {
            board.getFieldList().get(field.getId()).setStateOfField(StateOfField.OCCUPIED);
        }

        game.addUserAndHisBoard(activeGamesService.getUserFromDataBase(playersName), board);
        game.setGameplay(new Gameplay(board));

        return ships;
    }
}
