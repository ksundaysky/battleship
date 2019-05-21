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

import javax.naming.NoPermissionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
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

    public ShotOutcome makeAShoot(Long gameId, String playersName, Field field) throws NoPermissionException { // TODO: 17.05.19 ta metoda również nie tutaj końcowo
        User player = getUserFromDataBase(playersName);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("You have no permission to join this game!");
        }
        return game.moveHasBeenMade(new Move(gameId, player, field));
    }

    public boolean isPlayerTurn(long id, String playersName) {
        User player = getUserFromDataBase(playersName);
        Game game = games.get(id);
        return player.equals(game.getCurrentPlayer());
    }

    public List<Field> getUserFleet(Long id, String username) { // TODO: 17.05.19 ta metoda nie tutaj xd
        User user = getUserFromDataBase(username);
        Game game = getGameById(id);
        Board userBoard = game.getBoardByUser(user);

        return userBoard.getFieldList()
                .stream()
                .filter(field -> field.getStateOfField().equals(StateOfField.OCCUPIED))
                .collect(Collectors.toList());
    }

    public Map<Long, Game> getListOfGames() {
        if (games.isEmpty()) {
            throw new NoAvailableGamesException("No available games to display!");
        }
        return games;
    }


    public boolean isPlayersGame(long id, String user) throws NoPermissionException {
        User player = getUserFromDataBase(user);
        Game game = games.get(id);
        if(!game.getPlayersInGame().containsKey(player))
            throw new NoPermissionException("You have no permission to join this game!");
        else
            return true;
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
        return game.containsPlayer(player) || game.getNumberOfPlayers() < 2;
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
        if (game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 0) {
            game.setCurrentPlayer(getUserFromDataBase(playersName));
        } else if (!game.getGameConfig().isOwnerStarts() && game.getNumberOfPlayers() == 1) {
            game.setGameState(GameState.IN_PROGRESS);
            game.setCurrentPlayer(getUserFromDataBase(playersName));
        }
    }

    private void setCurrentPlayer(long id, User currentPlayer) {
        Game game = games.get(id);
        game.setCurrentPlayer(currentPlayer);
    }

    public boolean isGameReady(long id) {
        return games.get(id).getHowManyPlayersAreReady() == 2;
    }

    public void addReadyPlayer(long id){
        games.get(id).addReadyPlayer();
    }
}
