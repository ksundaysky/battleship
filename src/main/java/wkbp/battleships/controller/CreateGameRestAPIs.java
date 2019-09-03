package wkbp.battleships.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
import wkbp.battleships.service.GameService;

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
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @PostMapping("post/game_config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> createGame(@RequestBody ConfigDTO configDTO) {

        long gameId = gameService.createGame(configDTO);
        logger.info("class CreateGameRestAPIs, method createGame(); game created, returning response with gameId: " + gameId);
        return new ResponseEntity<>(String.valueOf(gameId), HttpStatus.OK);
    }
}