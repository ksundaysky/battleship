package wkbp.battleships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import wkbp.battleships.dao.repository.RoleRepository;
import wkbp.battleships.dao.repository.UserRepository;
import wkbp.battleships.dao.repository.entity.Role;
import wkbp.battleships.message.request.LoginForm;
import wkbp.battleships.message.request.SignUpForm;
import wkbp.battleships.message.response.JwtResponse;
import wkbp.battleships.model.RoleName;
import wkbp.battleships.model.User;
import wkbp.battleships.security.jwt.JwtProvider;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createNewAccount(@RequestBody @Valid SignUpForm signUpRequest) {
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            if ("admin".equals(role)) {
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("User Role not found."));
                roles.add(adminRole);
            } else {
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("User Role not found."));
                roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
    }

    public JwtResponse login(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }
}
