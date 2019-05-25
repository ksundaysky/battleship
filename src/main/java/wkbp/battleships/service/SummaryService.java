package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.SummaryRepository;
import wkbp.battleships.dao.repository.UserInGameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.dto.SummaryDTO;
import wkbp.battleships.model.*;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service that provides summary of the game logic
 *
 * @author Wiktor Rup
 */
@Service
public class SummaryService {

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

    /**
     * Creates and returns list of summaries {@link Summary} of given game
     *
     * @param gameId id of the given game
     * @param username username of player to get summary
     * @return list of summaries to display
     * @throws NoPermissionException when given game doesn't contain player
     */
    public List<SummaryDTO> getSummaryOfGame(long gameId, String username) throws NoPermissionException {
        List<SummaryDTO> summaries = new ArrayList<>();
        User player = getUserFromDataBase(username);
        Game game = getGameById(gameId);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("No permissions for such action!");
        } else {
            for (Map.Entry<User, Board> currentPlayer : game.getPlayersInGame().entrySet()) {
                SummaryDTO summary = createSummaryDTO(currentPlayer.getKey(), game);
                summaries.add(summary);
                setGameStateToFinished(game);
            }
        }
        return summaries;
    }

    /**
     * Creates summary of the game for given player
     *
     * @param player owner of summary
     * @param game which summary is to be returned
     * @return summaryDTO object with statistics {@link SummaryDTO}
     */
    private SummaryDTO createSummaryDTO(User player, Game game) {
        int shots = 0;
        int hits = 0;
        boolean isWinner = false;
        for (Map.Entry<Move, ShotOutcome> move : game.getGameplay().getMoves().entrySet()) {
            if (player.equals(move.getKey().getPlayer())) {
                shots++;
                if (move.getValue().isPlayerTurn()) {
                    hits++;
                }
                if (move.getValue().isPlayerWon()) {
                    isWinner = true;
                }
            }
        }
        int ratio = getHitRatio(shots, hits);
        GameEntity gameEntity = getGameFromDataBase(game.getId());
        UserInGameEntity userInGameEntity = getUserInGameEntityFromDataBase(player, gameEntity);
        Summary summary = new Summary(game.getGameConfig().getGameName(),
                player, userInGameEntity, isWinner, shots, hits, ratio);
        summaryRepository.save(summary);
        return compactToDTO(summary);
    }

    private int getHitRatio(int shots, float hits) {
        int ratio;
        ratio = (int) ((hits / shots) * 100);
        return ratio;
    }

    private Game getGameById(long gameId) {
        return activeGamesService.getGameById(gameId);
    }

    private User getUserFromDataBase(String username) {
        return userRepository.findByUsername(username).get();
    }

    private GameEntity getGameFromDataBase(long gameId) {
        return gameRepository.getOne(gameId);
    }

    private UserInGameEntity getUserInGameEntityFromDataBase(User user, GameEntity gameEntity) {
        return userInGameRepository.findUserInGameEntityByIds(user, gameEntity);
    }

    private SummaryDTO compactToDTO(Summary summary) {
        return new SummaryDTO(summary.getGameName(), summary.getUser().getName(),
                summary.isWinner(), summary.getShots(), summary.getHits(), summary.getRatio());
    }

    private void setGameStateToFinished(Game game) {
        game.setGameState(GameState.FINISHED);
    }
}
