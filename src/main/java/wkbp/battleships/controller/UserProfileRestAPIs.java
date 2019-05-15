package wkbp.battleships.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains basic server endpoints for user board depending on role
 *
 * @author Patryk Kucharski
 * @author Wiktor Wrup
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserProfileRestAPIs {

    @GetMapping("/api/wkbp/get/user_board")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        // TODO: 14.05.19 implement
        return ">>> User board";
    }

    @GetMapping("/api/wkbp/get/admin_board")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        // TODO: 14.05.19 implement
        return ">>> Admin board";
    }

}
