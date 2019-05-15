package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.businesslogic.ShipsRandomiser;
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
// TODO: 13.05.19 Rozbicie na osobne klasy : konfig, rozstawianie statków, rozgrywka
public class CreateGameRestAPIs {

    @GetMapping("/api/wkbp/get/game_config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String gameAccess() {
        // TODO: 14.05.19  implement
        return "game config";
    }

    @GetMapping("/api/wkbp/get/ships_placement")
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
        String s = objectMapper.writeValueAsString(shipsRandomiser.randomizeShips());
        System.out.println(s);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}