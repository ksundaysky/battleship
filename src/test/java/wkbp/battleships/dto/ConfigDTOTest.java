package wkbp.battleships.dto;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Wiktor Rup
 */
public class ConfigDTOTest {

    private ConfigDTO configDTO;

    @BeforeMethod
    public void initializeConfigDTO() {
        configDTO = new ConfigDTO("game1", 10, true, GameMode.STANDARD);
    }


    @Test
    public void testAssembly() {

        GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
        assertEquals(gameConfig, configDTO.assembly());

    }

    @Test
    public void testGetGameName() {
        String expectedName = "game1";
        String actualName = configDTO.getGameName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetDimension() {
        int expectedDimension = 10;
        int actualDimension = configDTO.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    public void testIsOwnerStarts() {

        boolean expectedIsOwnerStarts = true;
        boolean actualIsOwnerStarts = configDTO.isOwnerStarts();

        assertEquals(expectedIsOwnerStarts, actualIsOwnerStarts);
    }

    @Test
    public void testGetGameMode() {

        GameMode expectedGameMode = GameMode.STANDARD;
        GameMode actualGameMode = configDTO.getGameMode();

        assertEquals(expectedGameMode, actualGameMode);
    }

    @Test
    public void testSetGameName() {
        String expectedName = "game2";
        configDTO.setGameName(expectedName);

        String actualName = configDTO.getGameName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetDimension() {
        int expectedDimension = 125;
        configDTO.setDimension(expectedDimension);
        int actualDimension = configDTO.getDimension();

        assertEquals(expectedDimension, actualDimension);
    }

    @Test
    public void testSetOwnerStarts() {
        boolean expectedIsOwnerStarts = false;
        configDTO.setOwnerStarts(expectedIsOwnerStarts);
        boolean actualIsOwnerStarts = configDTO.isOwnerStarts();

        assertEquals(expectedIsOwnerStarts, actualIsOwnerStarts);
    }

    @Test
    public void testSetGameMode() {

        GameMode expectedGameMode = GameMode.STANDARD;
        configDTO.setGameMode(expectedGameMode);
        GameMode actualGameMode = configDTO.getGameMode();

        assertEquals(expectedGameMode, actualGameMode);
    }
}