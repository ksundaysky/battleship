package wkbp.battleships.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.model.Game;
import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;
import wkbp.battleships.service.GameService;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

/**
 * @author krzysztof.niedzielski
 */
public class CreateGameRestAPIsTest {
}