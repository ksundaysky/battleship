package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.SummaryRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.model.*;

import javax.naming.NoPermissionException;
import java.util.Map;

/**
 * @author Wiktor Rup
 */

@Service
public class EndOfGameService {

    @Autowired
    private UserInGameRepository userInGameRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private ActiveGamesService activeGamesService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SummaryRepository summaryRepository;


    public void endOfGameOnDestroy(long gameId, String user) throws NoPermissionException {
        User player = getUserFromDataBase(user);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("No permissions for such action!");
        } else if (game.getGameState().equals(GameState.FINISHED)) {
            System.out.println("jestem w elseifie");
            activeGamesService.removeGameById(gameId);
        }
        else {
            changeStateOfGameInDataBase(gameId);
            setWinnerOfTheGame(game, player);
        }
    }

    private Game getGameById(long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String username) {
        return userRepository.findByUsername(username).get();
    }

    private void changeStateOfGameInDataBase(long gameId) {
        GameEntity gameEntity = gameRepository.getOne(gameId);
        gameEntity.setGameState(GameState.ABORTED);
        gameRepository.save(gameEntity);
    }

    private void setWinnerOfTheGame(Game game, User player) {
        for (Map.Entry<Move, ShotOutcome> move : game.getGameplay().getMoves().entrySet()) {
            if (!player.equals(move.getKey().getPlayer())) {
                ShotOutcome shotOutcome = move.getValue();
                shotOutcome.setPlayerWon(true);
                move.setValue(shotOutcome);
                game.getGameQueues().get(move.getKey().getPlayer()).add(new ShotOutcome(false, null, null, true, "You won the game."));
            }
        }
    }


}
