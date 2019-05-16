package wkbp.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wkbp.battleships.model.User;

import java.util.Optional;

/**
 * Repository responsible for retrieving user from database and
 * checking if given user exists while registering a new account
 *
 * @author Krzysztof Niedzielski
 * @author Bartek Kupajski
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}