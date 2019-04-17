package com.wkbp.battleship.model.repository;

import com.wkbp.battleship.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wiktor Rup
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
