package wkbp.battleship.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import wkbp.battleship.business.logic.ShipsRandomize;
import wkbp.battleship.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller class which provides endpoints used by client side at the time of battle.
 * @author Wiktor Rup
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BattleRestAPIs {
    @PostMapping("/api/test/game/shot")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> gameShot(@RequestBody Field field) throws JsonProcessingException {

        System.out.println(field);
        field.setStateOfField(StateOfField.OCCUPIED);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(field);

        System.out.println(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/api/test/game/ships")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> gameShipsRandomize() throws JsonProcessingException {
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(new Ship(4), new Ship(3), new Ship(2))));
        Board board = new Board(fieldList);
        ShipsRandomize shipsRandomize = new ShipsRandomize(board, fleet);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(shipsRandomize.randomizeShips());
        System.out.println(s);

        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
