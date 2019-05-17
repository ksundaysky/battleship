package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.exception.GameIsFullException;
import wkbp.battleships.model.Field;
import wkbp.battleships.service.GameService;

import java.util.List;

/**
 * Contains endpoints used during creation of the game,
 * setting game configuration and placing ships
 *
 * @author Wiktor Wrup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
public class CreateGameRestAPIs {

    @Autowired
    private GameService gameService;

    @PostMapping("post/game_config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> gameAccess(@RequestBody ConfigDTO configDTO) {

        long gameId = gameService.createGame(configDTO);

        return new ResponseEntity<>(String.valueOf(gameId), HttpStatus.OK);
    }

    @GetMapping("get/ships_placement/{id}") // TODO: 17.05.19 ship placement controller
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> shipsPlacement(Authentication authentication, @PathVariable("id") long id) {
        String message;
        try {
            message = gameService.joinTheGame(id, authentication.getName());
        } catch (GameIsFullException e) {
            message = e.getMessage();
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/ship_randomize/{id}")// TODO: 17.05.19 ship placement controller
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> shipsRandomize(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            List<Field> ships = gameService.randomiseShips(id, authentication.getName());
            message = objectMapper.writeValueAsString(ships);
        } catch (CantPlaceShipsException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}