package wkbp.battleships.model;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<Move> moves;
    private Map<User, Board> playersInGame;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    public Gameplay(Board board) {
        this.board = board;
        this.boardUpdater = new BoardUpdater(board);
        this.moves = new ArrayList<>();
        this.playersInGame = new HashMap<>();
    }

    ShotOutcome update(Move move, Board board) {
        this.lastMove = move;
        this.board = board;
        boardUpdater.setRefereeBoard(board);
        boardUpdater.setCurrentBoard(board);
        addMove(move);
        logger.info("class GamePlay, method update(); move " + move.toString());
        return boardUpdater.updateBoard(move);
    }

    private void addMove(Move move) {
        logger.info("class GamePlay, method addMove(); adding " + move.toString() + " to the List<Move> moves: " + moves.toString());
        moves.add(move);
    }
}
