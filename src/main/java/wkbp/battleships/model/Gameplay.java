package wkbp.battleships.model;

import java.util.List;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
public class Gameplay {

    private Board board;
    BoardUpdater boardUpdater;
    GameReferee gameReferee;
    Move lastMove;
    List<Move> moves;
    Map<String, User> users;
}
