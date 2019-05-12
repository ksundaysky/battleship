package wkbp.battleship.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wkbp.battleship.model.Role;
import wkbp.battleship.model.RoleName;

import java.util.Optional;

/**
 * @author Wiktor Rup
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
