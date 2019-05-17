package wkbp.battleships.model;

/**
 * Contains all necessary informations to initialize game {@link Game}
 *
 * @author Wiktor Rup
 */
public class GameConfig {

    private String gameName;
    private int dimension;
    private GameMode gameMode;
    private Fleet fleet;
    private Board board;
    private boolean ownerStarts;

    public int getDimension() {
        return dimension;
    }

    public String getGameName() {
        return gameName;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public GameConfig(String gameName, int dimension, GameMode gameMode, boolean ownerStarts) {
        this.gameName = gameName;
        this.dimension = dimension;
        this.gameMode = gameMode;
        this.ownerStarts = ownerStarts;
    }

    public boolean doesOwnerStart() {
        return ownerStarts;
    }
}
