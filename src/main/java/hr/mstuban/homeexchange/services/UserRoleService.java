package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.UserRole;

import java.util.List;

/**
 * Created by mstuban on 21.05.17..
 */
public interface UserRoleService {

    List<UserRole> findAll();

}