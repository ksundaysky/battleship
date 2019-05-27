package wkbp.battleships.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Getter
public class Gameplay {

    private Board board;
    private BoardUpdater boardUpdater;
    private Move lastMove;
    private Map<Move, ShotOutcome> moves;
    private Map<User, Board> playersInGame;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    public Gameplay(Board board) {
        this.board = board;
        this.boardUpdater = new BoardUpdater(board);
        this.moves = new HashMap<>();
        this.playersInGame = new HashMap<>();
    }

    ShotOutcome update(Move move, Board board) {
        this.lastMove = move;
        this.board = board;
        boardUpdater.setRefereeBoard(board);
        boardUpdater.setCurrentBoard(board);
        logger.info("class GamePlay, method update(); move " + move.toString());
        ShotOutcome shotOutcome = boardUpdater.updateBoard(move);
        addMove(move, shotOutcome);
        return shotOutcome;
    }

    private void addMove(Move move, ShotOutcome shotOutcome) {
        logger.info("class GamePlay, method addMove(); adding key " + move.toString() + " and value " + shotOutcome.toString() + " to the Map<Move, ShotOutcome> moves: " + moves.toString());
        moves.put(move, shotOutcome);
    }
}
