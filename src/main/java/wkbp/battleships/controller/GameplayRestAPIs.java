package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.model.Field;
import wkbp.battleships.model.StateOfField;
import wkbp.battleships.service.ActiveGamesService;

import java.util.List;

/**
 * Contains endpoints used during actual game play between two players
 *
 * @author Patryk Kucharski
 * @author Wiktor Wrup
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
class GameplayRestAPIs {

    @Autowired
    private ActiveGamesService activeGamesService;

    @PostMapping("post/game/shoot/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> gameShot(Authentication authentication, @RequestBody Field field, @PathVariable("id") long id) throws JsonProcessingException {

        Field shotField = activeGamesService.makeAShoot(id, authentication.getName(), field);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(shotField);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/game/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> userFleet(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            List<Field> ships = activeGamesService.returnUserFleet(id, authentication.getName()); //todo no nie bardzo
            message = objectMapper.writeValueAsString(ships);
            System.out.println(message);
        } catch (CantPlaceShipsException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/game/is_my_turn/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> isUserTurn(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {

        System.out.println("JESTEM W IS MAJ ROLE BICZ");
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        message = objectMapper.writeValueAsString(activeGamesService.isPlayerTurn(id, authentication.getName()));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
