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
    boolean doesOwnerStart;

    public int getDimension() {
        return dimension;
    }

    public GameConfig(String gameName, int dimension, GameMode gameMode, boolean doesOwnerStart) {
        this.gameName = gameName;
        this.dimension = dimension;
        this.gameMode = gameMode;
        this.doesOwnerStart = doesOwnerStart;
    }
}
