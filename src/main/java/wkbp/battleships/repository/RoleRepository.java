package wkbp.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wkbp.battleships.model.Role;
import wkbp.battleships.model.RoleName;

import java.util.Optional;

/**
 * Repository responsible for retrieving role
 *
 * @author Krzysztof Niedzielski
 * @author Bartek Kupajski
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}