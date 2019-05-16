package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dto.ConfigDTO;
import wkbp.battleships.model.Game;
import wkbp.battleships.model.User;

/**
 * @author krzysztof.niedzielski
 */
@Service
public class GameService {

    @Autowired
    private
    GameRepository gameRepository;
    @Autowired
    private
    UserRepository userRepository;


    public long createGame(String name, ConfigDTO configDTO) {

        User owner = userRepository.findByUsername(name).get();

        Game game = new Game(owner, configDTO.assembly());
        GameEntity gameEntity = new GameEntity(owner, game.getGameState());

        gameRepository.save(gameEntity);

        return gameEntity.getId();

    }


}
