package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

/**
 * @author krzysztof.niedzielski
 */
public class CreateGameRestAPIsTest {

    private CreateGameRestAPIs createGameRestAPIs = new CreateGameRestAPIs();

//    @Test
//    public void testGameAccess() {
//        assertEquals("game config", createGameRestAPIs.gameAccess());
//    }

//    @Test(invocationCount = 10, successPercentage = 90)
//    public void testRandomizeShips() throws JsonProcessingException {
//        assertEquals(HttpStatus.OK, createGameRestAPIs.shipsPlacement().getStatusCode());
//    }
//
//    @Test
//    public void testResponseMessage() throws JsonProcessingException {
//        ResponseEntity responseEntity = createGameRestAPIs.shipsPlacement();
//        assertEquals(String.class, Objects.requireNonNull(responseEntity.getBody()).getClass());
//    }
}