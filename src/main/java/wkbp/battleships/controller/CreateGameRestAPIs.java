package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.businesslogic.ShipsRandomiser;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.model.Board;
import wkbp.battleships.model.Field;
import wkbp.battleships.model.Fleet;
import wkbp.battleships.model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains endpoints used during creation of the game,
 * setting game configuration and placing ships
 *
 * @author Wiktor Wrup
 * @author Patryk Kucharski
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
// TODO: 13.05.19 Rozbicie na osobne klasy : konfig, rozstawianie statków, rozgrywka
public class CreateGameRestAPIs {

    @GetMapping("get/game_config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    String gameAccess() {
        // TODO: 14.05.19  implementacja
        return "game config";
    }

    @GetMapping("get/ships_placement")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> randomizeShips() throws JsonProcessingException {

        // TODO: 14.05.19 logika nie tu
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new Board(fieldList);
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(shipsRandomiser.randomizeShips());
        }
        catch (CantPlaceShipsException e){
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}