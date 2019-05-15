package wkbp.battleships.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wkbp.battleships.dao.repository.entity.GameEntity;

/**
 * @author krzysztof.niedzielski
 */
@Repository
public interface GameRepository extends JpaRepository<GameEntity,Long> {

}
