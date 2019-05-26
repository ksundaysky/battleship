package wkbp.battleships.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
import wkbp.battleships.service.EndOfGameService;

import javax.naming.NoPermissionException;

/**
 * Controller responsible for ending the game
 * whenever players quit game or finish one
 *
 * @author Wiktor Rup
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
class EndOfGameRestAPIs {

    @Autowired
    private EndOfGameService endOfGameService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);


    @GetMapping("get/game/end_of_game/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> endOfGame(Authentication authentication,
                                       @PathVariable("id") long id) {

        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            endOfGameService.endOfGameOnDestroy(id, authentication.getName());
            logger.info("class EndOfGameRestAPIs, method endOfGame(); ending game with id = " + id);
        } catch (NoPermissionException e) {
            message = e.getMessage();
            logger.error("Player: " + authentication.getName() + " tried remove game with id: " + id + ". " + message);
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Game removed successfully.", HttpStatus.OK);
    }
}
