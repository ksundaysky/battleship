package com.wkbp.battleship.controller;

import com.wkbp.battleship.dto.UserDto;
import com.wkbp.battleship.model.User;
import com.wkbp.battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wiktor Rup
 */
@RestController
class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public HttpStatus getUserBody(@RequestBody UserDto userDto) {
        User user = userService.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (user != null) {
            return HttpStatus.ACCEPTED;
        } else
            return HttpStatus.UNAUTHORIZED;
    }
}
