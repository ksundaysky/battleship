package com.wkbp.battleship.model.repository;

import com.wkbp.battleship.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Wiktor Rup
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select usr from User usr where usr.email = :email and usr.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
