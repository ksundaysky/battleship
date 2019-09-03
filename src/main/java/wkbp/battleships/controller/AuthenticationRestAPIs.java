package wkbp.battleships.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wkbp.battleships.message.request.LoginForm;
import wkbp.battleships.message.request.SignUpForm;
import wkbp.battleships.message.response.JwtResponse;
import wkbp.battleships.message.response.ResponseMessage;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;
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
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @PostMapping("/sign_in")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        JwtResponse jwtResponse = authenticationService.login(loginRequest);
        logger.info("attempt to login " + loginRequest.toString());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/sign_up")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (authenticationService.userExistsByUsername(signUpRequest.getUsername())) {
            String returnMessage = "This username is already taken! ";
            logger.error(returnMessage + signUpRequest.getUsername());
            return new ResponseEntity<>(new ResponseMessage(returnMessage),
                    HttpStatus.BAD_REQUEST);
        }
        if (authenticationService.userExistsByEmail(signUpRequest.getEmail())) {
            String returnMassage = "Account with given email already exists! ";
            logger.error(returnMassage + signUpRequest.getEmail());
            return new ResponseEntity<>(new ResponseMessage(returnMassage),
                    HttpStatus.BAD_REQUEST);
        }
        authenticationService.createNewAccount(signUpRequest);
        String returnMessage = "User registered successfully! ";
        logger.info(returnMessage + signUpRequest.toString());
        return new ResponseEntity<>(new ResponseMessage(returnMessage), HttpStatus.OK);
    }
}