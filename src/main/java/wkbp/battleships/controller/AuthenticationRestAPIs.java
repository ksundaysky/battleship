package wkbp.battleships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.message.request.LoginForm;
import wkbp.battleships.message.request.SignUpForm;
import wkbp.battleships.message.response.JwtResponse;
import wkbp.battleships.message.response.ResponseMessage;
import wkbp.battleships.service.AuthenticationService;

import javax.validation.Valid;

/**
 * This controller sets two major endpoints for logging in and signing up.
 * These endpoints accept requests from client side and proceed with them,
 * doing required action.
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/auth")
class AuthenticationRestAPIs {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign_in")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        JwtResponse jwtResponse = authenticationService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/sign_up")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (authenticationService.userExistsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("This username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (authenticationService.userExistsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Account with given email already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        authenticationService.createNewAccount(signUpRequest);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}