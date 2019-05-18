package wkbp.battleships.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
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

    public ShotOutcome update(Move move, Board board) {
        this.lastMove = move;
        this.board = board;
        boardUpdater.setRefereeBoard(board);
        addMove(move);
        return boardUpdater.updateBoard(move, board);
    }

    private void addMove (Move move){
        moves.add(move);
    }
}
