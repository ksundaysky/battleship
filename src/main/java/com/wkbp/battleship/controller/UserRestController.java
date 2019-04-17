package com.wkbp.battleship.controller;

import com.wkbp.battleship.common.SuccessMessage;
import com.wkbp.battleship.dto.UserDto;
import com.wkbp.battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SuccessMessage> getUserBody(@RequestBody UserDto userDto) {
        userService.findUserByEmail(userDto.getEmail());
        return ResponseEntity.ok(new SuccessMessage("JEst super, zalogowany"));
    }
}
