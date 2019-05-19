package wkbp.battleships.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
public class GameTest {

    private GameConfig gameConfig = new GameConfig("game1", 10, GameMode.STANDARD, true);
    private Game game;

    @BeforeMethod
    public void initializeNewGame() {
        game = new Game(gameConfig);
    }

    @Test
    public void testAddPlayerToTheGame() {
        User user = new User();
        user.setName("user1");
        game.addPlayerToTheGame(user);

        assert game.getPlayersInGame().containsKey(user);
    }

    @Test
    public void testAddUserAndHisBoard() {
        User user = new User();
        user.setName("user2");
        Board board = new BoardFactory(gameConfig).createBoard();
        board.getField(12).setStateOfField(StateOfField.OCCUPIED);

        game.addUserAndHisBoard(user, board);

        assert board.equals(game.getBoardByUser(user));
    }

    @Test
    public void testGetNumberOfPlayers() {
        User player1 = new User();
        player1.setUsername("player1");
        User player2 = new User();
        player2.setUsername("player2");

        game.addPlayerToTheGame(player1);
        game.addPlayerToTheGame(player2);

        System.out.println(game.getNumberOfPlayers());

        assert game.getNumberOfPlayers() == 2;
    }

    @Test
    public void testContainsPlayer() {
        User player1 = new User();
        player1.setUsername("player1");
        game.addPlayerToTheGame(player1);

        assert game.containsPlayer(player1);
    }

    @Test
    public void testGetId() {
        game.setId(1);
        assert game.getId() == 1;
    }

    @Test
    public void testGetGameplay() {
        Gameplay gameplay = new Gameplay(new BoardFactory(gameConfig).createBoard());
        game.setGameplay(gameplay);

        assert gameplay.equals(game.getGameplay());
    }

    @Test
    public void testGetPlayersInGame() {
        Map<User, Board> currentPlayers = new HashMap<>();
        currentPlayers.put(new User(), new BoardFactory(gameConfig).createBoard());
        game.setPlayersInGame(currentPlayers);
        assert game.getPlayersInGame().equals(currentPlayers);

    }

    @Test
    public void testGetGameConfig() {
        assert game.getGameConfig().equals(gameConfig);
    }
}