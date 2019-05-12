package wkbp.battleship.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wkbp.battleship.model.User;
import wkbp.battleship.model.repository.UserRepository;

/**
 * Class is responsible for exchanging information and returning the result of identifying provided User {@link wkbp.battleship.model.User} in database.
 * Please see the {@link org.springframework.security.core.userdetails.UserDetailsService} for true identity.
 * @author Wiktor Rup
 */

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found! Username or email is wrong -> " + username));

        return UserPrinciple.build(user);
    }
}
