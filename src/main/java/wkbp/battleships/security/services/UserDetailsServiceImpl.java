package wkbp.battleships.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wkbp.battleships.model.User;
import wkbp.battleships.dao.repository.UserRepository;

/**
 * Class is responsible for exchanging information and returning the result
 * of identification of user in database {@link wkbp.battleships.model.User}.
 * Please see the {@link org.springframework.security.core.userdetails.UserDetailsService}
 * for true identity.
 *
 * @author Wiktor Rup
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(user);
    }
}