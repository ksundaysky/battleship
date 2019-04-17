package com.wkbp.battleship.controller;

import com.wkbp.battleship.common.SuccessMessage;
import com.wkbp.battleship.dto.UserDto;
import com.wkbp.battleship.model.User;
import com.wkbp.battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostMapping("/getUser/{userDto}")
//    public ResponseEntity<SuccessMessage> getUser(@PathVariable("userData") String userDto) {
//        //userRepository.findByEmail();
//        return ResponseEntity.ok(new SuccessMessage("User successfully logged in"));
//    }

    @PostMapping("/getUser")
    public String getUserBody(@RequestBody UserDto userDto) {
        User user = userService.findUserByEmail(userDto.getEmail());
        return "Udalo sie";
    }
}
