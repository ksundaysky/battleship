package com.wkbp.battleship.services;

import com.wkbp.battleship.model.User;

/**
 * @author Wiktor Rup
 */
public interface UserService {
    User findUserByEmail(String email);
}
