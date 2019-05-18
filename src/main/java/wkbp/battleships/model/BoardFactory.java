package wkbp.battleships.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates Boards {@link Board}
 *
 * @author Wiktor Rup
 */
@AllArgsConstructor
public class BoardFactory {

    private GameConfig gameConfig;

    public Board createBoard() {
        List<Field> boardFileds = new ArrayList<>();
        for (int i = 0; i < Math.pow(gameConfig.getDimension(), 2); i++)
            boardFileds.add(new Field(i));


        return new Board(boardFileds);
    }
}
