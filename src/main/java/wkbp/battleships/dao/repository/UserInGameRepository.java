package wkbp.battleships.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wkbp.battleships.model.UserInGameEntity;

/**
 * @author Wiktor Rup
 */
@Repository
public interface UserInGameRepository extends JpaRepository<UserInGameEntity, Long> {
}