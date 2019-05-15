package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wkbp.battleships.dao.repository.GameRepository;

/**
 * @author krzysztof.niedzielski
 */
@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;
}
