package wkbp.battleships.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains basic server endpoints for user board depending on role
 *
 * @author Patryk Kucharski
 * @author Wiktor Wrup
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wkbp/")
class UserProfileRestAPIs {

    @GetMapping("get/user_board")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    String userAccess() {
        // TODO: 14.05.19 implementacja
        return ">>> User board";
    }

    @GetMapping("get/admin_board")
    @PreAuthorize("hasRole('ADMIN')")
    String adminAccess() {
        // TODO: 14.05.19 implementacja
        return ">>> Admin board";
    }

}
