package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wkbp.battleships.model.Field;
import wkbp.battleships.model.StateOfField;

/**
 * Contains endpoints used during actual game play between two players
 *
 * @author Patryk Kucharski
 * @author Wiktor Wrup
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class GameplayRestAPIs {

    @PostMapping("/api/wkbp/post/game/shoot")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> gameShot(@RequestBody Field field) throws JsonProcessingException {

        // TODO: 14.05.19 logika nie tu
        field.setStateOfField(StateOfField.OCCUPIED);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(field);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
