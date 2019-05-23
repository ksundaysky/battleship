package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
import wkbp.battleships.service.ActiveGamesService;

/**
 * Controller responsible for getting list of all active games
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */

@RestController
@RequestMapping("/api/wkbp/")
@CrossOrigin(origins = "*", maxAge = 3600)
class ListOfGamesRestAPIs {

    @Autowired
    private ActiveGamesService activeGamesService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @GetMapping("get/gameslist")
    public ResponseEntity<?> gamesList() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(activeGamesService.getListOfGames());
            logger.info("class ListOfGamesRestAPIs, method gamesList(); sending Map<Long, Game> games as a response " + message);
        } catch (NoAvailableGamesException e) {
            message = e.getMessage();
            logger.error("class ListOfGamesRestAPIs, method gamesList(); failed to retreive list of games. " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
