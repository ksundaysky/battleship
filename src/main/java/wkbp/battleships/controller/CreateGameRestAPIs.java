package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.businesslogic.ShipsRandomiser;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.model.*;
import wkbp.battleships.repository.UserRepository;

import javax.validation.Valid;
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
// TODO: 13.05.19 Rozbicie na osobne klasy : konfig, rozstawianie statków, rozgrywka
public class CreateGameRestAPIs {

    @Autowired
    UserRepository userRepository; //TODO do gameserwisu to zrobisz

    @PostMapping("/api/wkbp/config")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> gameAccess(Authentication authentication, @RequestBody ConfigDTO configDTO) {

        System.out.println(configDTO);

        System.out.println(authentication.getName());

        User owner = userRepository.findByUsername(authentication.getName()).get();
//        Game game = new Game(configDTO, owner );

        return new ResponseEntity<>("1",HttpStatus.OK);
    }

    @GetMapping("/api/wkbp/get/ships_placement")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> randomizeShips() throws JsonProcessingException {

        // TODO: 14.05.19 logika nie tu
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fieldList.add(new Field(i));
        }
        Fleet fleet = new Fleet(new ArrayList<>(Arrays.asList(
                new Ship(4),
                new Ship(3), new Ship(3),
                new Ship(2), new Ship(2), new Ship(2),
                new Ship(1), new Ship(1), new Ship(1), new Ship(1))));

        Board board = new Board(fieldList);
        ShipsRandomiser shipsRandomiser = new ShipsRandomiser(board, fleet);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(shipsRandomiser.randomizeShips());
        System.out.println(s);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}