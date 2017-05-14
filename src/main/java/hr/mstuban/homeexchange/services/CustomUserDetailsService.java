package hr.mstuban.homeexchange.services;

/**
 * Created by marko on 11.05.17..
 */


import hr.mstuban.homeexchange.domain.CustomUserDetails;
import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.repositories.UserRepository;
import hr.mstuban.homeexchange.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (null == user) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            List<String> userRoles = userRoleRepository.findRolesByUsername(username);
            return new CustomUserDetails(user, userRoles);
        }
    }
}