package wkbp.battleships.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;

/**
 * @author krzysztof.niedzielski
 */
@Getter
@Setter
@AllArgsConstructor
public class ConfigDTO {

    private String gameName;
    private int dimension;
    private boolean ownerStarts;
    private GameMode gameMode;

    @Override
    public String toString() {
        return "ConfigDTO{" +
                "gameName='" + gameName + '\'' +
                ", dimension=" + dimension +
                ", ownerStarts=" + ownerStarts +
                ", gameMode=" + gameMode +
                '}';
    }

    public GameConfig assembly() {
        return new GameConfig(gameName, dimension, gameMode, ownerStarts);
    }
}
