package wkbp.battleships.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates Boards {@link Board}
 *
 * @author Wiktor Rup
 */
public class BoardFactory {

    private GameConfig gameConfig;

    public BoardFactory(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public Board createBoard() {
        List<Field> boardFileds = new ArrayList<>();
        for (int i = 0; i < Math.pow(gameConfig.getDimension(), 2); i++)
            boardFileds.add(new Field(i));


        return new Board(boardFileds);
    }
}
