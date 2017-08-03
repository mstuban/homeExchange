package hr.mstuban.homeexchange.services;


import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.EditUserForm;
import hr.mstuban.homeexchange.domain.form.NewUserForm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    User getById(Long id);

    User saveOrUpdate(User user);

    void delete(Long id);

    Object find(Pageable pageable);

    User createUser(NewUserForm form);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

    boolean existsByUserNameIgnoreCase(String username);

    @Transactional
    void editUser(EditUserForm form, Long id);

    List<User> findAllExceptOneWithUsername(String username);

}