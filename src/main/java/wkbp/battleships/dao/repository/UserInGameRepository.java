package wkbp.battleships.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;
import wkbp.battleships.model.User;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Repository
public interface UserInGameRepository extends JpaRepository<UserInGameEntity, Long> {

    @Query("select uig from UserInGameEntity uig where uig.user = :user and uig.gameEntity = :gameEntity")
    UserInGameEntity findUserInGameEntityByIds(@Param("user") User user, @Param("gameEntity") GameEntity gameEntity);
}
