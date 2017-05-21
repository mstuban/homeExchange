package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.UserRole;
import hr.mstuban.homeexchange.repositories.UserRoleRepository;
import hr.mstuban.homeexchange.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mstuban on 21.05.17..
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final
    UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

}
