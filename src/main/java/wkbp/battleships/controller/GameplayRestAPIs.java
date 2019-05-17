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
class GameplayRestAPIs {

    @Autowired
    private ActiveGamesService activeGamesService;

    @PostMapping("/api/wkbp/post/game/shoot")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> gameShot(@RequestBody Field field) throws JsonProcessingException {

        field.setStateOfField(StateOfField.OCCUPIED);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(field);

        return new ResponseEntity<>(s, HttpStatus.OK);
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
}
