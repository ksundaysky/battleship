package wkbp.battleships.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates Boards {@link Board}
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@AllArgsConstructor
public class BoardFactory {

    private GameConfig gameConfig;

    public Board createBoard() {
        List<Field> boardFields = new ArrayList<>();
        for (int i = 0; i < Math.pow(gameConfig.getDimension(), 2); i++)
            boardFields.add(new Field(i));

        return new Board(boardFields);
    }
}
