package wkbp.battleships.model;

import lombok.Getter;

/**
 * Contains all necessary informations to initialize game {@link Game}
 *
 * @author Wiktor Rup
 */
@Getter
public class GameConfig {

    private String gameName;
    private int dimension;
    private GameMode gameMode;
    private Fleet fleet;
    private Board board;
    private boolean ownerStarts;

    public GameConfig(String gameName, int dimension, GameMode gameMode, boolean ownerStarts) {
        this.gameName = gameName;
        this.dimension = dimension;
        this.gameMode = gameMode;
        this.ownerStarts = ownerStarts;
    }
}
