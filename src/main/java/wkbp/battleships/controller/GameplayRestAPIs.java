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
import wkbp.battleships.model.ShotOutcome;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
import wkbp.battleships.service.ActiveGamesService;
import wkbp.battleships.service.GameService;
import wkbp.battleships.service.ShipPlacementService;

import javax.naming.NoPermissionException;
import java.util.List;

/**
 * Contains endpoints used during actual game play between two players
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
class GameplayRestAPIs {

    @Autowired
    private ActiveGamesService activeGamesService;
    @Autowired
    private ShipPlacementService shipPlacementService;
    @Autowired
    private GameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @GetMapping("get/game/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> gameInit(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            boolean isPlayerGame = gameService.isPlayersGame(id, authentication.getName());
            message = objectMapper.writeValueAsString(isPlayerGame);
        } catch (NoPermissionException e) {
            logger.error("Player: " + authentication.getName() + " tried to join game with id: " + id + ". " + e.getMessage());
            message = e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/game/is_game_ready/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> isGameReady(@PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        boolean isGameReady = activeGamesService.isGameReady(id);
        message = objectMapper.writeValueAsString(isGameReady);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/game/fleet/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> userFleet(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        List<Field> ships = shipPlacementService.getUserFleet(id, authentication.getName());
        message = objectMapper.writeValueAsString(ships);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("post/game/shoot/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> gameShot(Authentication authentication, @RequestBody Field field, @PathVariable("id") long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            ShotOutcome shotOutcome = activeGamesService.makeAShoot(id, authentication.getName(), field);
            message = objectMapper.writeValueAsString(shotOutcome);
        } catch (NoPermissionException e) {
            message = e.getMessage();
            logger.error("Player: " + authentication.getName() + " tried to make a shot in game with id: " + id + ". " + e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("get/game/is_my_turn/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> isUserTurn(Authentication authentication, @PathVariable("id") long id) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        message = objectMapper.writeValueAsString(gameService.isPlayerTurn(id, authentication.getName()));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
