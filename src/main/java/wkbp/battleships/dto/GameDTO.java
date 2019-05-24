package wkbp.battleships.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wkbp.battleships.model.GameMode;
import wkbp.battleships.model.GameState;

/**
 * @author Wiktor Rup
 */
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    long gameId;
    GameState gameState;
    String gameName;
    int dimension;
    GameMode gameMode;

}
