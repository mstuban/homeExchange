package hr.mstuban.homeexchange.services;


import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.NewUserForm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);

    List<User> listAll();

    User getById(Long id);

    User saveOrUpdate(User user);

    void delete(Long id);

    Object find(Pageable pageable);

    User createUser(NewUserForm form);

}