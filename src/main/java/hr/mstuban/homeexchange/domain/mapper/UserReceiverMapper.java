package hr.mstuban.homeexchange.domain.mapper;

import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mstuban on 03/08/17.
 */
@Component
public class UserReceiverMapper {

    @Autowired
    private UserService userService;

    public User asUser(String username) {
        if (username != null) {
            return userService.findByUsername(username);
        }
        return null;
    }

    public String asUsername(User user) {
        if (user != null) {
            return user.getUserName();
        }
        return null;
    }
}
