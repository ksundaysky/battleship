package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.controller.NoAvailableGamesException;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Patryk Kucharski
 */
@Service
public class ActiveGamesService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInGameRepository userInGameRepository;
    @Autowired
    private GameRepository gameRepository;

    private Map<Long, Game> games = new HashMap<>();

    public ShotOutcome makeAShoot(Long gameId, String playersName, Field field) { // TODO: 17.05.19 ta metoda również nie tutaj końcowo
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        return game.moveHasBeenMade(new Move(gameId, player, field));
    }

    public boolean isPlayerTurn(long id, String playersName) {
        User player = getUserFromDataBase(playersName);
        Game game = games.get(id);
        return player.equals(game.getCurrentPlayer());
    }

    public List<Field> returnUserFleet(Long id, String username) { // TODO: 17.05.19 ta metoda nie tutaj xd
        User user = getUserFromDataBase(username);
        Game game = getGameById(id);
        game.setGameState(GameState.IN_PROGRESS);
        Board userBoard = game.getBoardByUser(user);

        return userBoard.getFieldList()
                .stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .collect(Collectors.toList());
    }

    public Map<Long, Game> returnListOfGames() {
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display!");
        }
        return games;
    }

    void addPlayerToTheGame(Long gameId, String playersName, Game game) {

        User owner = getUserFromDataBase(playersName);
        GameEntity gameEntity = gameRepository.getOne(gameId);
        UserInGameEntity userInGameEntity = new UserInGameEntity(owner, gameEntity);
        userInGameRepository.save(userInGameEntity);
        game.addPlayerToTheGame(owner);
    }

    boolean checkIfUserCanJoinTheGame(long gameId, String username) {
        User player = getUserFromDataBase(username);
        Game game = getGameById(gameId);
        if (game.containsPlayer(player))
            return true;
        else return game.getNumberOfPlayers() < 2;
    }

    User getUserFromDataBase(String name) {
        return userRepository.findByUsername(name).get();
    }

    void addGameToActiveGames(long id, Game game) {
        games.put(id, game);
    }

    Game getGameById(Long id) {
        return games.get(id);
    }

    void setStartingPlayer(Game game, String playersName) {
        if (game.getConfig().doesOwnerStart() && game.getNumberOfPlayers() == 0) {
            game.setCurrentPlayer(getUserFromDataBase(playersName));
            System.out.println("USTYWAIELEM GRACZA STARTUJACEGO NA " + game.getCurrentPlayer());
        } else if (!game.getConfig().doesOwnerStart() && game.getNumberOfPlayers() == 1) {
            game.setCurrentPlayer(getUserFromDataBase(playersName));
            System.out.println("ELSE USTYWAIELEM GRACZA STARTUJACEGO NA " + game.getCurrentPlayer());

        }
    }

    private void setCurrentPlayer(long id, User currentPlayer) {
        Game game = games.get(id);
        game.setCurrentPlayer(currentPlayer);
    }
}
