package wkbp.battleships.dto;

import wkbp.battleships.model.GameConfig;
import wkbp.battleships.model.GameMode;

/**
 * @author krzysztof.niedzielski
 */
public class ConfigDTO {

    private String gameName;
    private int dimension;
    private boolean ownerStarts;
    private GameMode gameMode;

    public ConfigDTO(String gameName, int dimension, boolean ownerStarts, GameMode gameMode) {
        this.gameName = gameName;
        this.dimension = dimension;
        this.ownerStarts = ownerStarts;
        this.gameMode = gameMode;
    }

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

    public boolean ownerStarts() {
        return ownerStarts;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public boolean isOwnerStarts() {
        return ownerStarts;
    }

    public void setOwnerStarts(boolean ownerStarts) {
        this.ownerStarts = ownerStarts;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
