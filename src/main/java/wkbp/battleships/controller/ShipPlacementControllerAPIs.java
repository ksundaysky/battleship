package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.model.Field;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
import wkbp.battleships.service.GameService;
import wkbp.battleships.service.ShipPlacementService;

import java.util.List;

/**
 * Controller responsible for requests from ship placement view on client side
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
public class ShipPlacementControllerAPIs {

    @Autowired
    private GameService gameService;
    @Autowired
    private ShipPlacementService shipPlacementService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @GetMapping("get/ships_placement/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> joinTheGame(Authentication authentication, @PathVariable("id") long id) {
        String message;
        try {
            message = gameService.tryToJoinTheGame(id, authentication.getName());
            logger.info("trying to join the game with id: " + id + "result: " + message);
        } catch (GameIsFullException e) {
            message = e.getMessage();
            logger.error("attempt to join the game failed, reason: " + message);
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/ship_randomize/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> randomizeFleetOnBoard(Authentication authentication,
                                                   @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            List<Field> ships = shipPlacementService.randomiseShipsForUser(id, authentication.getName());
            message = objectMapper.writeValueAsString(ships);
            logger.info("sending response: " + message);
        } catch (CantPlaceShipsException e) {
            message = e.getMessage();
            logger.error("attempt to place ships on board failed, reason: " + message);
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
