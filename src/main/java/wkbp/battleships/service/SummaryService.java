package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.SummaryRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dto.SummaryDTO;
import wkbp.battleships.model.*;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wiktor Rup
 */
@Service
public class SummaryService {


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

    public List<SummaryDTO> getSummaryOfGame(long id, String user) throws NoPermissionException {
        List<SummaryDTO> summaries = new ArrayList<>();
        User player = getUserFromDataBase(user);
        Game game = getGameById(id);
        if (!game.getPlayersInGame().containsKey(player)) {
            throw new NoPermissionException("No permissions for such action!");
        } else {
            for (Map.Entry<User, Board> currentPlayer : game.getPlayersInGame().entrySet()) {
                SummaryDTO summary = createSummaryDTO(currentPlayer.getKey(), game);
                summaries.add(summary);
            }
        }
        return summaries;
    }

    private SummaryDTO createSummaryDTO(User player, Game game) {
        int shots = 0;
        int hits = 0;
        boolean isWinner = false;
        int ratio = 0;
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
        ratio = (int) (((float) hits / shots) * 100);
        GameEntity gameEntity = getGameFromDataBase(game.getId());
        Summary summary = new Summary(game.getGameConfig().getGameName(), player, gameEntity, isWinner, shots, hits, ratio);
        summaryRepository.save(summary);
        return compactToDTO(summary);
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

    private SummaryDTO compactToDTO(Summary summary) {
        return new SummaryDTO(summary.getGameName(), summary.getUser().getName(), summary.isWinner(), summary.getShots(), summary.getHits(), summary.getRatio());
    }
}
