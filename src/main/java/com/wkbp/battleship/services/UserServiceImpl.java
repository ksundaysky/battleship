package com.wkbp.battleship.services;

import com.wkbp.battleship.model.User;
import com.wkbp.battleship.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wiktor Rup
 */

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private
    UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
