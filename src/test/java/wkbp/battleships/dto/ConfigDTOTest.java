package wkbp.battleships.dto;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;

/**
 * @author Wiktor Rup
 */
public class ConfigDTOTest {

    private ConfigDTO configDTO = new ConfigDTO("game1", 10, true, GameMode.STANDARD);

    @BeforeMethod


    @Test
    public void testAssembly() {

        GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
        assert gameConfig.equals(configDTO.assembly()) : "Returned GameConfig object is not proper";

    }
}