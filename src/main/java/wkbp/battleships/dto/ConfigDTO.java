package wkbp.battleships.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDTO {

    private String gameName;
    private int dimension;
    private boolean ownerStarts;
    private GameMode gameMode;

    public GameConfig assembly() {
        return new GameConfig(gameName, dimension, gameMode, ownerStarts);
    }
}
