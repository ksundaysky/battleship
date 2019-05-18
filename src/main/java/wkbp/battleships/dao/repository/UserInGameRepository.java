package wkbp.battleships.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Repository
public interface UserInGameRepository extends JpaRepository<UserInGameEntity, Long> {
}
