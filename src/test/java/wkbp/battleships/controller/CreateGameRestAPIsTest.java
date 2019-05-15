package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class CreateGameRestAPIsTest {

    CreateGameRestAPIs createGameRestAPIs = new CreateGameRestAPIs();

    @Test
    public void testGameAccess() {
        assertEquals("game config",createGameRestAPIs.gameAccess());
    }

    @Test
    public void testRandomizeShips() throws JsonProcessingException {
        assertEquals(HttpStatus.OK,createGameRestAPIs.randomizeShips().getStatusCode());
    }
}