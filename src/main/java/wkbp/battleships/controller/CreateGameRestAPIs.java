package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.businesslogic.ShipsRandomiser;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.exception.CantPlaceShipsException;
import wkbp.battleships.model.Board;
import wkbp.battleships.model.Field;
import wkbp.battleships.model.Fleet;
import wkbp.battleships.model.Ship;
import wkbp.battleships.service.GameService;

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

    @Autowired
    private GameService gameService; //TODO do gameserwisu to zrobisz

    @PostMapping("post/game_config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> gameAccess(Authentication authentication, @RequestBody ConfigDTO configDTO) {


        long gameId = gameService.createGame(authentication.getName(), configDTO);

        return new ResponseEntity<>("1", HttpStatus.OK);
    }

    @GetMapping("get/ships_placement/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> shipsPlacement(@PathVariable("id") long id) {
        return new ResponseEntity<>(String.valueOf(id), HttpStatus.OK);


    }

    @GetMapping("get/ship_randomize/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> shipsRandomize(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            List<Field> ships = gameService.randomShips(id, authentication.getName());
            message = objectMapper.writeValueAsString(ships);
        } catch (CantPlaceShipsException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}