package com.wkbp.battleship.services;

import com.wkbp.battleship.model.User;
import com.wkbp.battleship.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wiktor Rup
 */

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private
    UserRepository userRepository;


    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
