package wkbp.battleships.model;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
public class Gameplay {

    private Board board;
    private BoardUpdater boardUpdater;
    Move lastMove;
    List<Move> moves;
    private Map<User, Board> playersInGame;

    public Gameplay(Board board) {
        this.board = board;
        this.boardUpdater = new BoardUpdater(board);
    }

    public Board getBoard() {
        return board;
    }

    public BoardUpdater getBoardUpdater() {
        return boardUpdater;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Map<User, Board> getPlayersInGame() {
        return playersInGame;
    }

    public ShotOutcome update(Move move) {
        addMove(move);
        return boardUpdater.updateBoard(move);
    }

    private void addMove (Move move){
        moves.add(move);
    }
}
