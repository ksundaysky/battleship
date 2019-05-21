package wkbp.battleships.model;

import lombok.Getter;

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
        addMove(move);
        return boardUpdater.updateBoard(move);
    }

    private void addMove(Move move) {
        moves.add(move);
    }
}
