package wkbp.battleships.dto;

/**
 * @author krzysztof.niedzielski
 */
public class ConfigDTO {

    private String gameName;
    private int dimension;
    private boolean doesOwnerStart;
    private GameMode gameMode;

    public ConfigDTO(String gameName, int dimension, boolean doesOwnerStart, GameMode gameMode) {
        this.gameName = gameName;
        this.dimension = dimension;
        this.doesOwnerStart = doesOwnerStart;
        this.gameMode = gameMode;
    }

    @Override
    public String toString() {
        return "ConfigDTO{" +
                "gameName='" + gameName + '\'' +
                ", dimension=" + dimension +
                ", doesOwnerStart=" + doesOwnerStart +
                ", gameMode=" + gameMode +
                '}';
    }
}
