package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.service.ActiveGamesService;

/**
 * Controller responsible for getting list of all active games
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 */

@RestController
@RequestMapping("/api/wkbp/")
@CrossOrigin(origins = "*", maxAge = 3600)
class ListOfGamesRestAPIs {

    @Autowired
    private ActiveGamesService activeGamesService;

    @GetMapping("get/gameslist")
    public ResponseEntity<?> shipsRandomize() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(activeGamesService.returnListOfGames());
        } catch (NoAvailableGamesException e) {
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
