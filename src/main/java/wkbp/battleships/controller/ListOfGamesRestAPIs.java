package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.exception.NoAvailableGamesException;
import wkbp.battleships.service.GameService;

/**
 * @author Wiktor Rup
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
class ListOfGamesRestAPIs {


    @Autowired
    private GameService gameService;

    @GetMapping("/api/wkbp/get/gameslist")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> shipsRandomize() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(gameService.returnListOfGames());
        } catch (NoAvailableGamesException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
